package com.seshira.events.domain;

import com.seshira.events.domain.models.CreateEventPayload;
import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.models.EventStatus;
import com.seshira.events.domain.services.CreateEventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CreateEventServiceTest {

    @Test
    @DisplayName("Should create a valid Event from a valid CreateEventPayload")
    void testCreateEvent() throws MalformedURLException {
        // Given
        CreateEventService createEventService = new CreateEventService();
        CreateEventPayload payload = new CreateEventPayload(
            "Sample Event",
            "This is a sample event description.",
                LocalDateTime.now(),
                LocalDateTime.now(),
            "Sample Location",
            "123 Sample St, Sample City, SC 12345",
            "Sample Organizer",
            new URL("https://sampleorganizer.com"),
            new URL("https://sampleevent.com"),
            new URL("https://sampleevent.com/image.jpg")
        );

        // When
        Event event = createEventService.createEvent(payload);

        // Then
        assertNotNull(event);
        assertEquals(event.eventStatus(), EventStatus.EVENT_SCHEDULED);
    }


    @Test
    @DisplayName("Shall be able to add a sub-event ")
    void addSubEventAtPosition() {
        // Given
        CreateEventService createEventService = new CreateEventService();
        CreateEventPayload payload = new CreateEventPayload(
            "Sample Event"
        );

        Event event = createEventService.createEvent(payload);
        Event subEvent1 = createEventService.createEvent(new CreateEventPayload("Sub Event 1"));
        Event subEvent2 = createEventService.createEvent(new CreateEventPayload("Sub Event 2"));

        // When
        event.scheduleSubEvent(subEvent1);
        event.scheduleSubEvent(subEvent2);

        // Then
        assertEquals(2, event.getSubEvents().size());
        assertEquals(subEvent1, event.getSubEvents().get(0));
        assertEquals(subEvent2, event.getSubEvents().get(1));
        assertEquals(event, subEvent1.getParentEvent());
        assertEquals(event, subEvent2.getParentEvent());
    }
}
