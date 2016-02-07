package com.madpsyence.galaxyinsurgents.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

/**
 * Created by Lachie on 28/1/2016.
 */
public class Wall
{
    public static Entity Build(float posX, float posY, float width, float height, boolean isKillZone)
    {
        Entity entity = new Entity();
        Rectangle rect = new Rectangle(posX, posY, width, height);
        if(isKillZone)
            entity.add(new BoundsComponent(rect, EntityType.KillZone));
        else
            entity.add(new BoundsComponent(rect, EntityType.Wall));
        entity.add(new TransformComponent(posX, posY));

        return entity;
    }
}
