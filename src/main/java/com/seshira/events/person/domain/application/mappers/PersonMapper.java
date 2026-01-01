package com.seshira.events.person.domain.application.mappers;

import com.seshira.events.person.domain.models.Person;
import com.seshira.events.person.ports.inbound.dto.PersonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_NULL)
public interface PersonMapper {
    PersonDto toDto(Person person);
}
