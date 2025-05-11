package com.seshira.events.ports.inbound.dto;


import java.time.LocalDateTime;

public record EventDto(
        String id, // Unique identifier for the Thing
        String name,
        String description,
        String url,
        String image, // URL to an image representing the Thing
        String additionalType,
        String eventStatus, // e.g., EventScheduled, EventCancelled
        LocalDateTime startDate,
        LocalDateTime endDate,
        String locationName,
        String locationAddress,
        String organizerName,
        String organizerUrl) {
}
