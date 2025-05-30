package com.seshira.events.adapters.persistence;

import com.seshira.events.domain.models.Event;
import com.seshira.events.ports.outbound.GetEventRepository;
import com.seshira.events.ports.outbound.SaveEventRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventRepository implements SaveEventRepository, GetEventRepository {
    @Override
    public Event byId(UUID eventId) {
        return null;
    }

    @Override
    public void save(Event event) {

    }
}
