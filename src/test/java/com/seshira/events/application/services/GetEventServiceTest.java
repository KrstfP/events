package com.seshira.events.application.services;

import com.seshira.events.application.mappers.EventMapperImpl;
import com.seshira.events.domain.services.CreateEventService;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test") // make sure application-test.yml uses H2
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class GetEventServiceTest {

    private final GetEventRepository getEventRepository;
    private final GetEventChildrenRepository getEventChildrenRepository;
    private final SaveEventRepository saveEventRepository;

    public GetEventServiceTest(GetEventRepository getEventRepository, GetEventChildrenRepository getEventChildrenRepository, SaveEventRepository saveEventRepository) {
        this.getEventRepository = getEventRepository;
        this.getEventChildrenRepository = getEventChildrenRepository;
        this.saveEventRepository = saveEventRepository;
    }


    @Nested
    public class EventChildrenCanBeRetrieved {
        @Test
        @DisplayName("Shall be able to get all children of an event")
        void testCreateEvent() {
            // Given
            GetEventUseCaseService getEventUseCaseService = new GetEventUseCaseService(
                    getEventRepository,
                    getEventChildrenRepository,
                    new EventMapperImpl()
            );
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    getEventRepository,
                    saveEventRepository
            );

            CreateEventPayloadDto payloadDto = new CreateEventPayloadDto(
                    "Sample Parent Event"
            );
            Optional<EventDto> eventDto = createEventUseCaseService.createEvent(payloadDto);
            CreateEventPayloadDto childrenPayloadDto = new CreateEventPayloadDto(
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
                     eventDto.map(EventDto::getId).orElse(null)
            );

            // When
            final var nbChildren = 5;
            for (int i = 0; i < nbChildren; i++) {
                createEventUseCaseService.createEvent(childrenPayloadDto);
            }

            // Then
            List<EventDto> children = getEventUseCaseService.byParentId(eventDto.map(EventDto::getId).orElse(null));
            assertEquals(5, children.size());
        }

    }
}
