package com.seshira.events.adapters.web;

import com.seshira.events.ports.inbound.CreateEventUseCase;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreateEventController {

    private final CreateEventUseCase createEventUseCase;

    public CreateEventController(CreateEventUseCase createEventUseCase) {
        this.createEventUseCase = createEventUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<EventDto> createUser(@Valid @RequestBody CreateEventPayloadDto eventInput) {
        return this.createEventUseCase.createEvent(eventInput)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
