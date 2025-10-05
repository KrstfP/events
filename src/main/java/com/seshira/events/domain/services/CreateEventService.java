package com.seshira.events.domain.services;

import com.seshira.events.domain.exception.BadInputException;
import com.seshira.events.domain.models.*;
import com.seshira.events.domain.services.models.CreateEventPayload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateEventService {

    public EventSeries createEventSeries(CreateEventPayload createEventPayload, Event parent) {
        return new EventSeries(
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
    }

    public SessionEducationEvent createSession(CreateEventPayload createEventPayload, Event parent) {
        boolean parentIsNullOrCongress = parent == null || parent.getAdditionalType() != EventAdditionalType.CONGRESS;
        if (parentIsNullOrCongress)
            throw new BadInputException("A session can only be a sub-event of a Congress");
        return new SessionEducationEvent(
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
    }

    public CongressEducationEvent createCongress(CreateEventPayload createEventPayload, Event parent) {
        boolean parentIsNotNullAndNotEventSeries = parent != null && parent.getAdditionalType() != EventAdditionalType.EVENT_SERIES;
        if (parentIsNotNullAndNotEventSeries)
            throw new BadInputException("A congress can only be a sub-event of an EventSeries");
        return new CongressEducationEvent(
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
    }

    public Event createEvent(CreateEventPayload createEventPayload, Event parent) {
        return new Event(
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
    }
}
