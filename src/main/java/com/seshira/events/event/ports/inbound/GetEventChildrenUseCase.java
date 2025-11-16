package com.seshira.events.event.ports.inbound;

import com.seshira.events.event.ports.inbound.dto.EventDto;

import java.util.List;
import java.util.UUID;

public interface GetEventChildrenUseCase {
    List<EventDto> byParentId(UUID parentEventId);
}
