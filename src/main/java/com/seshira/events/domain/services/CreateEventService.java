package com.seshira.events.domain.services;

import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.services.models.CreateEventPayload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateEventService {


    public Event createEvent(CreateEventPayload createEventPayload, Event parent) {
        Event event = new Event(
                UUID.randomUUID(),
                createEventPayload.name(),
                createEventPayload.description(),
                createEventPayload.startDate(),
                createEventPayload.endDate(),
                createEventPayload.locationName(),
                createEventPayload.locationAddress(),
                createEventPayload.organizerName(),
                createEventPayload.organizerUrl(),
                createEventPayload.url(), // link to the event page
                createEventPayload.image(), // URL of an image
                parent
        );
        if (parent != null) {
            parent.scheduleSubEvent(event);
        }
        return event;
    }
}
