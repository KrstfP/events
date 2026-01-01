package com.seshira.events.person.domain.models;

import com.seshira.events.common.models.thing.Thing;
import com.seshira.events.common.models.thing.ThingType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Person extends Thing {
    private String givenName;
    private String familyName;
    private String nationality; // alpha-3 code
    private String email = null;

    public Person(String givenName, String familyName, String nationality, String email) {
        this(givenName, familyName, nationality);
        this.email = email;
    }

    public Person(String givenName, String familyName, String nationality) {
        super(UUID.randomUUID(), ThingType.PERSON, "");
        String trimmedGivenName = trim(givenName);
        String trimmedFamilyName = trim(familyName);
        super.setDescription(trimmedGivenName + " " + trimmedFamilyName);
        this.givenName = trimmedGivenName;
        this.familyName = trimmedFamilyName;
        this.nationality = nationality;
    }

    private static String trim(String str) {
        String output = str.trim().replaceAll("\\s+", " ");
        String[] parts = output.split(" ");

        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!sb.isEmpty()) sb.append(" ");
            sb.append(part);
        }
        return sb.toString();
    }
}
