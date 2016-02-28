package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Entities.EntityType;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;
import com.madpsyence.galaxyinsurgents.Events.SoundEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lachie on 10/2/2016.
 */
public class PlayerBulletCollisionSystem extends EntitySystem implements Listener<CollisionEvent>
{
    private List<CollisionEvent> events;

    private ComponentMapper<BoundsComponent> boundMap;

    private Engine engine;

    private Signal<Entity> EntityRemovalSignal;
    private Signal<String> SoundSignal;

    public PlayerBulletCollisionSystem(Signal<Entity> EntityRemovalSignal, Signal<String> SoundSignal,
                                       Signal<CollisionEvent> collisionSignal, int Priority)
    {
        events = new ArrayList<CollisionEvent>();
        this.priority = Priority;
        collisionSignal.add(this);
        this.EntityRemovalSignal = EntityRemovalSignal;
        this.SoundSignal = SoundSignal;
    }

    @Override
    public void addedToEngine(Engine engine)
    {
        this.engine = engine;
        boundMap = ComponentMapper.getFor(BoundsComponent.class);
    }

    @Override
    public void update(float deltaTime)
    {
        if(!events.isEmpty())
        {
            for(CollisionEvent e:events)
            {
                BoundsComponent bbObject = boundMap.get(e.EntityB);
                switch (bbObject.Type)
                {
                    case Enemy:
                    {
                        EntityRemovalSignal.dispatch(e.EntityB);
                        EntityRemovalSignal.dispatch(e.EntityA);
                        SoundSignal.dispatch(SoundEvents.Hit);
                    }
                    case KillZone:
                    {
                        EntityRemovalSignal.dispatch(e.EntityB);
                        EntityRemovalSignal.dispatch(e.EntityA);
                    }
                }
            }
        }
    }

    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent event)
    {
        BoundsComponent bbA = boundMap.get(event.EntityA);
        BoundsComponent bbB = boundMap.get(event.EntityB);
        if(bbA.Type == EntityType.PlayerBullet)
            events.add(new CollisionEvent(event.EntityA, event.EntityB));
        else if(bbB.Type == EntityType.PlayerBullet)
            events.add(new CollisionEvent(event.EntityB, event.EntityA));
    }
}
