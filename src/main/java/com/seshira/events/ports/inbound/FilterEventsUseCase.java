package com.seshira.events.ports.inbound;

import com.seshira.events.domain.models.EventAdditionalType;
import com.seshira.events.ports.inbound.dto.EventDto;

import java.util.List;

public interface FilterEventsUseCase {
    List<EventDto> filterEventsByAdditionalType(EventAdditionalType additionalType);
}
