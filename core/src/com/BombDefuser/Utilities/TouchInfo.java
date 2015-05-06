package com.BombDefuser.Utilities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Robin on 2015-05-02.
 */
public class TouchInfo {
    public Vector2 pos;
    public boolean isTouched;

    public TouchInfo(){
        pos = new Vector2();
        isTouched = false;
    }
}
