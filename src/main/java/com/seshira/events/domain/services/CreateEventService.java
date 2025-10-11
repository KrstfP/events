package com.seshira.events.domain.services;

import com.seshira.events.domain.exception.BadInputException;
import com.seshira.events.domain.models.*;
import com.seshira.events.domain.services.models.CreateEventPayload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateEventService {

    private void updateEventFromPayload(Event event, CreateEventPayload createEventPayload) {
        event.setName(createEventPayload.name());
        event.setDescription(createEventPayload.description());
        event.setStartDate(createEventPayload.startDate());
        event.setEndDate(createEventPayload.endDate());
        event.setLocationName(createEventPayload.locationName());
        event.setLocationAddress(createEventPayload.locationAddress());
        event.setOrganizerName(createEventPayload.organizerName());
        event.setOrganizerUrl(createEventPayload.organizerUrl());
        event.setUrl(createEventPayload.url()); // link to the event page
        event.setImage(createEventPayload.image()); // URL of an image
    }

    public EventSeries createEventSeries(CreateEventPayload createEventPayload, Event parent) {
        EventSeries event = new EventSeries(
                UUID.randomUUID(),
                createEventPayload.name()
        );
        updateEventFromPayload(event, createEventPayload);
        event.setParentEvent(parent);
        return event;
    }

    public InterventionEducationEvent createIntervention(CreateEventPayload createEventPayload, Event parent) {
        boolean parentIsNullOrNotSession = parent == null || parent.getAdditionalType() != EventAdditionalType.SESSION;
        if (parentIsNullOrNotSession)
            throw new BadInputException("An intervention can only be a sub-event of a Session");
        InterventionEducationEvent event = new InterventionEducationEvent(UUID.randomUUID(), createEventPayload.name());
        updateEventFromPayload(event, createEventPayload);
        event.setParentEvent(parent);
        return event;
    }

    public SessionEducationEvent createSession(CreateEventPayload createEventPayload, Event parent) {
        boolean parentIsNullOrCongress = parent == null || parent.getAdditionalType() != EventAdditionalType.CONGRESS;
        if (parentIsNullOrCongress)
            throw new BadInputException("A session can only be a sub-event of a Congress");
        SessionEducationEvent event = new SessionEducationEvent(
                UUID.randomUUID(),
                createEventPayload.name()
        );
        updateEventFromPayload(event, createEventPayload);
        event.setParentEvent(parent);
        return event;
    }

    public CongressEducationEvent createCongress(CreateEventPayload createEventPayload, Event parent) {
        boolean parentIsNotNullAndNotEventSeries = parent != null && parent.getAdditionalType() != EventAdditionalType.EVENT_SERIES;
        if (parentIsNotNullAndNotEventSeries)
            throw new BadInputException("A congress can only be a sub-event of an EventSeries");
        CongressEducationEvent event = new CongressEducationEvent(
                UUID.randomUUID(),
                createEventPayload.name());
        updateEventFromPayload(event, createEventPayload);
        event.setParentEvent(parent);
        return event;
    }

    public Event createEvent(CreateEventPayload createEventPayload, Event parent) {
        Event event = new Event(
                UUID.randomUUID(),
                createEventPayload.name()
        );
        updateEventFromPayload(event, createEventPayload);
        event.setParentEvent(parent);
        return event;
    }
}
