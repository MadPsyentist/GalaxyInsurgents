package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lachie on 10/2/2016.
 */
public class EntityRemovalSystem implements Listener<Entity>
{
    private List<Entity> entitiesToRemove;
    private Engine engine;

    public EntityRemovalSystem(Signal<Entity> removalSignal, Engine engine)
    {
        this.engine = engine;
        removalSignal.add(this);
        entitiesToRemove = new ArrayList<Entity>();
    }

    public void process()
    {
        if(!entitiesToRemove.isEmpty())
        {
            for (Entity e : entitiesToRemove)
                engine.removeEntity(e);
            entitiesToRemove.clear();
        }
    }

    @Override
    public void receive(Signal<Entity> signal, Entity object)
    {
        entitiesToRemove.add(object);
    }
}
