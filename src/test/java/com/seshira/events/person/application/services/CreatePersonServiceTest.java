package com.seshira.events.person.application.services;

import com.seshira.events.common.exception.BadInputException;
import com.seshira.events.person.ports.inbound.CreatePersonUseCase;
import com.seshira.events.person.ports.inbound.dto.CreatePersonPayload;
import com.seshira.events.person.ports.inbound.dto.PersonDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // make sure application-test.yml uses H2
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CreatePersonServiceTest {
    CreatePersonUseCase createPersonUseCase;

    public CreatePersonServiceTest(CreatePersonUseCase createPersonUseCase) {
        this.createPersonUseCase = createPersonUseCase;
    }


    @Test
    @DisplayName("Shall be able to create a person with minimal payload")
    void testCreatePerson() {
        // Given
        CreatePersonPayload payload = new CreatePersonPayload(
                "John",
                "Doe",
                "USA"
        );

        // When
        PersonDto personDto = createPersonUseCase.createPerson(payload);

        // Then
        assertNotNull(personDto);
        assertEquals(payload.givenName(), personDto.givenName());
        assertEquals(payload.familyName(), personDto.familyName());
        assertEquals(payload.nationality(), personDto.nationality());
    }

    @Test
    @DisplayName("Names shall be trimmed when creating a person")
    void testCreatePersonAndTrimNames() {
        // Given
        CreatePersonPayload payload = new CreatePersonPayload(
                "John  Patrick ",
                " Doe  Dee'n'Doo ",
                "USA"
        );

        // When
        PersonDto personDto = createPersonUseCase.createPerson(payload);

        // Then
        assertNotNull(personDto);
        assertEquals("John Patrick", personDto.givenName());
        assertEquals("Doe Dee'n'Doo", personDto.familyName());
        assertEquals(payload.nationality(), personDto.nationality());
    }

    @Test
    @DisplayName("Shall be able to create a person with non-ASCII characters")
    void testCreatePersonWithNonAsciiCharacters() {
        // Given
        CreatePersonPayload payload = new CreatePersonPayload(
                "Jürgen",
                "O'Connor-Smith",
                "ESP"
        );
        // When
        PersonDto personDto = createPersonUseCase.createPerson(payload);
        // Then
        assertNotNull(personDto);
        assertEquals(payload.givenName(), personDto.givenName());
        assertEquals(payload.familyName(), personDto.familyName());
        assertEquals(payload.nationality(), personDto.nationality());
    }

    @Test
    @DisplayName("Shall not be able to create a person with incorrect nationality country code")
    void testCreatePersonWithInvalidNationality() {
        // Given
        CreatePersonPayload payload = new CreatePersonPayload(
                "Alice",
                "Wonderland",
                "XX" // Invalid country code
        );
        // When & Then
        assertThrows(BadInputException.class, () -> createPersonUseCase.createPerson(payload));
    }

    @Test
    @DisplayName("Shall not be able to create a person with an email in the payload")
    void testCreatePersonWithEmailInPayload() {
        // Given
        CreatePersonPayload payload = new CreatePersonPayload(
                "Bob",
                "Builder",
                "CAN",
                "jj+2@jon.com"
        );
        // When
        PersonDto personDto = createPersonUseCase.createPerson(payload);
        // Then
        assertNotNull(personDto);
        assertEquals(payload.givenName(), personDto.givenName());
        assertEquals(payload.familyName(), personDto.familyName());
        assertEquals(payload.nationality(), personDto.nationality());
        assertEquals(payload.email(), personDto.email());
    }

    @Test
    @DisplayName("Shall not be able to create a person with invalid email in the payload")
    void testCreatePersonWithInvalidEmailInPayload() {
        // Given
        CreatePersonPayload payload = new CreatePersonPayload(
                "Charlie",
                "Chocolate",
                "GBR",
                "invalid-email@g"
        );
        // When & Then
        assertThrows(BadInputException.class, () -> createPersonUseCase.createPerson(payload));
    }
}
