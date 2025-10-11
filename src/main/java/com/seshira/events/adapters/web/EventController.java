package com.seshira.events.adapters.web;

import com.seshira.events.domain.models.EventAdditionalType;
import com.seshira.events.ports.inbound.CreateEventUseCase;
import com.seshira.events.ports.inbound.FilterEventsUseCase;
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
    private final FilterEventsUseCase filterEventsUseCase;

    public EventController(CreateEventUseCase createEventUseCase, GetEventUseCase getEventUseCase, GetEventChildrenUseCase getEventChildrenUseCase, FilterEventsUseCase filterEventsUseCase) {
        this.createEventUseCase = createEventUseCase;
        this.getEventUseCase = getEventUseCase;
        this.getEventChildrenUseCase = getEventChildrenUseCase;
        this.filterEventsUseCase = filterEventsUseCase;
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> filterEvents(
            @RequestParam(name = "additionalType", required = false) EventAdditionalType additionalType
    ) {
        return ResponseEntity.ok(filterEventsUseCase.filterEventsByAdditionalType(additionalType));
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventDto> getEventById(@Valid @PathVariable UUID eventId) {
        return this.getEventUseCase.byId(eventId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/events/{eventId}/children")
    public ResponseEntity<List<EventDto>> getEventChildrenById(@Valid @PathVariable UUID eventId) {
        return ResponseEntity.ok(getEventChildrenUseCase.byParentId(eventId));
    }

    @PostMapping("/events/createCongress")
    public ResponseEntity<EventDto> createCongress(@Valid @RequestBody CreateEventPayloadDto eventInput) {
        return this.createEventUseCase.createCongress(eventInput)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/events/createSession")
    public ResponseEntity<EventDto> createSession(@Valid @RequestBody CreateEventPayloadDto eventInput) {
        return this.createEventUseCase.createSession(eventInput)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/events/createIntevention")
    public ResponseEntity<EventDto> createIntervention(@Valid @RequestBody CreateEventPayloadDto eventInput) {
        return this.createEventUseCase.createIntervention(eventInput)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
