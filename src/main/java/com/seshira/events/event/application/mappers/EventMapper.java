package com.seshira.events.event.application.mappers;


import com.seshira.events.event.domain.models.Event;
import com.seshira.events.event.domain.services.models.CreateEventPayload;
import com.seshira.events.event.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.event.ports.inbound.dto.EventDto;
import com.seshira.events.event.ports.inbound.dto.ParentDto;
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
