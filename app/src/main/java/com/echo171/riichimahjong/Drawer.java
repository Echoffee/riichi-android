package com.echo171.riichimahjong;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import com.echo171.riichimahjong.game_ui.game.GameDrawer;

public class Drawer {
    private final Resources resources;
    private GameDrawer gameDrawer;

    public Drawer(Resources resources){
        this.resources = resources;
    }

    public void initialize(Canvas canvas) {
        gameDrawer = new GameDrawer(resources);
        gameDrawer.initialize(canvas);
    }

    public void draw(Canvas canvas) {
        gameDrawer.draw(canvas);
    }

    public boolean handleTouch(MotionEvent event) {
        return false;
    }
}
