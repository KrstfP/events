package com.seshira.events.adapters.persistence;

import com.seshira.events.adapters.persistence.mappers.EventEntityMapper;
import com.seshira.events.domain.models.Event;
import com.seshira.events.ports.outbound.GetEventRepository;
import com.seshira.events.ports.outbound.SaveEventRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class EventRepository implements SaveEventRepository, GetEventRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final EventEntityMapper eventEntityMapper;

    public EventRepository(EntityManager entityManager, EventEntityMapper eventEntityMapper) {
        this.entityManager = entityManager;
        this.eventEntityMapper = eventEntityMapper;
    }

    @Override
    public Event byId(UUID eventId) {
        return null;
    }

    @Override
    @Transactional
    public void save(Event event) {
        System.out.println(event);
        System.out.println(eventEntityMapper.toEntity(event));
        entityManager.persist(eventEntityMapper.toEntity(event));
    }
}
