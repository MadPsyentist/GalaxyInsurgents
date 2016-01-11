package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by lachi on 11/01/2016.
 */
public class BoundsComponent implements Component
{
    public Rectangle BoundingBox;

    public BoundsComponent(Rectangle BoundingBox)
    {
        this.BoundingBox = BoundingBox;
    }
}
