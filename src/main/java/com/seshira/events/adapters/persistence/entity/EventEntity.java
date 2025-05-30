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
@EqualsAndHashCode(callSuper = true)
public class EventEntity extends ThingEntity {
    @Enumerated(EnumType.STRING)
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
    private EventEntity parentEvent = null; // Parent event (if any)

    @Override
    public String toString() {
        String thing = super.toString();
        return thing + '\n' + "EventEntity{" +
                "eventStatus=" + eventStatus +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", locationName='" + locationName + '\'' +
                ", locationAddress='" + locationAddress + '\'' +
                ", organizerName='" + organizerName + '\'' +
                ", organizerUrl=" + organizerUrl +
                ", subEvents=" + subEvents +
                ", parentEvent=" + parentEvent +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", additionalType=" + additionalType +
                '}';
    }
}
