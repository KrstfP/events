package com.seshira.events.person.ports.inbound;

import com.seshira.events.person.ports.inbound.dto.CreatePersonPayload;
import com.seshira.events.person.ports.inbound.dto.PersonDto;

public interface CreatePersonUseCase {
    PersonDto createPerson(CreatePersonPayload payload);
}
