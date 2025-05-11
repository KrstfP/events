package com.seshira.events.ports.outbound;

import com.seshira.events.domain.models.Event;

import java.util.UUID;

public interface GetEventRepository {
    Event byId(UUID eventId);
}
