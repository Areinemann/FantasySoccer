package com.reinemann.alex.fantasysoccer;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by Alex on 9/29/2015.
 */
public class SoccerFieldView extends SurfaceView {
    public SoccerFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);


    }
}
