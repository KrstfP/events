package com.seshira.events.common.models.thing;

import com.seshira.events.event.domain.models.EventAdditionalType;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.UUID;

@Getter
@Setter
public abstract class Thing {
    final UUID id; // Unique identifier for the Thing
    final ThingType type;
    String name;
    String description;
    URI image; // URL to an image representing the Thing
    EventAdditionalType additionalType;
    private URI url;

    protected Thing(UUID id, ThingType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    protected Thing(UUID id, ThingType type, String name, String description, URI url, URI image) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.url = url;
        this.image = image;
    }
}
