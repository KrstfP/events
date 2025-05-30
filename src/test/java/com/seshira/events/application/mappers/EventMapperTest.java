package com.seshira.events.application.mappers;

import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.services.CreateEventService;
import com.seshira.events.domain.services.models.CreateEventPayload;
import com.seshira.events.ports.inbound.dto.EventDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventMapperTest {

    @Test
    @DisplayName("Shall be able to map an Event to EventDto and back")
    void testEventMapper() throws URISyntaxException {
        // Given
        CreateEventService createEventService = new CreateEventService();
        EventMapper eventMapper = new EventMapperImpl();
        Event event = createEventService.createEvent(new CreateEventPayload(
                "Sample Event",
                "This is a sample event description.",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Sample Location",
                "123 Sample St, Sample City, SC 12345",
                "Sample Organizer",
                new URI("https://sampleorganizer.com"),
                new URI("https://sampleevent.com"),
                new URI("https://sampleevent.com/image.jpg")
        ));

        Event subEvent1 = createEventService.createEvent(new CreateEventPayload("Sub Event 1"));
        Event subEvent2 = createEventService.createEvent(new CreateEventPayload("Sub Event 2"));
        event.scheduleSubEvent(subEvent1);
        event.scheduleSubEvent(subEvent2);

        // When
        EventDto eventDto = eventMapper.toDto(event);
        Event eventFromDto = eventMapper.toEntity(eventDto);

        // Then
        assertNotNull(eventDto);
        assertEquals(event.getId(), eventDto.id());
        assertEquals(event.getName(), eventDto.name());
        assertEquals(event.getStartDate(), eventDto.startDate());
        assertEquals(event.getEndDate(), eventDto.endDate());
        assertEquals(event.getOrganizerUrl(), eventDto.organizerUrl());
        assertEquals(event.getImage(), eventDto.image());
        assertEquals(event.getLocationName(), eventDto.locationName());
        assertEquals(event.getLocationAddress(), eventDto.locationAddress());

        assertEquals(event.getId(), eventFromDto.getId());
    }
}