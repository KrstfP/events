package com.seshira.events.event.ports.inbound;

import com.seshira.events.event.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.event.ports.inbound.dto.EventDto;

import java.util.Optional;

public interface CreateEventUseCase {
    Optional<EventDto> createEvent(CreateEventPayloadDto payloadDto);

    Optional<EventDto> createCongress(CreateEventPayloadDto payloadDto);

    Optional<EventDto> createSession(CreateEventPayloadDto payloadDto);

    Optional<EventDto> createIntervention(CreateEventPayloadDto payloadDto);

    Optional<EventDto> createEventSeries(CreateEventPayloadDto payloadDto);
}
