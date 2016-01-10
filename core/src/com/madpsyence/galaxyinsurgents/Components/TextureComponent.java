package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lachie on 8/1/2016.
 */
public class TextureComponent implements Component
{
    public TextureRegion Texture;
    public Vector2 TextureOrigin;

    public TextureComponent(TextureRegion texture, Vector2 textureOrigin)
    {
        this.Texture = texture;
        this.TextureOrigin = textureOrigin;
    }
}
