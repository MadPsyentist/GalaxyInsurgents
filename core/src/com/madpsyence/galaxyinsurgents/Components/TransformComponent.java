package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Lachie on 8/1/2016.
 */
public class TransformComponent implements Component
{
    public Vector3 Position;
    public Vector2 Scale;
    public float Rotation;

    public TransformComponent(Vector3 position, Vector2 scale, float rotation)
    {
        this.Position = position;
        this.Scale = scale;
        this.Rotation = rotation;
    }

    public TransformComponent(Vector3 position, Vector2 scale)
    {
        this.Position = position;
        this.Scale = scale;
        Rotation = 0.0f;
    }

    public TransformComponent(Vector3 position)
    {
        this.Position = position;
        Scale = new Vector2(1.0f, 1.0f);
        Rotation = 0.0f;
    }

    public TransformComponent(float posX, float posY)
    {
        Position = new Vector3(posX, posY, 0.0f);
        Scale = new Vector2(1.0f, 1.0f);
        Rotation = 0.0f;
    }

    public TransformComponent()
    {
        Position = new Vector3(0.0f, 0.0f, 0.0f);
        Scale = new Vector2(1.0f, 1.0f);
        Rotation = 0.0f;
    }
}
