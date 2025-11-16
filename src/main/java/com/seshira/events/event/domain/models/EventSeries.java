package com.seshira.events.event.domain.models;

import java.util.UUID;

public class EventSeries extends Event {
    public EventSeries(UUID id, String name) {
        super(id, name);
        this.setAdditionalType(EventAdditionalType.EVENT_SERIES);
    }
}
