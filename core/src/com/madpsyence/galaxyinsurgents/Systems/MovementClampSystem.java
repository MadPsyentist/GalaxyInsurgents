package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.PlayerComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;
import com.madpsyence.galaxyinsurgents.Entities.EntityType;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;

/**
 * Created by Lachie on 12/1/2016.
 */
public class MovementClampSystem extends EntitySystem implements Listener<CollisionEvent>
{
    private ComponentMapper<TransformComponent> transMap;
    private ComponentMapper<BoundsComponent>  boundsMap;
    private ImmutableArray<Entity> entities;
    private float movement;

    public MovementClampSystem()
    {
        this.priority = CONST.SYSTEM_PRIORITY_PHYSICS + CONST.TERTIRY_SYSTEM;
    }

    @Override
    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, TransformComponent.class).get());
        transMap = ComponentMapper.getFor(TransformComponent.class);
        boundsMap = ComponentMapper.getFor(BoundsComponent.class);
    }

    @Override
    public void update( float deltaTime)
    {
        if(movement != 0.0f)
        {
            for (Entity e : entities)
            {
                TransformComponent pos = transMap.get(e);
                pos.Position.x = movement;
            }
            movement = 0.0f;
        }
    }

    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent object)
    {
        BoundsComponent bbA = boundsMap.get(object.EntityA);
        BoundsComponent bbB = boundsMap.get(object.EntityB);

        if(bbA.Type == EntityType.Player && bbB.Type == EntityType.Wall)
        {
            if(bbB.Bound.getX() < 0) {
                movement = bbB.Bound.getX() + bbB.Bound.getWidth();
                System.out.println("left wall");
            }
            else {
                movement = bbB.Bound.getX() - bbA.Bound.getWidth();
                System.out.println("right wall");
            }
        }
        else if(bbA.Type == EntityType.Wall && bbB.Type == EntityType.Player)
        {
            if(bbA.Bound.getX() < 0) {
                movement = bbA.Bound.getX() + bbA.Bound.getWidth();
                System.out.println("left wall");
            }
            else {
                movement = bbB.Bound.getWidth() - bbA.Bound.getX();
                System.out.println("right wall");
            }
        }
    }
}
