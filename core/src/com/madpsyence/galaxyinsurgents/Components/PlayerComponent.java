package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Lachie on 9/1/2016.
 */
public class PlayerComponent implements Component
{
    public float Speed;
    public PlayerComponent(float Speed)
    {
        this.Speed = Speed;
    }
}
