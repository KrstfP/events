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
    @DisplayName("Shall be able to add a sub-event to an event and get an ordered list of sub-events")
    void addSubEvent() throws MalformedURLException {
        // Given
        final int MINUTES_TO_ADD = 10;
        final int DURATIONS_MINUTES = 30;
        LocalDateTime before = LocalDateTime.now();
        LocalDateTime after = before.plusMinutes(10);
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

        CreateEventPayload payloadSubEventBefore = new CreateEventPayload(
            "Event before",
            "This is a sample event description.",
                before,
                before.plusMinutes(DURATIONS_MINUTES),
            "Sample Location",
            "123 Sample St, Sample City, SC 12345",
            "Sample Organizer",
            new URL("https://sampleorganizer.com"),
            new URL("https://sampleevent.com"),
            new URL("https://sampleevent.com/image.jpg")
        );

        CreateEventPayload payloadSubEventAfter = new CreateEventPayload(
                "Event before",
                "This is a sample event description.",
                after,
                after.plusMinutes(DURATIONS_MINUTES),
                "Sample Location",
                "123 Sample St, Sample City, SC 12345",
                "Sample Organizer",
                new URL("https://sampleorganizer.com"),
                new URL("https://sampleevent.com"),
                new URL("https://sampleevent.com/image.jpg")
        );

        Event event = createEventService.createEvent(payload);
        Event subEventBefore = createEventService.createEvent(payloadSubEventBefore);
        Event subEventAfter = createEventService.createEvent(payloadSubEventAfter);

        // When
        boolean result = event.scheduleSubEvent(subEventAfter);
        result &= event.scheduleSubEvent(subEventBefore);

        // Then
        assertEquals(true, result);
        assertEquals(2, event.getSubEvents().size());
        assertEquals(subEventBefore, event.getSubEvents().get(0));
        assertEquals(subEventAfter, event.getSubEvents().get(1));

    }
}
