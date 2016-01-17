package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.GunComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

/**
 * Created by Lachie on 14/1/2016.
 */
public class ReloadGunsSystem extends IteratingSystem
{
    private ComponentMapper<GunComponent> gunMap;

    public ReloadGunsSystem(Engine engine)
    {
        super(Family.all(GunComponent.class).get(), CONST.SYSTEM_PRIORITY_LOGIC);
        gunMap = ComponentMapper.getFor(GunComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime)
    {
        GunComponent gun = gunMap.get(entity);
        if(!gun.CanFire)
        {
            gun.elapsedTime += deltaTime;
            if(gun.elapsedTime > gun.ReloadTime)
            {
                gun.CanFire = true;
                gun.elapsedTime = 0.0f;
            }
        }
    }
}
