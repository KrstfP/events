package com.seshira.events.adapters.web;

import com.seshira.events.ports.inbound.CreateEventUseCase;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api")
public class CreateEventController {

    private final CreateEventUseCase createEventUseCase;

    public record TestDto(@NotNull String name) {}

    @PostMapping("/test")
    public ResponseEntity<?> test(@Valid @RequestBody TestDto dto) {
        return ResponseEntity.ok("ok");
    }

    public CreateEventController(CreateEventUseCase createEventUseCase) {
        this.createEventUseCase = createEventUseCase;
    }

    @PostMapping("/create2")
    public ResponseEntity<EventDto> createUser2(@Valid @RequestBody Shit shit) {
        return this.createEventUseCase.createEvent(new CreateEventPayloadDto(shit.name))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PostMapping("/create")
    public ResponseEntity<EventDto> createUser(@Valid @RequestBody CreateEventPayloadDto eventInput) {
        System.out.println("Received event input: " + eventInput);
        return this.createEventUseCase.createEvent(eventInput)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
