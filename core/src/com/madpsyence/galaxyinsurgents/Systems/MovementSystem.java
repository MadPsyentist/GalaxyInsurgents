package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.DebugComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementComponent;

/**
 * Created by Lachie on 9/1/2016.
 */
public class MovementSystem extends IteratingSystem
{

    private ComponentMapper<MovementComponent> movementComponentMap;
    private ComponentMapper<TransformComponent> posComponentMap;
    private ComponentMapper<BoundsComponent> boundMap;

    public MovementSystem()
    {
        super(Family.all(MovementComponent.class, TransformComponent.class,
                DebugComponent.class).get(), CONST.SYSTEM_PRIORITY_MOVEMENT);

        movementComponentMap = ComponentMapper.getFor(MovementComponent.class);
        posComponentMap = ComponentMapper.getFor(TransformComponent.class);
        boundMap = ComponentMapper.getFor(BoundsComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        TransformComponent pos = posComponentMap.get(entity);
        MovementComponent mov = movementComponentMap.get(entity);
        BoundsComponent bound = boundMap.get(entity);

        pos.Position.x += mov.Velocity.x * deltaTime;
        pos.Position.y += mov.Velocity.y * deltaTime;
        bound.Bound.x = pos.Position.x;
        bound.Bound.y = pos.Position.y;
    }
}
