package com.seshira.events.domain.models;

import java.util.UUID;

public class CongressEducationEvent extends EducationEvent {
    public CongressEducationEvent(UUID id, String name) {
        super(id, name);
        this.setAdditionalType(EventAdditionalType.CONGRESS);
    }
}
