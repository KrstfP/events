package com.seshira.events.adapters.persistence;

import com.seshira.events.adapters.persistence.entity.EventEntity;
import com.seshira.events.adapters.persistence.mappers.EventEntityMapper;
import com.seshira.events.domain.models.Event;
import com.seshira.events.domain.models.EventAdditionalType;
import com.seshira.events.ports.outbound.FilterEventRepository;
import com.seshira.events.ports.outbound.GetEventChildrenRepository;
import com.seshira.events.ports.outbound.GetEventRepository;
import com.seshira.events.ports.outbound.SaveEventRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EventRepository implements SaveEventRepository, GetEventRepository, GetEventChildrenRepository, FilterEventRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final EventEntityMapper eventEntityMapper;

    public EventRepository(EntityManager entityManager, EventEntityMapper eventEntityMapper) {
        this.entityManager = entityManager;
        this.eventEntityMapper = eventEntityMapper;
    }

    @Override
    public Event byId(UUID eventId) {
        var eventEntity = entityManager.find(com.seshira.events.adapters.persistence.entity.EventEntity.class, eventId);
        return eventEntity == null ? null : eventEntityMapper.toDomain(eventEntity);
    }

    @Override
    @Transactional
    public void save(Event event) {
        EventEntity e = eventEntityMapper.toEntity(event);
        entityManager.persist(eventEntityMapper.toEntity(event));
        entityManager.flush();
    }

    @Override
    public List<Event> byParentId(UUID parentEventId) {
        List<EventEntity> subEventEntities = entityManager
                .createQuery(
                        "SELECT e FROM EventEntity e WHERE e.parentEvent.id = :parentId",
                        EventEntity.class
                )
                .setParameter("parentId", parentEventId)
                .getResultList();

        return subEventEntities.stream()
                .map(eventEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Event> filterByAdditionalType(EventAdditionalType additionalType) {
        TypedQuery<EventEntity> filterAll = entityManager
                .createQuery(
                        "SELECT e FROM EventEntity e",
                        EventEntity.class
                );
        TypedQuery<EventEntity> filter = entityManager
                .createQuery(
                        "SELECT e FROM EventEntity e WHERE e.additionalType = :additionalType",
                        EventEntity.class
                )
                .setParameter("additionalType", additionalType);


        List<EventEntity> subEventEntities = additionalType == null ?
                filterAll.getResultList() :
                filter.getResultList();

        return subEventEntities.stream()
                .map(eventEntityMapper::toDomain)
                .toList();
    }
}
