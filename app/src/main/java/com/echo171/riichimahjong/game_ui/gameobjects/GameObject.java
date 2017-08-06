package com.echo171.riichimahjong.game_ui.gameobjects;

import android.graphics.*;
import com.echo171.riichimahjong.game_ui.animations.GameObjectAnimator;

public class GameObject {
    private boolean isInitialized = false;
    private boolean isMoving = false;
    private RectF rect;
    private Bitmap texture;
    private Paint paint;
    private GameObjectAnimator gameObjectAnimator;

    public void initialize(){
        this.paint = new Paint();

        isInitialized = true;
    }

    public void draw(Canvas canvas){
        if (!isInitialized)
            initialize();

        if (isMoving){
            gameObjectAnimator.move();
            rect = gameObjectAnimator.getPositionRect();
        }

        canvas.drawBitmap(texture, new Rect(0, 0, texture.getWidth(), texture.getHeight()), rect, paint);
    }

    public void setTexture(Bitmap texture) {
        this.texture = texture;
    }

    public void setRect(RectF rect) {
        this.rect = rect;
    }

    public void setMoveTo(int x, int y, float speed){
        if (gameObjectAnimator == null)
            gameObjectAnimator = new GameObjectAnimator(this, (int) rect.left, (int) rect.top);

        gameObjectAnimator.setGoal(x, y, speed);
        isMoving = true;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void stopMoving() {
        isMoving = false;
        gameObjectAnimator = null;
    }
}
