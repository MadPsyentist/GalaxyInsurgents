package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

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

    public GunComponent(Vector2 BulletVelocity, Vector2 BulletOrigin, float ReloadTime)
    {
        this.BulletVelocity = BulletVelocity;
        this.BulletOrigin = BulletOrigin;
        this.ReloadTime = ReloadTime;
        elapsedTime = 0.0f;
        CanFire = true;
        PullTrigger = false;
    }
}
