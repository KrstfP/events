package com.seshira.events.event.ports.outbound;

import com.seshira.events.event.domain.models.Event;
import com.seshira.events.event.domain.models.EventAdditionalType;

import java.util.List;

public interface FilterEventRepository {
    List<Event> filterByAdditionalType(EventAdditionalType additionalType);
}
