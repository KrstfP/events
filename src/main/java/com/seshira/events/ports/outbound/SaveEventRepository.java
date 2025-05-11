package com.seshira.events.ports.outbound;

import com.seshira.events.domain.models.Event;

public interface SaveEventRepository {
    void save(Event event);
}
