package com.madpsyence.galaxyinsurgents.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.madpsyence.galaxyinsurgents.Components.DebugComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementComponent;
import com.madpsyence.galaxyinsurgents.Components.TextureComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

/**
 * Created by Lachie on 10/1/2016.
 */
public class Background
{
    public static Entity Build(float posX, float posY)
    {
        Entity background = new Entity();
        background.add(new TextureComponent(new TextureRegion(new Texture(Gdx.files.internal("Background.png"))),
                new Vector2(0.0f, 0.0f)));
        background.add(new TransformComponent(new Vector3(posX,posY,10.0f), new Vector2(0.6f, 0.6f), 0.0f));
        //Add our debug component
        background.add(new DebugComponent("Background"));

        return background;
    }

}
