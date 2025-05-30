package com.seshira.events.adapters.persistence.mappers;

import com.seshira.events.adapters.persistence.entity.EventEntity;
import com.seshira.events.domain.models.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_NULL)
public interface EventEntityMapper {
    EventEntity toEntity(Event event);

}
