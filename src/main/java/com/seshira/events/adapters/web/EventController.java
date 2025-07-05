package com.seshira.events.adapters.web;

import com.seshira.events.ports.inbound.CreateEventUseCase;
import com.seshira.events.ports.inbound.GetEventChildrenUseCase;
import com.seshira.events.ports.inbound.GetEventUseCase;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final GetEventUseCase getEventUseCase;
    private final GetEventChildrenUseCase getEventChildrenUseCase;

    public EventController(CreateEventUseCase createEventUseCase, GetEventUseCase getEventUseCase, GetEventChildrenUseCase getEventChildrenUseCase) {
        this.createEventUseCase = createEventUseCase;
        this.getEventUseCase = getEventUseCase;
        this.getEventChildrenUseCase = getEventChildrenUseCase;
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventDto> getEventById(@Valid @PathVariable UUID eventId) {
        return this.getEventUseCase.byId(eventId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/events/{eventId}/childrens")
    public ResponseEntity<List<EventDto>> getEventChildrenById(@Valid @PathVariable UUID eventId) {
        return ResponseEntity.ok(getEventChildrenUseCase.byParentId(eventId));
    }

    @PostMapping("/events/create")
    public ResponseEntity<EventDto> createUser(@Valid @RequestBody CreateEventPayloadDto eventInput) {
        return this.createEventUseCase.createEvent(eventInput)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
