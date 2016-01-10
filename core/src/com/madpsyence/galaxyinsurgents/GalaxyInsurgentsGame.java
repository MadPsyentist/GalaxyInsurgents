package com.madpsyence.galaxyinsurgents;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpsyence.galaxyinsurgents.Entities.Background;
import com.madpsyence.galaxyinsurgents.Entities.Enemy;
import com.madpsyence.galaxyinsurgents.Entities.Player;
import com.madpsyence.galaxyinsurgents.Input.KeyboardInputProcessor;
import com.madpsyence.galaxyinsurgents.Systems.DirectionalInputSystem;
import com.madpsyence.galaxyinsurgents.Systems.EnemyMovementSystem;
import com.madpsyence.galaxyinsurgents.Systems.MovementSystem;
import com.madpsyence.galaxyinsurgents.Systems.RenderSystem;

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
		eng.addEntity(Player.Build(0.0f, -100.0f));

		for(int j = 0; j < 5; j++)
			eng.addEntity(Enemy.Build((150 * j) - 450, (150 * 5), EnemyType.Vinny));
		EnemyType type = EnemyType.Minny;

		for(int i = 1; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
				eng.addEntity(Enemy.Build((150 * j) - 450, (150 * i), type));

			if(i%2 == 0)
				type = EnemyType.Tinny;
		}

        eng.addEntity(Background.Build((CONST.FRUSTRUM_WIDTH / 2) * -1, (CONST.FRUSTRUM_HEIGHT / 2) * -1));
		eng.addSystem(new RenderSystem(new SpriteBatch()));
        eng.addSystem(new MovementSystem());
        //eng.addSystem(new EnemyMovementSystem());

        DirectionalInputSystem inSys = new DirectionalInputSystem();
        InputEventsSignal.add(inSys);
        eng.addSystem(inSys);

		return eng;
	}
}
