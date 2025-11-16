package com.seshira.events.event.ports.inbound;

import com.seshira.events.event.ports.inbound.dto.EventDto;

import java.util.Optional;
import java.util.UUID;

public interface GetEventUseCase {
    public Optional<EventDto> byId(UUID eventId);
}
