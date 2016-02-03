package com.madpsyence.galaxyinsurgents;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpsyence.galaxyinsurgents.Entities.*;
import com.madpsyence.galaxyinsurgents.Events.CollisionEvent;
import com.madpsyence.galaxyinsurgents.Input.KeyboardInputProcessor;
import com.madpsyence.galaxyinsurgents.Systems.CollisionReportSystem;
import com.madpsyence.galaxyinsurgents.Systems.CollisionSystem;
import com.madpsyence.galaxyinsurgents.Systems.DebugRender;
import com.madpsyence.galaxyinsurgents.Systems.DirectionalInputSystem;
import com.madpsyence.galaxyinsurgents.Systems.EnemyMovementSystem;
import com.madpsyence.galaxyinsurgents.Systems.MoveBoundsSystem;
import com.madpsyence.galaxyinsurgents.Systems.MovementClampSystem;
import com.madpsyence.galaxyinsurgents.Systems.MovementSystem;
import com.madpsyence.galaxyinsurgents.Systems.RenderSystem;

public class GalaxyInsurgentsGame extends Game
{
	Engine engine;

    Signal<String> InputEventsSignal;
	Signal<CollisionEvent> collisionEventSignal;

	@Override
	public void create ()
	{
        InitializeEventSignals();
		engine = InitializeEngine();
        Gdx.input.setInputProcessor(new KeyboardInputProcessor(InputEventsSignal));
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0.15f, 0.09f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(Gdx.graphics.getDeltaTime());
	}

    private void InitializeEventSignals()
    {
        InputEventsSignal = new Signal<String>();
		collisionEventSignal = new Signal<CollisionEvent>();
    }

	private Engine InitializeEngine()
	{
		Engine eng = new Engine();
		Entity placeHolder;
		eng.addEntity(Player.Build(0.0f, -300.0f));

		for(int j = 0; j < 5; j++)
			eng.addEntity(Enemy.Build((50 * j) - 140, (50 * 5), EnemyType.Vinny));
		EnemyType type = EnemyType.Minny;

		for(int i = 1; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
				eng.addEntity(Enemy.Build((50 * j) - 140, (50 * i), type));

			if(i%2 == 0)
				type = EnemyType.Tinny;
		}
		eng.addEntity(Wall.Build(-250, -319, 20, 640));
		eng.addEntity(Wall.Build(230, -319, 20, 640));
        //eng.addEntity(Enemy.Build(0,(CONST.FRUSTUM_HEIGHT / 2)-25,EnemyType.UFO));

		eng.addSystem(new MovementClampSystem());
		eng.addSystem(new RenderSystem(new SpriteBatch()));
        eng.addSystem(new MovementSystem());

		DirectionalInputSystem dirSys = new DirectionalInputSystem();
		InputEventsSignal.add(dirSys);
		eng.addSystem(dirSys);

		EnemyMovementSystem enemMov = new EnemyMovementSystem();
		collisionEventSignal.add(enemMov);
		eng.addSystem(enemMov);
		eng.addSystem(new CollisionSystem(collisionEventSignal));
		eng.addSystem(new MoveBoundsSystem());
		eng.addSystem(new DebugRender());

        DirectionalInputSystem inSys = new DirectionalInputSystem();
        InputEventsSignal.add(inSys);
        eng.addSystem(inSys);
		collisionEventSignal.add(new CollisionReportSystem());

		return eng;
	}
}
