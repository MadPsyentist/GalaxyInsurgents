package com.madpsyence.galaxyinsurgents.Systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.madpsyence.galaxyinsurgents.Events.SoundEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lachie on 28/2/2016.
 */
public class SoundSystem extends EntitySystem implements Listener<String>
{
    private List<String> soundsToPlay;
    private Sound shotSound;
    private Sound hitSound;

    public SoundSystem(Signal<String> SoundSignal)
    {
        SoundSignal.add(this);
        soundsToPlay = new ArrayList<String>();
        shotSound = Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("invaderkilled.wav"));
    }

    @Override
    public void update(float deltaTime)
    {
        boolean playHit = false;
        boolean playShot = false;

        for (String s: soundsToPlay)
            if (s == SoundEvents.Hit)
                playHit = true;
            else if (s == SoundEvents.Fire)
                playShot = true;

        if(playHit)
            hitSound.play();
        if(playShot);
            hitSound.play();
    }

    @Override
    public void receive(Signal<String> signal, String object)
    {
        soundsToPlay.add(object);
    }
}
