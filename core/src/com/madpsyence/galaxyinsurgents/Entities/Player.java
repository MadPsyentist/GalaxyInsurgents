package com.madpsyence.galaxyinsurgents.Entities;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.madpsyence.galaxyinsurgents.Components.*;

import java.util.List;

/**
 * Created by Lachie on 10/1/2016.
 */
public class Player
{
    public static Entity Build(float posX, float posY)
    {
        TextureRegion sprite = new TextureRegion(new Texture(Gdx.files.internal("Ship.png")));
        Entity player = new Entity();

        player.add(new TextureComponent(sprite, Vector2.Zero));
        player.add(new TransformComponent(new Vector3(posX, posY, 0.0f),
                new Vector2(1.0f, 1.0f), 0.0f));
        player.add(new MovementComponent());
        player.add(new PlayerComponent(300.0f));
        //Add our debug component
        player.add(new DebugComponent("Player"));

        return player;
    }
}
