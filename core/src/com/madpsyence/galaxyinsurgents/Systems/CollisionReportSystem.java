package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;

/**
 * Created by lachi on 3/02/2016.
 * System is used for debug purposes and should not be included in game release
 */
public class CollisionReportSystem implements Listener<CollisionEvent>
{
    ComponentMapper<BoundsComponent> boundsMap;

    public CollisionReportSystem()
    {
        boundsMap = ComponentMapper.getFor(BoundsComponent.class);
    }


    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent object)
    {
        BoundsComponent bou1 = boundsMap.get(object.EntityA);
        BoundsComponent bou2 = boundsMap.get(object.EntityB);

        System.out.println("Collision EntityA=" + bou1.Type.toString() + " EntityB=" + bou2.Type.toString());
    }
}
