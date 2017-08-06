package com.echo171.riichimahjong.game_ui.controls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.echo171.riichimahjong.game_ui.GameInstance;

/**
 * Created by echo on 20/06/2017.
 */
public class Button {

    private String text;
    private int idleColor;
    private int activeColor;
    private boolean enabled;
    private boolean visible;
    private boolean active;
    private RectF placement;
    private ButtonType type;
    private Action action;
    private Canvas canvas;

    private RectF placementFinal = null;

    private Paint paint;

    public Button(Canvas canvas)
    {
        text = "Empty button";
        idleColor = Color.RED;
        activeColor = Color.GREEN;
        active = false;
        placement = new RectF(0, 0, 300, 32);
        type = ButtonType.SWITCH;
        paint = new Paint();
        this.canvas = canvas;
    }

    public Button() {
        text = "Empty button";
        idleColor = Color.RED;
        activeColor = Color.GREEN;
        active = false;
        placement = new RectF(0, 0, 300, 32);
        type = ButtonType.SWITCH;
        paint = new Paint();
    }

    public void setColors(int idleColor, int activeColor)
    {
        this.idleColor = idleColor;
        this.activeColor = activeColor;
    }

    public void setType(ButtonType type) {
        this.type = type;
    }

    public void setText(String text) {

        this.text = text;
    }

    public void setPlacement(RectF placement) {

        this.placement = placement;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void draw(Canvas canvas)
    {
        if (placementFinal == null)
        {
            placementFinal = new RectF();
            placementFinal.left =placement.left * canvas.getWidth();
            placementFinal.right =placement.right * canvas.getWidth();
            placementFinal.top =placement.top * canvas.getHeight();
            placementFinal.bottom =placement.bottom * canvas.getHeight();
        }

        paint.setColor((active?activeColor:idleColor));
        canvas.drawRect(placementFinal, paint);
        paint.setTextSize(32);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, placementFinal.centerX(), placementFinal.centerY() + paint.getTextSize()/2, paint);
    }

    public boolean handleTouch(MotionEvent event, GameInstance game)
    {
        float x = event.getX();
        float y = event.getY();
        //if (!visible)
        //	return false;

        if (event.getAction() == MotionEvent.ACTION_DOWN && placementFinal.contains(x, y))
        {
            active = !active;
            if (action != null)
                //TODO
                //action.execute(game, ???);

            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP && type == ButtonType.ACTIVE)
        {
            active = false;

            return true;
        }

        return false;
    }

    public void hide(){
        this.visible = false;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}