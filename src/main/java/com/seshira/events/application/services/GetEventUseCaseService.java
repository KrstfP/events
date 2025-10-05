package com.seshira.events.application.services;

import com.seshira.events.application.mappers.EventMapper;
import com.seshira.events.domain.models.Event;
import com.seshira.events.ports.inbound.GetEventChildrenUseCase;
import com.seshira.events.ports.inbound.GetEventUseCase;
import com.seshira.events.ports.inbound.dto.EventDto;
import com.seshira.events.ports.outbound.GetEventChildrenRepository;
import com.seshira.events.ports.outbound.GetEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetEventUseCaseService implements GetEventUseCase, GetEventChildrenUseCase {
    private final GetEventRepository getEventRepository;
    private final GetEventChildrenRepository getEventChildrenRepository;
    private final EventMapper eventMapper;

    public GetEventUseCaseService(GetEventRepository getEventRepository, GetEventChildrenRepository getEventChildrenRepository, EventMapper eventMapper) {
        this.getEventRepository = getEventRepository;
        this.getEventChildrenRepository = getEventChildrenRepository;
        this.eventMapper = eventMapper;
    }


    @Override
    public Optional<EventDto> byId(UUID eventId) {
        Event event = getEventRepository.byId(eventId);
        return event == null ? Optional.empty() : Optional.of(eventMapper.toDto(event));
    }

    @Override
    public List<EventDto> byParentId(UUID parentEventId) {
        return getEventChildrenRepository.byParentId(parentEventId)
                .stream()
                .map(eventMapper::toDto)
                .toList();
    }
}
