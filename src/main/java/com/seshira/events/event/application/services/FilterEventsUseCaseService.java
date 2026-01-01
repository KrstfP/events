package com.seshira.events.event.application.services;

import com.seshira.events.event.application.mappers.EventMapper;
import com.seshira.events.event.domain.models.EventAdditionalType;
import com.seshira.events.event.ports.inbound.FilterEventsUseCase;
import com.seshira.events.event.ports.inbound.dto.EventDto;
import com.seshira.events.event.ports.outbound.FilterEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterEventsUseCaseService implements FilterEventsUseCase {
    private final FilterEventRepository filterEventRepository;
    private final EventMapper eventMapper;

    public FilterEventsUseCaseService(FilterEventRepository filterEventRepository, EventMapper eventMapper) {
        this.filterEventRepository = filterEventRepository;
        this.eventMapper = eventMapper;
    }


    @Override
    public List<EventDto> filterEventsByAdditionalType(EventAdditionalType additionalType) {
        return this.filterEventRepository.filterByAdditionalType(additionalType).stream().map(eventMapper::toDto).toList();
    }
}
