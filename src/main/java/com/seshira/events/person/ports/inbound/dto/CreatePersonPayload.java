package com.seshira.events.person.ports.inbound.dto;

public record CreatePersonPayload(
        String givenName,
        String familyName,
        String nationality,
        String email) {
    public CreatePersonPayload(String givenName, String familyName, String nationality) {
        this(givenName, familyName, nationality, null);
    }
}
