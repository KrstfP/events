package com.seshira.events.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Event extends Thing {

    private EventStatus eventStatus; // e.g., EventScheduled, EventCancelled
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String locationName;
    private String locationAddress;
    private String organizerName;
    private URI organizerUrl;
    private List<Event> subEvents = new ArrayList<>(); // List of sub-events (if any)
    @Setter
    private Event parentEvent; // Parent event (if any)

    public Event(UUID id,
                 String name,
                 String description,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 String locationName,
                 String locationAddress,
                 String organizerName,
                 URI organizerUrl,
                 URI url,
                 URI image,
                 Event parentEvent) {
        super(id, name, description, url, image);
        this.eventStatus = EventStatus.EVENT_SCHEDULED; // Default status
        this.startDate = startDate;
        this.endDate = endDate;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.organizerName = organizerName;
        this.organizerUrl = organizerUrl;
        this.parentEvent = parentEvent;
    }

    public void scheduleSubEvent(Event event) {
        subEvents.add(event);
        event.parentEvent = this;
    }


}
