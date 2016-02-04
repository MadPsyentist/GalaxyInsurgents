package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Components.GunComponent;
import com.madpsyence.galaxyinsurgents.Components.PlayerComponent;
import com.madpsyence.galaxyinsurgents.Events.InputEvents;

/**
 * Created by lachi on 4/02/2016.
 */
public class PlayerFireSystem extends IteratingSystem implements Listener<String>
{
    ComponentMapper<GunComponent> gunMap;
    boolean fire;

    public PlayerFireSystem()
    {
        super(Family.all(PlayerComponent.class, GunComponent.class).get(),
                CONST.SYSTEM_PRIORITY_INPUT + CONST.TERTIRY_SYSTEM);
        gunMap = ComponentMapper.getFor(GunComponent.class);
        fire = false;
    }

    @Override
    public void receive(Signal<String> signal, String event)
    {
        if(event == InputEvents.Fire)
            fire = true;
        else if(event == InputEvents.HoldFire)
            fire = false;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        if(fire)
            gunMap.get(entity).PullTrigger = true;
        else
            gunMap.get(entity).PullTrigger = false;
    }
}
