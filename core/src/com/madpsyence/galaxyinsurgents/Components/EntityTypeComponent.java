package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;
import com.madpsyence.galaxyinsurgents.Entities.EntityType;

/**
 * Created by Lachie on 10/1/2016.
 */
public class EntityTypeComponent implements Component
{
    private static int idPool = 0;

    public int ID;
    EntityType Type;

    public EntityTypeComponent(EntityType Type)
    {
        this.Type = Type;
        ID = idPool++;
    }
}
