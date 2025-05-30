package com.seshira.events.domain.services.models;

import java.net.URI;
import java.time.LocalDateTime;

public record CreateEventPayload (
    String name,
    String description,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String locationName,
    String locationAddress,
    String organizerName,
    URI organizerUrl,
    URI url, // link to the event page
    URI image // URL of an image
) {
    public CreateEventPayload(String name) {
        this(name, null, null, null, null, null, null, null, null, null);
    }
}