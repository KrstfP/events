package com.seshira.events.person.ports.inbound.dto;

import java.util.UUID;


public record PersonDto(UUID id, String givenName, String familyName, String nationality, String email) {
}
