package com.seshira.events.ports.inbound;

import com.seshira.events.ports.inbound.dto.EventDto;

import java.util.List;
import java.util.UUID;

public interface GetEventChildrenUseCase {
    List<EventDto> byParentId(UUID parentEventId);
}
