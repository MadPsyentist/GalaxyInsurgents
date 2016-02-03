package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.Events.InputEvents;
import com.madpsyence.galaxyinsurgents.Components.PlayerComponent;
import com.madpsyence.galaxyinsurgents.Components.MovementComponent;

/**
 * Created by Lachie on 9/1/2016.
 */
public class DirectionalInputSystem extends IteratingSystem implements Listener<String>
{
    private boolean moveLeft = false;
    private boolean moveRight = false;

    private ComponentMapper<MovementComponent> movementComponentMap;
    private ComponentMapper<PlayerComponent> playerComponentMap;

    public DirectionalInputSystem()
    {
        super(Family.all(MovementComponent.class, PlayerComponent.class).get(), CONST.SYSTEM_PRIORITY_INPUT + CONST.TERTIRY_SYSTEM);

        movementComponentMap = ComponentMapper.getFor(MovementComponent.class);
        playerComponentMap = ComponentMapper.getFor(PlayerComponent.class);
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        MovementComponent mov = movementComponentMap.get(entity);
        PlayerComponent play = playerComponentMap.get(entity);

        float speed = 0.0f;
        if(moveLeft)
            speed -= play.Speed;
        if(moveRight)
            speed += play.Speed;

        mov.Velocity.x = speed;
    }

    @Override
    public void receive(Signal<String> signal, String eventKey)
    {
        if(eventKey == InputEvents.Move_Left)
            moveLeft = true;
        if(eventKey == InputEvents.Move_Right)
            moveRight = true;
        if(eventKey == InputEvents.StopMove_Left)
            moveLeft = false;
        if(eventKey == InputEvents.StopMove_Right)
            moveRight = false;
    }
}
