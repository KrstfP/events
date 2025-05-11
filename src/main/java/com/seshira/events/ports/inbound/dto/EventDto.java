package com.seshira.events.ports.inbound.dto;


import com.seshira.events.domain.models.EventAdditionalType;
import com.seshira.events.domain.models.EventStatus;

import java.net.URI;
import java.time.LocalDateTime;

public record EventDto(
        String id, // Unique identifier for the Thing
        String name,
        String description,
        URI url,
        URI image, // URL to an image representing the Thing
        EventAdditionalType additionalType,
        EventStatus eventStatus, // e.g., EventScheduled, EventCancelled
        LocalDateTime startDate,
        LocalDateTime endDate,
        String locationName,
        String locationAddress,
        String organizerName,
        URI organizerUrl) {
}
