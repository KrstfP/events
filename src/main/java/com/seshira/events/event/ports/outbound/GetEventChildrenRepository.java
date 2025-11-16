package com.seshira.events.event.ports.outbound;


import com.seshira.events.event.domain.models.Event;

import java.util.List;
import java.util.UUID;

public interface GetEventChildrenRepository {
    List<Event> byParentId(UUID parentEventId);
}
