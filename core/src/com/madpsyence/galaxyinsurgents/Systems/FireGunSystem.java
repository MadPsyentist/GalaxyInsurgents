package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.GunComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;
import com.madpsyence.galaxyinsurgents.Entities.Bullet;

/**
 * Created by Lachie on 14/1/2016.
 */
public class FireGunSystem extends IteratingSystem
{
    private ComponentMapper<GunComponent> gunMap;
    private ComponentMapper<TransformComponent> posMap;
    private Engine engine;

    public FireGunSystem(Engine engine, int Priority)
    {
        super(Family.all(GunComponent.class, TransformComponent.class).get(), Priority);
        gunMap = ComponentMapper.getFor(GunComponent.class);
        posMap = ComponentMapper.getFor(TransformComponent.class);
        this.engine = engine;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime)
    {
        TransformComponent pos = posMap.get(entity);
        GunComponent gun = gunMap.get(entity);
        if(gun.PullTrigger && gun.CanFire)
        {
            engine.addEntity(Bullet.BuildFromGun(pos.Position, gun));
            gun.CanFire = false;
        }
        else
            gun.PullTrigger = false;

    }
}
