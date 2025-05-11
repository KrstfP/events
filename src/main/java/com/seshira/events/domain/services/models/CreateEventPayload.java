package com.seshira.events.domain.services.models;

import java.net.URL;
import java.time.LocalDateTime;

public record CreateEventPayload (
    String name,
    String description,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String locationName,
    String locationAddress,
    String organizerName,
    URL organizerUrl,
    URL url, // link to the event page
    URL image // URL of an image
) {
    public CreateEventPayload(String name) {
        this(name, null, null, null, null, null, null, null, null, null);
    }
}