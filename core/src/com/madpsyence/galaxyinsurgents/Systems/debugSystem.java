package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.Components.PlayerComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

/**
 * Created by Lachie on 13/1/2016.
 */
public class debugSystem extends IteratingSystem
{
    ComponentMapper<TransformComponent> tMap;

    public debugSystem()
    {
        super(Family.all(PlayerComponent.class, TransformComponent.class).get());
        tMap = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        System.out.println("player pos x:" + tMap.get(entity).Position.x +
            " y:" + tMap.get(entity).Position.y);
    }
}
