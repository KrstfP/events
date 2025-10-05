package com.seshira.events.application.services;

import com.seshira.events.application.mappers.EventMapperImpl;
import com.seshira.events.domain.exception.BadInputException;
import com.seshira.events.domain.services.CreateEventService;
import com.seshira.events.ports.inbound.GetEventChildrenUseCase;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import com.seshira.events.ports.outbound.GetEventChildrenRepository;
import com.seshira.events.ports.outbound.GetEventRepository;
import com.seshira.events.ports.outbound.SaveEventRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // make sure application-test.yml uses H2
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CreateEventServiceTest {

    private final GetEventRepository getEventRepository;
    private final SaveEventRepository saveEventRepository;
    private final GetEventChildrenRepository getEventChildrenRepository;

    CreateEventServiceTest(GetEventRepository getEventRepository, SaveEventRepository saveEventRepository, GetEventChildrenRepository getEventChildrenRepository) {
        this.getEventRepository = getEventRepository;
        this.saveEventRepository = saveEventRepository;
        this.getEventChildrenRepository = getEventChildrenRepository;
    }


    @Nested
    class EventCanBeCreated {
        @Test
        @DisplayName("Shall be able to create a basic event")
        void testCreateEvent() {
            // Given
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    getEventRepository,
                    saveEventRepository
            );

            CreateEventPayloadDto payloadDto = new CreateEventPayloadDto(
                    "Sample Event",
                    "This is a sample event description.",
                    null,
                    null,
                    "Sample Location",
                    "123 Sample St, Sample City, SC 12345",
                    "Sample Organizer",
                    null,
                    null,
                    null,
                    null
            );

            // When
            Optional<EventDto> eventDto = createEventUseCaseService.createEvent(payloadDto);

            // Then
            assertNotNull(eventDto);
            assertTrue(eventDto.isPresent());
            assertEquals("Sample Event", eventDto.get().getName());
        }

        @Test
        @DisplayName("Shall be able to create an event with a parent event")
        void testCreateEventWithParent() {
            // Given
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    getEventRepository,
                    saveEventRepository
            );
            CreateEventPayloadDto parentPayloadDto = new CreateEventPayloadDto(
                    "Parent Event"
            );
            Optional<EventDto> parentEventDto = createEventUseCaseService.createEvent(parentPayloadDto);
            UUID parentEventId = parentEventDto.map(EventDto::getId).orElse(null);
            CreateEventPayloadDto payloadDto = new CreateEventPayloadDto(
                    "Sample Sub Event",
                    "This is a sample sub event description.",
                    null,
                    null,
                    "Sample Location",
                    "123 Sample St, Sample City, SC 12345",
                    "Sample Organizer",
                    null,
                    null,
                    null,
                    parentEventId
            );

            // When
            Optional<EventDto> eventDto = createEventUseCaseService.createEvent(payloadDto);

            // Then
            assertNotNull(eventDto);
            assertTrue(eventDto.isPresent());
            assertTrue(parentEventDto.isPresent());
            assertEquals(parentEventDto.get().getId(), eventDto.get().getParentEvent().getId());
            assertEquals(parentEventDto.get().getName(), eventDto.get().getParentEvent().getName());
        }
    }

    @Nested
    class CongressCanBeCreated {
        @Test
        @DisplayName("Shall be able to create a congress event")
        void testCreateCongress() {
            // Given
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    getEventRepository,
                    saveEventRepository
            );

            CreateEventPayloadDto payloadDto = new CreateEventPayloadDto(
                    "Sample Congress",
                    "This is a sample congress description.",
                    null,
                    null,
                    "Sample Location",
                    "123 Sample St, Sample City, SC 12345",
                    "Sample Organizer",
                    null,
                    null,
                    null,
                    null
            );

            // When
            Optional<EventDto> eventDto = createEventUseCaseService.createCongress(payloadDto);

            // Then
            assertNotNull(eventDto);
            assertTrue(eventDto.isPresent());
            assertEquals("Sample Congress", eventDto.get().getName());
        }

        @Test
        @DisplayName("A congress event can only be created with a parent event which is an EventSeries")
        void testCreateCongressWithParent() {
            // Given
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    getEventRepository,
                    saveEventRepository
            );
            CreateEventPayloadDto parentEventPayload = new CreateEventPayloadDto(
                    "A Congress"
            );

            Optional<EventDto> genericEventParentDto = createEventUseCaseService.createEvent(parentEventPayload);
            Optional<EventDto> eventSeriesParentDto = createEventUseCaseService.createEventSeries(parentEventPayload);
            CreateEventPayloadDto payloadWithGenericParentDto = new CreateEventPayloadDto(
                    "A Congress trying to be part of a Congress",
                    "This is a sample congress description.",
                    null,
                    null,
                    "Sample Location",
                    "123 Sample St, Sample City, SC 12345",
                    "Sample Organizer",
                    null,
                    null,
                    null,
                    genericEventParentDto.map(EventDto::getId).orElse(null)
            );
            CreateEventPayloadDto payloadWithEventSeriesParentDto = new CreateEventPayloadDto(
                    "A Congress trying to be part of a Congress",
                    "This is a sample congress description.",
                    null,
                    null,
                    "Sample Location",
                    "123 Sample St, Sample City, SC 12345",
                    "Sample Organizer",
                    null,
                    null,
                    null,
                    eventSeriesParentDto.map(EventDto::getId).orElse(null)
            );

            // When, Then
            assertThrows(RuntimeException.class, () -> createEventUseCaseService.createCongress(payloadWithGenericParentDto));
            assertNotNull(createEventUseCaseService.createCongress(payloadWithEventSeriesParentDto));
        }
    }

    @Nested
    class SessionCanBeCreated {
        @Test
        @DisplayName("A session event cannot be created without a parent event of type Congress")
        void testCreateSessionShallFailIfNoOrIncorrectParentType() {
            // Given
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    getEventRepository,
                    saveEventRepository
            );
            CreateEventPayloadDto parentPayloadDto = new CreateEventPayloadDto(
                    "Not a congress"
            );
            Optional<EventDto> parentEventDto = createEventUseCaseService.createEvent(parentPayloadDto);
            CreateEventPayloadDto payloadNoCongressDto = new CreateEventPayloadDto(
                    "A Session trying to be part of a non Congress",
                    "This is a sample session description.",
                    null,
                    null,
                    "Sample Location",
                    "123 Sample St, Sample City, SC 12345",
                    "Sample Organizer",
                    null,
                    null,
                    null,
                    parentEventDto.map(EventDto::getId).orElse(null)
            );

            CreateEventPayloadDto payloadNoParentDto = new CreateEventPayloadDto(
                    "A Session without parent",
                    "This is a sample session description.",
                    null,
                    null,
                    "Sample Location",
                    "123 Sample St, Sample City, SC 12345",
                    "Sample Organizer",
                    null,
                    null,
                    null,
                    null
            );

            // When, Then
            assertThrows(BadInputException.class, () -> createEventUseCaseService.createSession(payloadNoCongressDto));
            assertThrows(BadInputException.class, () -> createEventUseCaseService.createSession(payloadNoParentDto));

        }

        @Test
        @DisplayName("Shall be able to create a session event")
        void testCreateSession() {
            // Given
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    getEventRepository,
                    saveEventRepository
            );

            GetEventChildrenUseCase getEventChildrenUseCase = new GetEventUseCaseService(
                    getEventRepository,
                    getEventChildrenRepository,
                    new EventMapperImpl()
            );

            CreateEventPayloadDto parentPayloadDto = new CreateEventPayloadDto(
                    "Not a congress"
            );
            Optional<EventDto> parentEventDto = createEventUseCaseService.createCongress(parentPayloadDto);
            UUID parentEventId = parentEventDto.map(EventDto::getId).orElse(null);
            CreateEventPayloadDto payloadDto = new CreateEventPayloadDto(
                    "Sample Session",
                    "This is a sample session description.",
                    null,
                    null,
                    "Sample Location",
                    "123 Sample St, Sample City, SC 12345",
                    "Sample Organizer",
                    null,
                    null,
                    null,
                    parentEventId
            );

            // When
            Optional<EventDto> eventDto = createEventUseCaseService.createSession(payloadDto);

            // Then
            assertNotNull(eventDto);
            assertTrue(eventDto.isPresent());
            assertEquals("Sample Session", eventDto.get().getName());
            assertTrue(getEventChildrenUseCase.byParentId(parentEventId).stream().anyMatch(e -> e.getId().equals(eventDto.get().getId())));
        }
    }

}