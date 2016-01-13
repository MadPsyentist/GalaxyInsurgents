package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementClampComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;

/**
 * Created by Lachie on 12/1/2016.
 */
public class MovementClampSystem extends IteratingSystem
{
    private ComponentMapper<TransformComponent> transMap;

    private Rectangle boundry;

    public MovementClampSystem(Entity StageEntity)
    {
        super(Family.all(MovementClampComponent.class, TransformComponent.class).get(), 15);
        transMap = ComponentMapper.getFor(TransformComponent.class);
        this.boundry = StageEntity.getComponent(BoundsComponent.class).Bound;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime)
    {
        TransformComponent tran = transMap.get(entity);
        if(!boundry.contains(tran.Position.x, tran.Position.y))
        {
            System.out.println("Outside");
            if(tran.Position.x < boundry.getX())
                tran.Position.x = boundry.getX();
            else if(tran.Position.x > boundry.getX() + boundry.getWidth())
                tran.Position.x = boundry.getX() + boundry.getWidth();

            if(tran.Position.y < boundry.getY())
                tran.Position.y = boundry.getY();
            else if(tran.Position.x > boundry.getY() + boundry.getHeight())
               tran.Position.y = boundry.getY() + boundry.getHeight();
        }
        else
            System.out.println("Inside");
    }
}
