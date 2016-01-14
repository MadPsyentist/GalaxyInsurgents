package com.madpsyence.galaxyinsurgents.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.GunComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementComponent;
import com.madpsyence.galaxyinsurgents.Components.TextureComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

/**
 * Created by Lachie on 10/1/2016.
 */
public class Bullet
{
    public static Entity Build(float posX, float posY)
    {
        Entity bullet = new Entity();
        bullet.add(new TransformComponent(posX, posY));
        bullet.add(new MovementComponent());
        bullet.add(new TextureComponent(new TextureRegion(new Texture(Gdx.files.internal("MightyShot.png"))),
                new Vector2(1.0f, 1.0f)));

        bullet.add(new BoundsComponent(new Rectangle(posX, posY, 20, 20)));
        return bullet;
    }

    public static Entity BuildFromGun(Vector3 EntityPosition, GunComponent gun)
    {
        Entity bullet = new Entity();

        bullet.add(new TransformComponent(EntityPosition.x + gun.BulletOrigin.x,
                EntityPosition.y + gun.BulletOrigin.y));

        bullet.add(new MovementComponent(gun.BulletVelocity.cpy()));

        bullet.add(new TextureComponent(new TextureRegion(
                new Texture(Gdx.files.internal("MightyShot.png"))), new Vector2(1.0f, 1.0f)));

        bullet.add(new BoundsComponent(new Rectangle(EntityPosition.x + gun.BulletOrigin.x,
                EntityPosition.y + gun.BulletOrigin.y, 20, 20)));

        return bullet;
    }
}
