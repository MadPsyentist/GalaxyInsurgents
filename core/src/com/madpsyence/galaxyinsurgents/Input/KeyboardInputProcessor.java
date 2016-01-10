package com.madpsyence.galaxyinsurgents.Input;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.madpsyence.galaxyinsurgents.Events.InputEvents;

/**
 * Created by Lachie on 9/1/2016.
 */
public class KeyboardInputProcessor implements InputProcessor
{
    private Signal<String> InputEventsSignal;

    public KeyboardInputProcessor(Signal<String> InputEventsSignal)
    {
        this.InputEventsSignal = InputEventsSignal;
    }

    public boolean keyDown (int keycode)
    {
        if(keycode == Input.Keys.LEFT)
        {
            InputEventsSignal.dispatch(InputEvents.Move_Left);
            System.out.println("Left pressed");
            return true;
        }
        if(keycode == Input.Keys.RIGHT)
        {
            InputEventsSignal.dispatch(InputEvents.Move_Right);
            System.out.println("Right pressed");
            return true;
        }
        if(keycode == Input.Keys.SPACE)
        {
            InputEventsSignal.dispatch(InputEvents.Fire);
            return true;
        }
        return false;
    }

    public boolean keyUp (int keycode)
    {
        if(keycode == Input.Keys.LEFT)
        {
            InputEventsSignal.dispatch(InputEvents.StopMove_Left);
            System.out.println("Left released");
            return true;
        }
        if(keycode == Input.Keys.RIGHT)
        {
            InputEventsSignal.dispatch(InputEvents.StopMove_Right);
            System.out.println("Right released");
            return true;
        }
        if(keycode == Input.Keys.SPACE)
        {
            InputEventsSignal.dispatch(InputEvents.HoldFire);
            return true;
        }
        return false;
    }

    public boolean keyTyped (char character)
    {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button)
    {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button)
    {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer)
    {
        return false;
    }

    public boolean mouseMoved (int x, int y)
    {
        return false;
    }

    public boolean scrolled (int amount)
    {
        return false;
    }
}
