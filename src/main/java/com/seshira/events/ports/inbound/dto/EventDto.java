package com.seshira.events.ports.inbound.dto;


import com.seshira.events.domain.models.EventAdditionalType;
import com.seshira.events.domain.models.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private UUID id; // Identifiant unique pour la chose
    private String name;
    private String description;
    private URI url;
    private URI image; // URL vers une image représentant la chose
    private EventAdditionalType additionalType;
    private EventStatus eventStatus; // ex: EventScheduled, EventCancelled
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String locationName;
    private String locationAddress;
    private String organizerName;
    private URI organizerUrl;
    private List<EventDto> subEvents;
    private ParentDto parentEvent;
}

