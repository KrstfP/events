package com.seshira.events.adapters.persistence.entity;

import com.seshira.events.domain.models.EventAdditionalType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.net.URI;
import java.util.UUID;


@MappedSuperclass
public abstract class ThingEntity {
    String name;
    String description;
    URI image; // URL to an image representing the Thing
    @Enumerated(EnumType.STRING)
    EventAdditionalType additionalType;
    @Id
    private UUID id; // Unique identifier for the Thing
    private URI url;

}
