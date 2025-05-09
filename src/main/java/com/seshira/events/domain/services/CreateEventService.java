package com.seshira.events.domain.services;

import com.seshira.events.domain.models.CreateEventPayload;
import com.seshira.events.domain.models.Event;
import org.springframework.stereotype.Service;

@Service
public class CreateEventService {
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
