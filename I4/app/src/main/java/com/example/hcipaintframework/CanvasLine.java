package com.example.hcipaintframework;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Paint;

public class CanvasLine implements CanvasShape {
    private PointF start;
    private PointF end;
    private Paint paint;

    /**
     * TO IMPLEMENT
     * Constructor for a Line object drawn on the canvas
     * @param mStart: the touch down point
     * @param mEnd: the touch up point
     * @param color: The integer based line color
     * @param brush: the integer based brush/line width
     */
    public CanvasLine(PointF mStart, PointF mEnd, int color, int brush) {
        //....
    }

    // TO IMPLEMENT: draw method
    @Override
    public void draw(Canvas canvas){
        //....
    }
}
