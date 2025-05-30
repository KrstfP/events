package com.seshira.events.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
abstract class Thing {
    final String id; // Unique identifier for the Thing
    String name;
    String description;
    private URI url;
    URI image; // URL to an image representing the Thing
    EventAdditionalType additionalType;

    Thing(String id){ this.id = id;}
    Thing(String id, String name, String description, URI url, URI image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.image = image;
    }
}
