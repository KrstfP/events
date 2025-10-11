package com.seshira.events.domain.models;

import java.util.UUID;

public class SessionEducationEvent extends EducationEvent {

    public SessionEducationEvent(UUID id, String name) {
        super(id, name);
        this.setAdditionalType(EventAdditionalType.SESSION);
    }
}
