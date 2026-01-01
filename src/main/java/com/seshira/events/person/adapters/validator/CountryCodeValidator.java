package com.seshira.events.person.adapters.validator;

import com.ibm.icu.util.ULocale;
import com.seshira.events.person.ports.outbound.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryCodeValidator implements CountryService {
    @Override
    public boolean isValidCountryCodeA3(String countryCode) {
        if (countryCode == null || countryCode.length() != 3) {
            return false; // format invalide
        }

        for (String alpha2 : ULocale.getISOCountries()) {
            ULocale locale = new ULocale("", alpha2);
            if (locale.getISO3Country().equalsIgnoreCase(countryCode)) {
                return true; // trouvé !
            }
        }

        return false;
    }
}
