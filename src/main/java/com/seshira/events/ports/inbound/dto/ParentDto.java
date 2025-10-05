package com.seshira.events.ports.inbound.dto;

import com.seshira.events.domain.models.EventAdditionalType;
import com.seshira.events.domain.models.ThingType;
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
