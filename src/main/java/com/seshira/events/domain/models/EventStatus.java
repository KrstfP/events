package com.seshira.events.domain.models;

/**
 * Represents the status of an Event based on schema.org/EventStatusType
 */
public enum EventStatus {
    EVENT_SCHEDULED,
    EVENT_CANCELLED,
    EVENT_POSTPONED,
    EVENT_MOVED_ONLINE,
    EVENT_RESCHEDULED
}
