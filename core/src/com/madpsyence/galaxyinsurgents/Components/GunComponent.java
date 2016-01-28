package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.madpsyence.galaxyinsurgents.Entities.EntityType;

/**
 * Created by Lachie on 14/1/2016.
 */
public class GunComponent implements Component
{
    public Vector2 BulletVelocity;
    public Vector2 BulletOrigin;
    public float ReloadTime;
    public float elapsedTime;
    public boolean CanFire;
    public boolean PullTrigger;
    public EntityType BulletType;

    public GunComponent(Vector2 BulletVelocity, Vector2 BulletOrigin,
                        float ReloadTime, EntityType BulletType)
    {
        this.BulletVelocity = BulletVelocity;
        this.BulletOrigin = BulletOrigin;
        this.ReloadTime = ReloadTime;
        this.BulletType = BulletType;
        elapsedTime = 0.0f;
        CanFire = true;
        PullTrigger = false;
    }
}
