package com.reinemann.alex.fantasysoccer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

        Paint white = new Paint();
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.STROKE);
        white.setStrokeWidth(10);

        Paint background = new Paint();
        background.setColor(0x2a6613);
        background.setStyle(Paint.Style.STROKE);

        canvas.drawRect(50, 50, canvas.getWidth() - 50, canvas.getHeight() - 50, white);

        white.setStrokeWidth(5);

        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 150, white);
        canvas.drawLine(canvas.getWidth() / 2, 50, canvas.getWidth() / 2, canvas.getHeight() - 50, white);

        canvas.drawRect(20, canvas.getHeight() / 2 - 50, 50, canvas.getHeight() / 2 + 50, white);
        canvas.drawRect(50, canvas.getHeight() / 2 - 120, 100, canvas.getHeight() / 2 + 120, white);
        canvas.drawRect(50, canvas.getHeight() / 2 - 240, 250, canvas.getHeight() / 2 + 240, white);
        canvas.drawArc(200, canvas.getHeight() / 2 - 120, 300, canvas.getHeight() / 2 + 120, -90, 180, false, white);

        canvas.drawRect(canvas.getWidth() - 50, canvas.getHeight() / 2 - 50, canvas.getWidth() - 20, canvas.getHeight() / 2 + 50, white);
        canvas.drawRect(canvas.getWidth() - 100,canvas.getHeight()/2 - 120,canvas.getWidth() - 50,canvas.getHeight()/2 + 120,white);
        canvas.drawRect(canvas.getWidth() - 250,canvas.getHeight()/2 - 240,canvas.getWidth() - 50,canvas.getHeight()/2 + 240,white);
        canvas.drawArc(canvas.getWidth() - 300, canvas.getHeight() / 2 - 120, canvas.getWidth() - 200, canvas.getHeight() / 2 + 120,90,180,false,white);
    }
}