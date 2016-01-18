package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;

import java.util.Map;

/**
 * Created by Lachie on 17/1/2016.
 */
public class CollisionSystem extends EntitySystem
{
    private Signal<CollisionEvent> CollisionEventSignal;

    private ComponentMapper<BoundsComponent> boundsMap;

    private ImmutableArray<Entity> entities;

    public CollisionSystem(Signal<CollisionEvent> CollisionEventSignal)
    {
        this.CollisionEventSignal = CollisionEventSignal;
        this.priority = CONST.SYSTEM_PRIORITY_PHYSICS;
    }

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(BoundsComponent.class).get());
        boundsMap = ComponentMapper.getFor(BoundsComponent.class);
    }

    public void update(float deltaTime)
    {
        for(int i = 0; i < (entities.size() - 1); i++)
        {
            Entity entityA = entities.get(i);
            BoundsComponent BA = boundsMap.get(entityA);
            for(int j = i+1; j < entities.size(); j++)
            {
                Entity entityB = entities.get(j);
                BoundsComponent BB = boundsMap.get(entityB);

                if(BA.Bound.overlaps(BB.Bound))
                    CollisionEventSignal.dispatch(new CollisionEvent(entityA, entityB));
            }
        }

    }
}
