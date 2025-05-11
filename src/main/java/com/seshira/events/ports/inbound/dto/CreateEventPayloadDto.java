package com.seshira.events.ports.inbound.dto;

import jakarta.validation.constraints.NotBlank;

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
        String organizerUrl,
        String url, // link to the event page
        String imageUrl
) {
        public CreateEventPayloadDto(String name) {
                this(name, null, null, null, null, null, null, null, null, null);
        }
}
