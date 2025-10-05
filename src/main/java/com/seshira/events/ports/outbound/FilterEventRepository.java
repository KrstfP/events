package com.seshira.events.ports.outbound;

import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.models.EventAdditionalType;

import java.util.List;

public interface FilterEventRepository {
    List<Event> filterByAdditionalType(EventAdditionalType additionalType);
}
