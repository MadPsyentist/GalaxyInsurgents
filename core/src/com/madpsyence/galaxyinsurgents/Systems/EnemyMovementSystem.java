package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.EnemyComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;
import com.madpsyence.galaxyinsurgents.Entities.EntityType;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;

import java.util.Random;

/**
 * Created by Lachie on 10/1/2016.
 */
public class EnemyMovementSystem extends IteratingSystem implements Listener<CollisionEvent>
{
    private ComponentMapper<MovementComponent> transMap;
    private ComponentMapper<BoundsComponent>  boundsMap;

    private boolean previousProcessEvent;
    private boolean processEvent;
    private boolean flipMovement;
    private Random rng;

    public EnemyMovementSystem()
    {
        super(Family.all(TransformComponent.class, MovementComponent.class,
                EnemyComponent.class).get(), CONST.SYSTEM_PRIORITY_MOVEMENT);

        transMap = ComponentMapper.getFor(MovementComponent.class);
        boundsMap = ComponentMapper.getFor(BoundsComponent.class);
        processEvent = false;
        flipMovement = false;

        rng = new Random();
    }

    @Override
    public void update(float deltaTime)
    {
        if(processEvent && !previousProcessEvent)
            flipMovement = !flipMovement;
        super.update(deltaTime);
        previousProcessEvent = processEvent;
        processEvent = false;
    }


    @Override
    public void processEntity(Entity entity, float deltaTime)
    {
        MovementComponent mov = movementComponentMap.get(entity);

        mov.Velocity.x *= -1;
    }

    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent event)
    {
        BoundsComponent bbA = boundsMap.get(event.EntityA);
        BoundsComponent bbB = boundsMap.get(event.EntityB);

        if(bbA.Type == EntityType.Enemy && bbB.Type == EntityType.Wall ||
                bbB.Type == EntityType.Enemy && bbA.Type == EntityType.Wall)
        {
            processEvent = true;
        }
    }
}
