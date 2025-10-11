package com.seshira.events.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.UUID;

@Getter
@Setter
abstract class Thing {
    final UUID id; // Unique identifier for the Thing
    final ThingType type;
    String name;
    String description;
    URI image; // URL to an image representing the Thing
    EventAdditionalType additionalType;
    private URI url;

    Thing(UUID id, ThingType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    Thing(UUID id, ThingType type, String name, String description, URI url, URI image) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.url = url;
        this.image = image;
    }
}
