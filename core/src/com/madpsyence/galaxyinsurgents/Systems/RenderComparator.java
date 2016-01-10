package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

import java.util.Comparator;

/**
 * Created by Lachie on 8/1/2016.
 */
public class RenderComparator implements Comparator<Entity>
{
    private ComponentMapper<TransformComponent> transformComponentMap;

    public RenderComparator()
    {
        transformComponentMap = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity e1, Entity e2)
    {
        return (int)Math.signum(transformComponentMap.get(e2).Position.z -
                transformComponentMap.get(e1).Position.z);
    }
}
