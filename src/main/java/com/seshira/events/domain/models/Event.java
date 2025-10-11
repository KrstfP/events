package com.seshira.events.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Event extends Thing {
    @Setter
    private EventStatus eventStatus = EventStatus.EVENT_SCHEDULED;     // e.g., EventScheduled, EventCancelled
    @Setter
    private LocalDateTime startDate;
    @Setter
    private LocalDateTime endDate;
    @Setter
    private String locationName;
    @Setter
    private String locationAddress;
    @Setter
    private String organizerName;
    @Setter
    private URI organizerUrl;
    @Setter
    private Event parentEvent; // Parent event (if any)

    public Event(UUID id, String name) {
        super(id, ThingType.EVENT, name);
    }

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
        super(id, ThingType.EVENT, name, description, url, image);
        this.startDate = startDate;
        this.endDate = endDate;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.organizerName = organizerName;
        this.organizerUrl = organizerUrl;
        this.parentEvent = parentEvent;
    }

}
