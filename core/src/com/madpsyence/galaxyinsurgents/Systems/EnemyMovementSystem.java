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
    private ComponentMapper<MovementComponent> movMap;
    private ComponentMapper<BoundsComponent>  boundsMap;
    private ComponentMapper<TransformComponent> transMap;
    private ComponentMapper<EnemyComponent> enemMap;

    private boolean previousProcessEvent;
    private boolean processEvent;
    private boolean flipMovement;
    private float dropDistance;
    private boolean drop;

    public EnemyMovementSystem(int Priority)
    {
        super(Family.all(TransformComponent.class, MovementComponent.class,
                EnemyComponent.class).get(), Priority);

        transMap = ComponentMapper.getFor(TransformComponent.class);
        boundsMap = ComponentMapper.getFor(BoundsComponent.class);
        movMap = ComponentMapper.getFor(MovementComponent.class);
        enemMap = ComponentMapper.getFor(EnemyComponent.class);

        processEvent = false;
        flipMovement = false;
        dropDistance = 25.0f;
    }

    @Override
    public void update(float deltaTime)
    {
        if(processEvent && !previousProcessEvent)
        {
            flipMovement = !flipMovement;
            drop = true;
        }
        super.update(deltaTime);
        previousProcessEvent = processEvent;
        processEvent = false;
        drop = false;
    }


    @Override
    public void processEntity(Entity entity, float deltaTime)
    {
        MovementComponent mov = movMap.get(entity);
        TransformComponent tran = transMap.get(entity);
        EnemyComponent enem = enemMap.get(entity);

        if(drop)
            tran.Position.y -= dropDistance;

        if(flipMovement)
            mov.Velocity.x = -enem.Speed;
        else
            mov.Velocity.x = enem.Speed;
    }

    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent event)
    {
        BoundsComponent bbA = boundsMap.get(event.EntityA);
        BoundsComponent bbB = boundsMap.get(event.EntityB);

        if(bbA.Type == EntityType.Enemy && bbB.Type == EntityType.Wall ||
                bbB.Type == EntityType.Enemy && bbA.Type == EntityType.Wall)
            processEvent = true;
    }
}
