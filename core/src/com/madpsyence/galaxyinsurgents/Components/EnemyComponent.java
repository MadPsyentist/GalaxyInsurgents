package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Lachie on 10/1/2016.
 */
public class EnemyComponent implements Component
{
    public float Speed;

    public EnemyComponent(float Speed)
    {
        this.Speed = Speed;
    }

    public EnemyComponent()
    {
        Speed = 100.0f;
    }

}
