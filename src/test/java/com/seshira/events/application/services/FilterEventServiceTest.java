package com.seshira.events.application.services;


import com.seshira.events.application.mappers.EventMapperImpl;
import com.seshira.events.domain.models.EventAdditionalType;
import com.seshira.events.domain.services.CreateEventService;
import com.seshira.events.ports.inbound.dto.CreateEventPayloadDto;
import com.seshira.events.ports.inbound.dto.EventDto;
import com.seshira.events.ports.outbound.FilterEventRepository;
import com.seshira.events.ports.outbound.GetEventRepository;
import com.seshira.events.ports.outbound.SaveEventRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test") // make sure application-test.yml uses H2
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class FilterEventServiceTest {

    private final int NB_CONGRESSES = 14;
    private final int NB_SESSIONS = 8;
    private final FilterEventRepository filterEventRepository;
    private final CreateEventUseCaseService createEventUseCaseService;

    public FilterEventServiceTest(SaveEventRepository saveEventRepository, FilterEventRepository filterEventRepository, GetEventRepository getEventRepository) {
        this.filterEventRepository = filterEventRepository;
        this.createEventUseCaseService = new CreateEventUseCaseService(
                new CreateEventService(),
                new EventMapperImpl(),
                getEventRepository,
                saveEventRepository
        );
    }

    @BeforeEach
    void setUp() {
        saveTestEvents();
    }

    private void saveTestEvents() {
        CreateEventPayloadDto congressEventPayload = new CreateEventPayloadDto("A congress");
        UUID congressEventId = null;
        for (var i = 0; i < NB_CONGRESSES; i++) {
            Optional<EventDto> parentEventDto = createEventUseCaseService.createCongress(congressEventPayload);
            congressEventId = parentEventDto.map(EventDto::getId).orElse(null);
        }
        CreateEventPayloadDto sessionEventPayload = new CreateEventPayloadDto(
                "Sample Event",
                "This is a sample event description.",
                null,
                null,
                "Sample Location",
                "123 Sample St, Sample City, SC 12345",
                "Sample Organizer",
                null,
                null,
                null,
                congressEventId
        );
        for (var i = 0; i < NB_SESSIONS; i++) {
            createEventUseCaseService.createSession(sessionEventPayload);
        }
    }

    @Nested
    public class EventsCanBeFilteredByAdditionalType {
        @Test
        @DisplayName("Shall support filtering events by additional type")
        void testReturnAllEventsIfAdditionalTypeIsNull() {
            // Given
            FilterEventsUseCaseService filterEventsUseCaseService = new FilterEventsUseCaseService(
                    filterEventRepository,
                    new EventMapperImpl()
            );

            // When
            List<EventDto> allEvents = filterEventsUseCaseService.filterEventsByAdditionalType(null);
            List<EventDto> allCongresses = filterEventsUseCaseService.filterEventsByAdditionalType(EventAdditionalType.CONGRESS);
            List<EventDto> allSessions = filterEventsUseCaseService.filterEventsByAdditionalType(EventAdditionalType.SESSION);

            // Then
            assertEquals(NB_CONGRESSES + NB_SESSIONS, allEvents.size());
            assertEquals(NB_CONGRESSES, allCongresses.size());
            assertEquals(NB_SESSIONS, allSessions.size());

            assertTrue(
                    allCongresses.stream().allMatch(e -> e.getAdditionalType() == EventAdditionalType.CONGRESS),
                    "All events should have additionalType = CONGRESS"
            );
            assertTrue(
                    allSessions.stream().allMatch(e -> e.getAdditionalType() == EventAdditionalType.SESSION),
                    "All events should have additionalType = SESSION"
            );
        }
    }
}
