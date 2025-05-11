package com.seshira.events.ports.inbound.dto;

import jakarta.validation.constraints.NotBlank;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateEventPayloadDto(
        @NotBlank(message = "Name is mandatory")
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
        UUID parentEventId
) {
    public CreateEventPayloadDto(String name) {
        this(name, null, null, null, null, null, null, null, null, null, null);
    }
}
