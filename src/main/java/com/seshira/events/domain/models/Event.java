package com.seshira.events.domain.models;

import lombok.Getter;

import java.time.LocalDateTime;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Event extends Thing {
    private static final Comparator<Event> chronologicalOrderComparator = Comparator.comparing(Event::getStartDate);

    private EventStatus eventStatus; // e.g., EventScheduled, EventCancelled
    @Getter
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String locationName;
    private String locationAddress;
    private String organizerName;
    private URL organizerUrl;
    @Getter
    private List<Event> subEvents = new ArrayList<>(); // List of sub-events (if any)

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


    /// Add a new sub-event to the list of sub-events in chronological order
    public boolean scheduleSubEvent(Event subEvent) {
        if (subEvent != null) {
            int index = Collections.binarySearch(subEvents, subEvent, chronologicalOrderComparator);
            if (index < 0) {
                index = -(index + 1); // Trouver la position d'insertion
            }
            subEvents.add(index, subEvent);
            return true;
        }
        return false;
    }
}
