package com.seshira.events.domain.models;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventSeries extends Event {
    public EventSeries(UUID id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, String locationName, String locationAddress, String organizerName, URI organizerUrl, URI url, URI image, Event parentEvent) {
        super(id, name, description, startDate, endDate, locationName, locationAddress, organizerName, organizerUrl, url, image, parentEvent);
    }
}
