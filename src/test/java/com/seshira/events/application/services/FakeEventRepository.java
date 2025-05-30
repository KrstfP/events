package com.seshira.events.application.services;

import com.seshira.events.domain.models.Event;
import com.seshira.events.ports.outbound.GetEventRepository;
import com.seshira.events.ports.outbound.SaveEventRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeEventRepository implements SaveEventRepository, GetEventRepository {
    private final Map<UUID, Event> eventById = new HashMap<>();


    @Override
    public void save(Event event) {
        eventById.put(event.getId(), event);
    }

    @Override
    public Event byId(UUID eventId) {
        return eventById.get(eventId);
    }
}
