package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.Components.EnemyComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;

import java.util.Random;

/**
 * Created by Lachie on 10/1/2016.
 */
public class EnemyMovementSystem extends IteratingSystem implements Listener<String>
{
    private ComponentMapper<MovementComponent> movementComponentMap;

    private boolean processEvent;
    private Random rng;

    public EnemyMovementSystem()
    {
        super(Family.all(TransformComponent.class, MovementComponent.class,
                EnemyComponent.class).get(), 10);

        movementComponentMap = ComponentMapper.getFor(MovementComponent.class);
        processEvent = false;

        rng = new Random();
    }

    @Override
    public void update(float deltaTime)
    {
        if(processEvent)
            super.update(deltaTime);
        processEvent = !processEvent;
    }


    @Override
    public void processEntity(Entity entity, float deltaTime)
    {
        MovementComponent mov = movementComponentMap.get(entity);

        mov.Velocity.x *= -1;
    }

    @Override
    public void receive(Signal<String> signal, String collision)
    {
        processEvent = !processEvent;
    }
}
