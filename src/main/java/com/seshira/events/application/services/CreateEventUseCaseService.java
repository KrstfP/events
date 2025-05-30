package com.seshira.events.application.services;

import com.seshira.events.application.mappers.EventMapper;
import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.services.CreateEventService;
import com.seshira.events.domain.services.models.CreateEventPayload;
import com.seshira.events.ports.inbound.CreateEventUseCase;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import com.seshira.events.ports.outbound.GetEventRepository;
import com.seshira.events.ports.outbound.SaveEventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateEventUseCaseService implements CreateEventUseCase {
    private final CreateEventService createEventService;
    private final EventMapper eventMapper;
    private final GetEventRepository getEventRepository;
    private final SaveEventRepository saveEventRepository;

    public CreateEventUseCaseService(CreateEventService createEventService, EventMapper eventMapper, GetEventRepository getEventRepository, SaveEventRepository saveEventRepository) {
        this.createEventService = createEventService;
        this.eventMapper = eventMapper;
        this.getEventRepository = getEventRepository;
        this.saveEventRepository = saveEventRepository;
    }

    @Override
    public Optional<EventDto> createEvent(CreateEventPayloadDto payloadDto) {
        CreateEventPayload payload = eventMapper.toEntity(payloadDto);
        UUID parentEventId = payloadDto.parentEventId();
        Event parent =
                parentEventId != null
                        ? getEventRepository.byId(parentEventId)
                        : null;
        Event event = createEventService.createEvent(payload, parent);
        saveEventRepository.save(event);
        return Optional.ofNullable(eventMapper.toDto(event));
    }
}
