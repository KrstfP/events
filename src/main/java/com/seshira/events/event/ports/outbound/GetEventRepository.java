package com.seshira.events.event.ports.outbound;

import com.seshira.events.event.domain.models.Event;

import java.util.UUID;

public interface GetEventRepository {
    Event byId(UUID eventId);
}
