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
import com.madpsyence.galaxyinsurgents.Systems.CollisionSystem;
import com.madpsyence.galaxyinsurgents.Systems.DebugRender;
import com.madpsyence.galaxyinsurgents.Systems.DirectionalInputSystem;
import com.madpsyence.galaxyinsurgents.Systems.EnemyMovementSystem;
import com.madpsyence.galaxyinsurgents.Systems.EntityRemovalSystem;
import com.madpsyence.galaxyinsurgents.Systems.FireGunSystem;
import com.madpsyence.galaxyinsurgents.Systems.MoveBoundsSystem;
import com.madpsyence.galaxyinsurgents.Systems.MovementClampSystem;
import com.madpsyence.galaxyinsurgents.Systems.MovementSystem;
import com.madpsyence.galaxyinsurgents.Systems.PlayerBulletCollisionSystem;
import com.madpsyence.galaxyinsurgents.Systems.PlayerFireSystem;
import com.madpsyence.galaxyinsurgents.Systems.ReloadGunsSystem;
import com.madpsyence.galaxyinsurgents.Systems.RenderSystem;

public class GalaxyInsurgentsGame extends Game
{
	Engine engine;
	EntityRemovalSystem eRemov;

    Signal<String> InputEventsSignal;
	Signal<CollisionEvent> collisionEventSignal;
	Signal<Entity> EntityRemovalSignal;

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
		eRemov.process();
	}

    private void InitializeEventSignals()
    {
        InputEventsSignal = new Signal<String>();
		collisionEventSignal = new Signal<CollisionEvent>();
		EntityRemovalSignal = new Signal<Entity>();
    }

	private Engine InitializeEngine()
	{
		Engine eng = new Engine();

		// Add Entities to the engine
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
		eng.addEntity(Wall.Build(-280, -319, 50, 640, false));
		eng.addEntity(Wall.Build(230, -319, 50, 640, false));

		// KillZones to mop up stray bullets
		eng.addEntity(Wall.Build(-280, -400, 550, 50, true));
		eng.addEntity(Wall.Build(-280, 400, 550, 50, true));

		// Create systems
		DirectionalInputSystem dirIn = new DirectionalInputSystem(InputEventsSignal, 1);
		EnemyMovementSystem eneMov = new EnemyMovementSystem(2);
		ReloadGunsSystem reload = new ReloadGunsSystem(3);
		FireGunSystem fire = new FireGunSystem(eng, 4);
		PlayerFireSystem playFire = new PlayerFireSystem(5);
		MovementSystem mov = new MovementSystem(6);
		MoveBoundsSystem movBound = new MoveBoundsSystem(7);
		CollisionSystem colisSys = new CollisionSystem(collisionEventSignal, 8);
		MovementClampSystem movClamp = new MovementClampSystem(9);
		RenderSystem render = new RenderSystem(new SpriteBatch(), 10);
		DebugRender debugRend = new DebugRender(11);
		PlayerBulletCollisionSystem plyColSys = new PlayerBulletCollisionSystem(EntityRemovalSignal, collisionEventSignal, 9 );

		eRemov = new EntityRemovalSystem(EntityRemovalSignal, eng);

		// Add listener systems to their respective Signals
		collisionEventSignal.add(movClamp);
		collisionEventSignal.add(eneMov);
		InputEventsSignal.add(playFire);

		// add systems to the engine
		eng.addSystem(dirIn);
		eng.addSystem(eneMov);
		eng.addSystem(reload);
		eng.addSystem(fire);
		eng.addSystem(playFire);
		eng.addSystem(mov);
		eng.addSystem(movBound);
		eng.addSystem(colisSys);
		eng.addSystem(movClamp);
		eng.addSystem(render);
		eng.addSystem(debugRend);
		eng.addSystem(plyColSys);

		return eng;
	}
}
