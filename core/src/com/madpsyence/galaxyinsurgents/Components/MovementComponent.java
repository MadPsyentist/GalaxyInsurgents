package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lachie on 9/1/2016.
 */
public class MovementComponent implements Component
{
    public Vector2 Velocity;

    public MovementComponent(Vector2 Velocity)
    {
        this.Velocity = Velocity;
    }

    public MovementComponent()
    {
        this.Velocity = Vector2.Zero;
    }
}
