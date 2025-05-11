package com.seshira.events.ports.inbound.dto;

import jakarta.validation.constraints.NotBlank;

import java.net.URI;
import java.time.LocalDateTime;


public record CreateEventPayloadDto(
        @NotBlank(message = "name is mandatory")
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String locationName,
        String locationAddress,
        String organizerName,
        URI organizerUrl,
        URI url, // link to the event page
        URI image
) {
        public CreateEventPayloadDto(String name) {
                this(name, null, null, null, null, null, null, null, null, null);
        }
}
