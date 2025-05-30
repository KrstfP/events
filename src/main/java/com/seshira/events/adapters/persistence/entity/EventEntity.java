package com.seshira.events.adapters.persistence.entity;

import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.models.EventStatus;
import jakarta.persistence.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class EventEntity extends ThingEntity {
    private EventStatus eventStatus; // e.g., EventScheduled, EventCancelled
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String locationName;
    private String locationAddress;
    private String organizerName;
    private URI organizerUrl;
    @OneToMany(mappedBy = "parentEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventEntity> subEvents; // List of sub-events (if any)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Event parentEvent = null; // Parent event (if any)
}
