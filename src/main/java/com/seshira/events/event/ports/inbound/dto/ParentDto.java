package com.seshira.events.event.ports.inbound.dto;

import com.seshira.events.common.models.thing.ThingType;
import com.seshira.events.event.domain.models.EventAdditionalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentDto {
    private UUID id; // Identifiant unique pour la chose
    private String name;
    private ThingType type;
    private EventAdditionalType additionalType;
}
