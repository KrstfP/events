package com.seshira.events.ports.inbound.dto;

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
}
