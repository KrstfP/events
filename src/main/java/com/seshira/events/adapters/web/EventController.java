package com.seshira.events.adapters.web;

import com.seshira.events.ports.inbound.CreateEventUseCase;
import com.seshira.events.ports.inbound.GetEventUseCase;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final GetEventUseCase getEventUseCase;

    public EventController(CreateEventUseCase createEventUseCase, GetEventUseCase getEventUseCase) {
        this.createEventUseCase = createEventUseCase;
        this.getEventUseCase = getEventUseCase;
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventDto> getEventById(@Valid @PathVariable UUID eventId) {
        return this.getEventUseCase.byId(eventId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/events/create")
    public ResponseEntity<EventDto> createUser(@Valid @RequestBody CreateEventPayloadDto eventInput) {
        return this.createEventUseCase.createEvent(eventInput)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
