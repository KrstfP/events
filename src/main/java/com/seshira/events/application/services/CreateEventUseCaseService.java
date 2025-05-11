package com.seshira.events.application.services;

import com.seshira.events.application.mappers.EventMapper;
import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.services.CreateEventService;
import com.seshira.events.domain.services.models.CreateEventPayload;
import com.seshira.events.ports.inbound.CreateEventUseCase;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import org.springframework.stereotype.Service;

@Service
public class CreateEventUseCaseService implements CreateEventUseCase {
    private final CreateEventService createEventService;
    private final EventMapper eventMapper;

    public CreateEventUseCaseService(CreateEventService createEventService, EventMapper eventMapper) {
        this.createEventService = createEventService;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDto createEvent(CreateEventPayloadDto payloadDto) {
        CreateEventPayload payload = eventMapper.toEntity(payloadDto);
        Event event = createEventService.createEvent(payload);
        return eventMapper.toDto(event);
    }
}
