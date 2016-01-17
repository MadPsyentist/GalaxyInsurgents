package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

/**
 * Created by Lachie on 15/1/2016.
 */
public class MoveBoundsSystem extends IteratingSystem
{
    ComponentMapper<TransformComponent> posMap;
    ComponentMapper<BoundsComponent> boundsMap;

    public MoveBoundsSystem()
    {
        super(Family.all(TransformComponent.class, MovementComponent.class, BoundsComponent.class).get());
        posMap = ComponentMapper.getFor(TransformComponent.class);
        boundsMap = ComponentMapper.getFor(BoundsComponent.class);

    }

    public void processEntity(Entity entity, float deltaTime)
    {
        TransformComponent pos = posMap.get(entity);
        BoundsComponent bound = boundsMap.get(entity);

        bound.Bound.setPosition(pos.Position.x, pos.Position.y);
    }
}
