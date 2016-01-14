package com.madpsyence.galaxyinsurgents;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpsyence.galaxyinsurgents.Components.BoundsComponent;
import com.madpsyence.galaxyinsurgents.Entities.Enemy;
import com.madpsyence.galaxyinsurgents.Entities.GameStage;
import com.madpsyence.galaxyinsurgents.Entities.Player;
import com.madpsyence.galaxyinsurgents.Input.KeyboardInputProcessor;
import com.madpsyence.galaxyinsurgents.Systems.DirectionalInputSystem;
import com.madpsyence.galaxyinsurgents.Systems.MovementClampSystem;
import com.madpsyence.galaxyinsurgents.Systems.MovementSystem;
import com.madpsyence.galaxyinsurgents.Systems.RenderSystem;
import com.madpsyence.galaxyinsurgents.Systems.debugSystem;

public class GalaxyInsurgentsGame extends Game
{
	Engine engine;

    Signal<String> InputEventsSignal;

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

        placeHolder = GameStage.Build((CONST.FRUSTUM_WIDTH / 2) * -1, (CONST.FRUSTUM_HEIGHT / 2) * -1);
		eng.addEntity(placeHolder);
        //eng.addEntity(Enemy.Build(0,(CONST.FRUSTUM_HEIGHT / 2)-25,EnemyType.UFO));

		eng.addSystem(new MovementClampSystem(placeHolder));
		eng.addSystem(new RenderSystem(new SpriteBatch()));
        eng.addSystem(new MovementSystem());
        eng.addSystem(new debugSystem());
        //eng.addSystem(new EnemyMovementSystem());

        DirectionalInputSystem inSys = new DirectionalInputSystem();
        InputEventsSignal.add(inSys);
        eng.addSystem(inSys);

		return eng;
	}
}
