package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.DebugComponent;
import com.madpsyence.galaxyinsurgents.Components.TextureComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

/**
 * Created by Lachie on 8/1/2016.
 */
public class RenderSystem extends SortedIteratingSystem
{
    private ComponentMapper<TextureComponent> textureComponentMap;
    private ComponentMapper<TransformComponent> transformComponentMap;
    private ComponentMapper<DebugComponent> dbMap;

    SpriteBatch batch;
    OrthographicCamera camera;

    Sprite theSprite;

    int rendercount = 0;

    public RenderSystem(SpriteBatch batch)
    {
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new RenderComparator(), 50);

        textureComponentMap = ComponentMapper.getFor(TextureComponent.class);
        transformComponentMap = ComponentMapper.getFor(TransformComponent.class);
        dbMap = ComponentMapper.getFor(DebugComponent.class);
        this.batch = batch;
        camera = new OrthographicCamera(CONST.FRUSTRUM_WIDTH, CONST.FRUSTRUM_HEIGHT);
        theSprite = new Sprite();
    }

    @Override
    public void update(float deltaTime)
    {
        rendercount = 0;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime)
    {
        rendercount++;
        TextureComponent tex = textureComponentMap.get(entity);
        TransformComponent pos = transformComponentMap.get(entity);
        DebugComponent db = dbMap.get(entity);

        System.out.println(db.Name + db.ID + " renderCount " + rendercount);

        batch.draw(tex.Texture, pos.Position.x - tex.TextureOrigin.x,
                pos.Position.y - tex.TextureOrigin.y,
                tex.TextureOrigin.x, tex.TextureOrigin.y,
                tex.Texture.getRegionWidth(), tex.Texture.getRegionHeight(),
                pos.Scale.x, pos.Scale.y,
                MathUtils.radiansToDegrees * pos.Rotation);
    }
}
