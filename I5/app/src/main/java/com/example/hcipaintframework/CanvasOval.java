package com.example.hcipaintframework;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class CanvasOval implements CanvasShape {
    private RectF bound;
    private Paint paint;

    /**
     * TO IMPLEMENT
     * CanvasOval constructor makes a new oval object.
     * Note that the bound needs to be calculated by finding the minimum
     * and maximum x and y coordinates from mStart and mEnd. Also note that
     * ShapeFactory may be able to help you.
     * @param mStart: the touch down event point
     * @param mEnd: the touch up event point
     * @param color: The color of the oval (integer)
     * @param brush: the width of the oval outline
     * @param fill: boolean whether the oval is filled in or not.
     */
    public CanvasOval(PointF mStart, PointF mEnd, int color, int brush, boolean fill) {
        bound = new RectF(mStart.x, mStart.y, mEnd.x, mEnd.y);
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(brush);
        paint.setStyle(fill ? Paint.Style.FILL_AND_STROKE : Paint.Style.STROKE);
    }

    // TO IMPLEMENT: draw method
    @Override
    public void draw(Canvas canvas){
        canvas.drawOval(bound, paint);
    }
}
