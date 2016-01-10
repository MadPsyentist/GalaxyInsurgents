package com.madpsyence.galaxyinsurgents.Events;

import com.badlogic.ashley.core.Entity;

/**
 * Created by Lachie on 10/1/2016.
 */
public class CollisionEvent
{
    private static int idPool = 1;
    public int ID;
    public Entity EntityA;
    public Entity EntityB;

    public CollisionEvent(Entity EntityA, Entity EntityB)
    {
        ID = idPool++;
        this.EntityA = EntityA;
        this.EntityB = EntityB;
    }
}
