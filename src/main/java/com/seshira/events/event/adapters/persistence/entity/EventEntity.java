package com.seshira.events.event.adapters.persistence.entity;

import com.seshira.events.event.domain.models.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.ZonedDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventEntity extends ThingEntity {
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String locationName;
    private String locationAddress;
    private String organizerName;
    private URI organizerUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private EventEntity parentEvent = null; // Parent event (if any)
}
