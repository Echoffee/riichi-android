package com.echo171.riichimahjong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CImageView extends View {
    private Drawer drawer;
    private boolean isInitialized;
    private static Handler viewHandler;
    private static Runnable updateView;
    private static Rect redrawRect;

    //private static LinkedList<AnimatedObject> animatedObjects;

    public CImageView(Context context) {
        super(context);
    }

    public CImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initialize()
    {
        if (!isHardwareAccelerated())
            setLayerType(View.LAYER_TYPE_HARDWARE, null);

        viewHandler = new Handler();
        updateView = new Runnable(){
            @Override
            public void run(){
                invalidate();
                viewHandler.postDelayed(updateView, 17);
            }
        };

        //animatedObjects = new LinkedList<>();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawer != null)
            if (!isInitialized)
            {
                drawer.initialize(canvas);
                isInitialized = true;
            }

        drawer.draw(canvas);
        //handleAnimations(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (drawer == null || !isInitialized)
            return false;

        if (drawer.handleTouch(event)){
            invalidate();
        }

        return true;
    }

    /*public void handleAnimations(Canvas canvas)
    {
        if (animatedObjects.size() == 0)
        {
            viewHandler.removeCallbacks(updateView);
            return;
        }

        for(int i = 0; i < animatedObjects.size(); i++)
        {
            AnimatedObject a = animatedObjects.get(i);
            if (!a.isEnded()) {
                a.move();
                canvas.drawBitmap(a.getBitmap(), a.getBitmapRect(), a.getPositionRect(), null);
            }else{
                animatedObjects.remove(a);
                i--;
            }
        }
    }*/

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }
/*
    public static void addAnimatedObject(AnimatedObject a)
    {
        if (animatedObjects.size() == 0)
            viewHandler.post(updateView);

        animatedObjects.add(a);
    }*/
}