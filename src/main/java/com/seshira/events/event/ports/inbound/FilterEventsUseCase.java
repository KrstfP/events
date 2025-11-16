package com.seshira.events.event.ports.inbound;

import com.seshira.events.event.domain.models.EventAdditionalType;
import com.seshira.events.event.ports.inbound.dto.EventDto;

import java.util.List;

public interface FilterEventsUseCase {
    List<EventDto> filterEventsByAdditionalType(EventAdditionalType additionalType);
}
