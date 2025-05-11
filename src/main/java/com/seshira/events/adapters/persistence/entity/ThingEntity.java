package com.seshira.events.adapters.persistence.entity;

import com.seshira.events.domain.models.EventAdditionalType;
import org.springframework.data.annotation.Id;

import java.net.URI;
import java.util.UUID;


public abstract class ThingEntity {
    String name;
    String description;
    URI image; // URL to an image representing the Thing
    EventAdditionalType additionalType;
    @Id
    private UUID id; // Unique identifier for the Thing
    private URI url;

}
