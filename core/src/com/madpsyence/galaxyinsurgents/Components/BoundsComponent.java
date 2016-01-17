package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.madpsyence.galaxyinsurgents.Entities.EntityType;

/**
 * Created by lachi on 11/01/2016.
 */
public class BoundsComponent implements Component
{
    public Rectangle Bound;
    public EntityType Type;

    public BoundsComponent(Rectangle Bound, EntityType Type)
    {
        this.Bound = Bound;
        this.Type = Type;
    }
}
