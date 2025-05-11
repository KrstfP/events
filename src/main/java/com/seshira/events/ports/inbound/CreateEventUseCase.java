package com.seshira.events.ports.inbound;

import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;

public interface CreateEventUseCase {
    EventDto createEvent(CreateEventPayloadDto payloadDto);
}
