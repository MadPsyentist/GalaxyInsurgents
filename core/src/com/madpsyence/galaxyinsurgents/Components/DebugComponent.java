package com.madpsyence.galaxyinsurgents.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Lachie on 10/1/2016.
 */
public class DebugComponent implements Component
{
    private static int id = 0;
    public final int ID;
    public final String Name;

    public DebugComponent()
    {
        ID = id++;
        Name = "UnNamed";
    }

    public DebugComponent(String Name)
    {
        this.Name = Name;
        ID = id++;
    }
}
