package com.seshira.events.application.mappers;


import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.services.models.CreateEventPayload;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_NULL)
public interface EventMapper {
    EventDto toDto(Event event);
    Event toEntity(EventDto eventDto);

    CreateEventPayloadDto toDto(CreateEventPayload payload);
    CreateEventPayload toEntity(CreateEventPayloadDto payloadDto);
}
