package com.seshira.events.adapters.persistence.mappers;

import com.seshira.events.adapters.persistence.entity.EventEntity;
import com.seshira.events.domain.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_NULL)
public interface EventEntityMapper {

    @Mapping(target = "parentEvent", source = "parentEvent", qualifiedByName = "mapParentDomainToEntity")
    EventEntity toEntity(Event event);

    @Mapping(target = "parentEvent", source = "parentEvent", qualifiedByName = "mapParentEntityToDomain")
    Event toDomain(EventEntity eventEntity);

    // Only map parent ID + name
    @Named("mapParentEntityToDomain")
    default Event mapParentEntityToDomain(EventEntity parentEntity) {
        if (parentEntity == null) return null;
        return new Event(parentEntity.getId(), parentEntity.getName(), null, null, null, null, null, null, null, null, null, null);
    }

    @Named("mapParentDomainToEntity")
    default EventEntity mapParentDomainToEntity(Event parent) {
        if (parent == null) return null;
        EventEntity entity = new EventEntity();
        entity.setId(parent.getId());
        return entity;
    }

}
