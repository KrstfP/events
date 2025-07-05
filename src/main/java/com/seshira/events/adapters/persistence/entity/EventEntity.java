package com.seshira.events.adapters.persistence.entity;

import com.seshira.events.domain.models.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventEntity extends ThingEntity {
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String locationName;
    private String locationAddress;
    private String organizerName;
    private URI organizerUrl;
    @OneToMany(mappedBy = "parentEvent", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EventEntity> subEvents; // List of sub-events (if any)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private EventEntity parentEvent = null; // Parent event (if any)
}
