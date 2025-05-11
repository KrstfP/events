package com.seshira.events.application.services;

import com.seshira.events.application.mappers.EventMapperImpl;
import com.seshira.events.domain.services.CreateEventService;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateEventServiceTest {

    @Test
    @DisplayName("Shall be able to create an event")
    void testCreateEvent() {
        // Given
        CreateEventUseCaseService createEventUseCaseService = new CreateEventUseCaseService(
                new CreateEventService(),
                new EventMapperImpl()
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
                null
        );

        // When
        Optional<EventDto> eventDto = createEventUseCaseService.createEvent(payloadDto);

        // Then
        assertNotNull(eventDto);
        assertEquals("Sample Event", eventDto.get().name());
    }
}