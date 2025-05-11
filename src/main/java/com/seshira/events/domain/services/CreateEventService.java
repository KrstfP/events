package com.seshira.events.domain.services;

import com.seshira.events.domain.models.CreateEventPayload;
import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.models.EventAdditionalType;
import org.springframework.stereotype.Service;

@Service
public class CreateEventService {

   private Event createEventWithType(CreateEventPayload createEventPayload, EventAdditionalType eventType) {
        Event event = createEvent(createEventPayload);
        event.setAdditionalType(eventType);
        return event;
    }

    public Event createCongressEvent(CreateEventPayload createEventPayload) {
        return createEventWithType(createEventPayload, EventAdditionalType.CONGRESS);
    }

    public Event createSessionEvent(CreateEventPayload createEventPayload) {
        return createEventWithType(createEventPayload, EventAdditionalType.SESSION);
    }

    public Event createInterventionEvent(CreateEventPayload createEventPayload) {
        return createEventWithType(createEventPayload, EventAdditionalType.INTERVENTION);
    }


    public Event createEvent(CreateEventPayload createEventPayload) {
        String id = java.util.UUID.randomUUID().toString();
        return new Event(
                id,
                createEventPayload.name(),
                createEventPayload.description(),
                createEventPayload.startDate(),
                createEventPayload.endDate(),
                createEventPayload.locationName(),
                createEventPayload.locationAddress(),
                createEventPayload.organizerName(),
                createEventPayload.organizerUrl(),
                createEventPayload.url(), // link to the event page
                createEventPayload.image() // URL of an image
        );
    }
}
