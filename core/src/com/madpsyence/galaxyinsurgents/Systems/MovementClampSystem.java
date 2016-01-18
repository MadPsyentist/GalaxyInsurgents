package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementClampComponent;
import com.madpsyence.galaxyinsurgents.Components.TransformComponent;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Lachie on 12/1/2016.
 */
public class MovementClampSystem extends EntitySystem implements Listener<CollisionEvent>
{
    private ComponentMapper<TransformComponent> transMap;
    private ImmutableArray<Entity> entities;
    private Queue<CollisionEvent> events;

    public MovementClampSystem()
    {
        this.priority = CONST.SYSTEM_PRIORITY_PHYSICS + CONST.TERTIRY_SYSTEM;
        events = new ArrayBlockingQueue<CollisionEvent>();
    }

    @Override
    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(MovementClampComponent.class, TransformComponent.class).get());
        transMap = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update( float deltaTime)
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

    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent object) {

    }
}
