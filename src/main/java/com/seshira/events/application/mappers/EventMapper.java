package com.seshira.events.application.mappers;


import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.services.models.CreateEventPayload;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import com.seshira.events.ports.inbound.dto.ParentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_NULL)
public interface EventMapper {

    @Mapping(source = "parentEvent", target = "parentEvent", qualifiedByName = "toParentDto")
    EventDto toDto(Event event);

    @Named("toParentDto")
    ParentDto toParentDto(Event parentEvent);

    CreateEventPayload toEntity(CreateEventPayloadDto payloadDto);
}
