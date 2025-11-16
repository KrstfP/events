package com.seshira.events.event.domain.models;

import java.util.UUID;

public class InterventionEducationEvent extends EducationEvent {
    public InterventionEducationEvent(UUID id, String name) {
        super(id, name);
        this.setAdditionalType(EventAdditionalType.INTERVENTION);
    }
}
