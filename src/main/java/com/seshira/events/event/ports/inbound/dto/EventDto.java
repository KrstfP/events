package com.seshira.events.event.ports.inbound.dto;


import com.seshira.events.common.models.thing.ThingType;
import com.seshira.events.event.domain.models.EventAdditionalType;
import com.seshira.events.event.domain.models.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private UUID id; // Identifiant unique pour la chose
    private ThingType type;
    private String name;
    private String description;
    private URI url;
    private URI image; // URL vers une image représentant la chose
    private EventAdditionalType additionalType;
    private EventStatus eventStatus; // ex: EventScheduled, EventCancelled
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String locationName;
    private String locationAddress;
    private String organizerName;
    private URI organizerUrl;
    private ParentDto parentEvent;
}

