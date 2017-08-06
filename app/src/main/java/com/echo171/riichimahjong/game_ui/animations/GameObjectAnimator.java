package com.echo171.riichimahjong.game_ui.animations;

import android.graphics.RectF;
import com.echo171.riichimahjong.game_ui.gameobjects.GameObject;

public class GameObjectAnimator {
    private GameObject object;
    private int x;
    private int y;
    private int ox;
    private int oy;
    private float speed;
    private float w;
    private float h;
    private boolean ended = false;
    private RectF positionRect;

    public GameObjectAnimator(GameObject object, int x, int y)
    {
        this.object = object;
        this.x = x;
        this.y = y;
    }

    public void setGoal(int ox, int oy, float speed)
    {
        this.ox = ox;
        this.oy = oy;
        this.speed = speed;
        //object.startMoving();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getOx() {
        return ox;
    }

    public int getOy() {
        return oy;
    }

    public float getSpeed() {
        return speed;
    }

    public void move()
    {
        int dx = Math.abs(ox - x);
        int dy = Math.abs(oy - y);
        if (dx == 0 && dy == 0)
        {
            object.stopMoving();
            ended = true;
        }

        float mx = Math.min(dx, speed);
        float my = Math.min(dy, speed);
        if (ox - x < 0)
            mx *= -1;

        if (oy - y < 0)
            my *= -1;

        x += mx;
        y += my;

        positionRect = new RectF(x, y, x + w, y + h);

    }

    public void setDims(float w, float h){
        this.w = w;
        this.h = h;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public boolean isEnded() {
        return ended;
    }

    public RectF getPositionRect() {
        return positionRect;
    }
}
