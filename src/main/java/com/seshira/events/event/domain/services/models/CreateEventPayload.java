package com.seshira.events.event.domain.services.models;

import java.net.URI;
import java.time.ZonedDateTime;

public record CreateEventPayload(
        String name,
        String description,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        String locationName,
        String locationAddress,
        String organizerName,
        URI organizerUrl,
        URI url, // link to the event page
        URI image // URL of an image
) {
}