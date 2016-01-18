package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementClampComponent;
import com.madpsyence.galaxyinsurgents.Components.PlayerComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;
import com.madpsyence.galaxyinsurgents.Entities.EntityType;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Lachie on 12/1/2016.
 */
public class MovementClampSystem extends EntitySystem implements Listener<CollisionEvent>
{
    private ComponentMapper<TransformComponent> transMap;
    private ComponentMapper<BoundsComponent>  boundsMap;
    private ImmutableArray<Entity> entities;
    private Vector2 movement;

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
        for (Entity e: entities)
        {
            TransformComponent pos = transMap.get(e);
            pos.Position.x -= movement.x;
            pos.Position.y -= movement.y;
        }
        movement = new Vector2();
    }

    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent object)
    {
        BoundsComponent bbA = boundsMap.get(object.EntityA);
        BoundsComponent bbB = boundsMap.get(object.EntityB);

        if(bbA.Type == EntityType.Player && bbB.Type == EntityType.Wall)
        {
            if(bbA.Bound.getX() < bbB.Bound.getX() + bbB.Bound.getWidth())
                movement.x = -(bbB.Bound.getX() + bbB.Bound.getWidth());
            else if(bbA.Bound.getX() + bbA.Bound.getWidth() > bbB.Bound.getX())
                movement.x = bbB.Bound.getX() - bbA.Bound.getWidth();

            else if(bbA.Bound.getY() < bbB.Bound.getY() + bbB.Bound.getHeight())
                movement.y = -(bbB.Bound.getY() + bbB.Bound.getHeight());
            else if(bbA.Bound.getY() + bbA.Bound.getHeight() > bbB.Bound.getY())
                movement.y = bbB.Bound.getX() - bbA.Bound.getHeight();
        }
        else if(bbA.Type == EntityType.Wall && bbB.Type == EntityType.Player)
        {
            if(bbB.Bound.getX() < bbA.Bound.getX() + bbA.Bound.getWidth())
                movement.x = -(bbA.Bound.getX() + bbA.Bound.getWidth());
            else if(bbB.Bound.getX() + bbB.Bound.getWidth() > bbA.Bound.getX())
                movement.x = bbB.Bound.getX() - bbA.Bound.getWidth();

            else if(bbB.Bound.getY() < bbA.Bound.getY() + bbA.Bound.getHeight())
                movement.y = -(bbA.Bound.getY() + bbA.Bound.getHeight());
            else if(bbB.Bound.getY() + bbB.Bound.getHeight() > bbA.Bound.getY())
                movement.y = bbA.Bound.getX() - bbA.Bound.getHeight();
        }
    }
}
