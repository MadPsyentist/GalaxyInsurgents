package com.madpsyence.galaxyinsurgents.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.madpsyence.galaxyinsurgents.Components.EnemyComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementComponent;
import com.madpsyence.galaxyinsurgents.Components.TextureComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;
import com.madpsyence.galaxyinsurgents.EnemyType;

/**
 * Created by Lachie on 10/1/2016.
 */
public class Enemy
{
    private static boolean initilized = false;

    private static Texture TinnyTex;
    private static Texture MinnyTex;
    private static Texture VinnyTex;
    private static Texture UFOTex;
    private static TextureRegion sprite;

    private static void Initilize()
    {
        MinnyTex = new Texture(Gdx.files.internal("Alien1.png"));
        TinnyTex = new Texture(Gdx.files.internal("Alien2.png"));
        VinnyTex = new Texture(Gdx.files.internal("Alien3.png"));
        UFOTex = new Texture(Gdx.files.internal("Ufo.png"));
        initilized = true;
        sprite = new TextureRegion();
    }


    public static Entity Build(float posX, float posY, EnemyType type)
    {
        if(!initilized)
            Initilize();

        Texture spriteSheet;
        switch (type)
        {
            case Minny:
                spriteSheet = MinnyTex;
                break;
            case Tinny:
                spriteSheet = TinnyTex;
                break;
            case Vinny:
                spriteSheet = VinnyTex;
                break;
            case UFO:
                spriteSheet = UFOTex;
                break;
            default:
                spriteSheet = new Texture(Gdx.files.internal("Alien1.png"));
        }
        if(type != EnemyType.UFO)
            sprite = new TextureRegion(spriteSheet, 0, 0, spriteSheet.getWidth()/2,
                    spriteSheet.getHeight());
        else
            sprite = new TextureRegion(spriteSheet);

        Entity enemy = new Entity();
        enemy.add(new TextureComponent(sprite, new Vector2(0.0f, 0.0f)));
        enemy.add(new TransformComponent(new Vector3(posX, posY, 0.0f),
                new Vector2(0.35f, 0.35f), 0.0f));
        enemy.add(new MovementComponent(new Vector2(0.0f, 0.0f)));
        enemy.add(new EnemyComponent());

        return enemy;
    }
}
