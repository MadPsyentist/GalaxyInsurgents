package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;

/**
 * Created by lachi on 3/02/2016.
 * System is used for debug purposes and should not be included in game release
 */
public class DebugRender extends IteratingSystem
{
    ComponentMapper<BoundsComponent> boundsMap;
    OrthographicCamera cam;
    ShapeRenderer shapeRenderer;

    public DebugRender()
    {
        super(Family.all(BoundsComponent.class).get(), CONST.SYSTEM_PRIORITY_RENDERER - CONST.SECONDARY_SYSTEM);
        cam = new OrthographicCamera(CONST.FRUSTUM_WIDTH, CONST.FRUSTUM_HEIGHT);
        boundsMap = ComponentMapper.getFor(BoundsComponent.class);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float deltaTime)
    {
        cam.update();
        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);
        super.update(deltaTime);
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        BoundsComponent bounds = boundsMap.get(entity);
        shapeRenderer.rect(bounds.Bound.x, bounds.Bound.y, bounds.Bound.width, bounds.Bound.height);
    }
}
