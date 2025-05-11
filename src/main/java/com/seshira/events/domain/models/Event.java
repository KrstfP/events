package com.seshira.events.domain.models;

import lombok.Getter;

import java.time.LocalDateTime;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class Event extends Thing {
    private static final Comparator<Event> chronologicalOrderComparator = Comparator.comparing(Event::getStartDate);

    private EventStatus eventStatus; // e.g., EventScheduled, EventCancelled
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String locationName;
    private String locationAddress;
    private String organizerName;
    private URL organizerUrl;
    private List<Event> subEvents = new ArrayList<>(); // List of sub-events (if any)
    private Event parentEvent = null; // Parent event (if any)

    Event(String id) {
        super(id);
    }
    public Event(String id,
                 String name,
                 String description,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 String locationName,
                 String locationAddress,
                 String organizerName,
                 URL organizerUrl,
                 URL url,
                 URL image) {
        super(id, name, description, url, image);
        this.eventStatus = EventStatus.EVENT_SCHEDULED; // Default status
        this.startDate = startDate;
        this.endDate = endDate;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.organizerName = organizerName;
        this.organizerUrl = organizerUrl;
    }

    public EventStatus eventStatus() {
        return eventStatus;
    }

    public boolean equals(Event other) {
        return this.id.equals(other.id);
    }

    public void scheduleSubEvent(Event event) {
        subEvents.add(event);
        event.parentEvent = this;
    }


}
