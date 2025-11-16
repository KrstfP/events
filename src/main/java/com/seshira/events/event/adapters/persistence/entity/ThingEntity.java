package com.seshira.events.event.adapters.persistence.entity;

import com.seshira.events.common.models.thing.ThingType;
import com.seshira.events.event.domain.models.EventAdditionalType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.UUID;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ThingEntity {
    String name;
    @Enumerated(EnumType.STRING)
    ThingType type;
    String description;
    URI image; // URL to an image representing the Thing
    @Enumerated(EnumType.STRING)
    EventAdditionalType additionalType;
    @Id
    private UUID id; // Unique identifier for the Thing
    private URI url;

    @Override
    public String toString() {
        return "ThingEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", additionalType=" + additionalType +
                ", id=" + id +
                ", url=" + url +
                '}';
    }
}
