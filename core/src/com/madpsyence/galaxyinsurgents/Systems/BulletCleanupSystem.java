package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Entities.EntityType;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;

import java.util.List;

/**
 * Created by Lachie on 7/2/2016.
 */
public class BulletCleanupSystem extends EntitySystem implements Listener<CollisionEvent>
{
    ComponentMapper<BoundsComponent> boundMap;

    Engine engine;

    List<Entity> bullets;

    public BulletCleanupSystem(Signal<CollisionEvent> collisionSignal)
    {

    }

    @Override
    public void addedToEngine(Engine engine)
    {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent event)
    {
        BoundsComponent bbA = boundMap.get(event.EntityA);
        BoundsComponent bbB = boundMap.get(event.EntityB);

        Entity entBullet;
        Entity entObject;

        if(bbA.Type == EntityType.PlayerBullet)
            processPlayerBullet(event.EntityA, event.EntityB);
        else if(bbB.Type == EntityType.PlayerBullet)
            processPlayerBullet(event.EntityB, event.EntityA);
        else if(bbA.Type == EntityType.EnemyBullet)
            processEnemyBullet(event.EntityA, event.EntityB);
        else if(bbB.Type == EntityType.EnemyBullet)
            processEnemyBullet(event.EntityB, event.EntityA);
    }

    private void processPlayerBullet(Entity bullet, Entity object)
    {

    }

    private void processEnemyBullet(Entity bullet, Entity object)
    {

    }
}
