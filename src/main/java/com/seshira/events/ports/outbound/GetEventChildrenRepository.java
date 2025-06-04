package com.seshira.events.ports.outbound;


import com.seshira.events.domain.models.Event;

import java.util.List;
import java.util.UUID;

public interface GetEventChildrenRepository {
    List<Event> byParentId(UUID parentEventId);
}
