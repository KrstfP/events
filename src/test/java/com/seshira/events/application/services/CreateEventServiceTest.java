package com.seshira.events.application.services;

import com.seshira.events.application.mappers.EventMapperImpl;
import com.seshira.events.domain.services.CreateEventService;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateEventServiceTest {

    private final FakeEventRepository fakeEventRepository = new FakeEventRepository();


    @Nested
    public class EventCanBeCreated {
        @Test
        @DisplayName("Shall be able to create a basic event")
        void testCreateEvent() {
            // Given
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    fakeEventRepository,
                    fakeEventRepository
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
            assertEquals("Sample Event", eventDto.get().getName());
        }

        @Test
        @DisplayName("Shall be able to create an event with a parent event")
        void testCreateEventWithParent() {
            // Given
            CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                    new CreateEventService(),
                    new EventMapperImpl(),
                    fakeEventRepository,
                    fakeEventRepository
            );
            CreateEventPayloadDto parentPayloadDto = new CreateEventPayloadDto(
                    "Parent Event"
            );
            Optional<EventDto> parentEventDto = createEventUseCaseService.createEvent(parentPayloadDto);

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
                    parentEventDto.map(EventDto::getId).orElse(null)
            );

            // When
            Optional<EventDto> eventDto = createEventUseCaseService.createEvent(payloadDto);

            // Then
            assertNotNull(eventDto);
            assertEquals(parentEventDto.get().getId(), eventDto.get().getParentEvent().getId());
            assertEquals(parentEventDto.get().getName(), eventDto.get().getParentEvent().getName());
        }
    }


}