package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.Components.EnemyComponent;
import com.madpsyence.galaxyinsurgents.Components.GunComponent;

import java.util.Random;

/**
 * Created by Lachie on 29/2/2016.
 */
public class EnemyFireSystem extends IteratingSystem
{
    private float nextShot;
    private float elapsed;
    private Random rng;
    private int count;
    private int[] firingShips;
    ComponentMapper<GunComponent> gunMap;

    private int entityCount;

    public EnemyFireSystem(int priority)
    {
        super(Family.all(GunComponent.class, EnemyComponent.class).get());
        this.priority = priority;
        rng = new Random();
        gunMap = ComponentMapper.getFor(GunComponent.class);
        firingShips = new int[2];
        nextShot = 0.0f;
        elapsed = 0.0f;
    }

    @Override
    public void update(float deltaTime)
    {
        entityCount = getEntities().size();

        count = 0;
        elapsed += deltaTime;
        if(entityCount > 0 && elapsed > nextShot)
        {
            nextShot = rng.nextFloat();
            elapsed = 0.0f;
            int shots = rng.nextInt(10);
            if(entityCount > 0) {
                while (shots >= entityCount)
                    shots = rng.nextInt(10);
                firingShips = new int[shots];
            }

            for(int i = 0; i < shots; i++)
                firingShips[i] = rng.nextInt(entityCount);

            super.update(deltaTime);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        for(int i: firingShips)
            if(i == count)
                gunMap.get(entity).PullTrigger = true;
        count++;
    }
}
