package com.seshira.events.domain.services;

import com.seshira.events.domain.models.CongressEducationEvent;
import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.models.EventSeries;
import com.seshira.events.domain.models.SessionEducationEvent;
import com.seshira.events.domain.services.models.CreateEventPayload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateEventService {


    public SessionEducationEvent createSession(CreateEventPayload createEventPayload, Event parent) {
//        boolean parentIsNullOrCongress = parent == null || parent.getAdditionalType() == EventAdditionalType.CONGRESS;
//        if (!parentIsNullOrCongress)
//            throw new RuntimeException("A session can only be a sub-event of a Congress");
        SessionEducationEvent event = new SessionEducationEvent(
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

    public CongressEducationEvent createCongress(CreateEventPayload createEventPayload, Event parent) {
        boolean parentIsNullOrEventSeries = parent == null || parent instanceof EventSeries;
        if (!parentIsNullOrEventSeries)
            throw new RuntimeException("A congress can only be a sub-event of an EventSeries");
        CongressEducationEvent event = new CongressEducationEvent(
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
