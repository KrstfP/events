package com.seshira.events.event.ports.outbound;

import com.seshira.events.event.domain.models.Event;

public interface SaveEventRepository {
    void save(Event event);
}
