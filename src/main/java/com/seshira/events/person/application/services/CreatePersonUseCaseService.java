package com.seshira.events.person.application.services;

import com.seshira.events.common.exception.BadInputException;
import com.seshira.events.person.domain.application.mappers.PersonMapper;
import com.seshira.events.person.domain.models.Person;
import com.seshira.events.person.ports.inbound.CreatePersonUseCase;
import com.seshira.events.person.ports.inbound.dto.CreatePersonPayload;
import com.seshira.events.person.ports.inbound.dto.PersonDto;
import com.seshira.events.person.ports.outbound.CountryService;
import com.seshira.events.person.ports.outbound.EmailService;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonUseCaseService implements CreatePersonUseCase {
    private final CountryService countryService;
    private final PersonMapper personMapper;
    private final EmailService emailService;

    public CreatePersonUseCaseService(CountryService countryService, PersonMapper personMapper, EmailService emailService) {
        this.countryService = countryService;
        this.personMapper = personMapper;
        this.emailService = emailService;
    }

    @Override
    public PersonDto createPerson(CreatePersonPayload payload) {
        if (payload.nationality() != null && !countryService.isValidCountryCodeA3(payload.nationality())) {
            throw new BadInputException("Invalid country: " + payload.nationality());
        }
        if (payload.email() != null && !emailService.isValidEmail(payload.email())) {
            throw new BadInputException("Invalid email: " + payload.email());
        }
        Person person = new Person(
                payload.givenName(),
                payload.familyName(),
                payload.nationality(),
                payload.email()
        );
        return personMapper.toDto(person);
    }
}
