package com.seshira.events.application.services;

import com.seshira.events.application.mappers.EventMapper;
import com.seshira.events.domain.models.Event;
import com.seshira.events.ports.inbound.GetEventUseCase;
import com.seshira.events.ports.inbound.dto.EventDto;
import com.seshira.events.ports.outbound.GetEventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetEventUseCaseService implements GetEventUseCase {
    private final GetEventRepository getEventRepository;
    private final EventMapper eventMapper;

    public GetEventUseCaseService(GetEventRepository getEventRepository, EventMapper eventMapper) {
        this.getEventRepository = getEventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public Optional<EventDto> byId(UUID eventId) {
        Event event = getEventRepository.byId(eventId);
        return event == null ? Optional.empty() : Optional.of(eventMapper.toDto(event));
    }
}
