package com.seshira.events;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {
        org.springframework.cloud.vault.config.VaultAutoConfiguration.class
})
class EventsApplicationTests {
}
