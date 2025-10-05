package com.seshira.events.ports.inbound;

import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;

import java.util.Optional;

public interface CreateEventUseCase {
    Optional<EventDto> createEvent(CreateEventPayloadDto payloadDto);

    Optional<EventDto> createCongress(CreateEventPayloadDto payloadDto);

    Optional<EventDto> createSession(CreateEventPayloadDto payloadDto);
}
