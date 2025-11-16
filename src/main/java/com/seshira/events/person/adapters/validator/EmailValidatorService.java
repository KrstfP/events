package com.seshira.events.person.adapters.validator;

import com.seshira.events.person.ports.outbound.EmailService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;


@Service
public class EmailValidatorService implements EmailService {

    @Override
    public boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
