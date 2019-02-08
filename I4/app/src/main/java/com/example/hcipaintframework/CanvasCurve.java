package com.example.hcipaintframework;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.constraint.solver.widgets.Rectangle;

public class CanvasCurve implements CanvasShape {
    private Path curvePath;
    private Paint paint;

    public CanvasCurve(PointF start, PointF mid, PointF end, int color, int brush, boolean fill) {
        // Use the points start, mid and end as given but make a copy (Points are objects after all)
        PointF copyStart = new PointF(start.x, start.y);//ShapeFactory.getMinPt(mStart, mEnd);
        PointF copyEnd = new PointF(end.x, end.y);//ShapeFactory.getMaxPt(mStart, mEnd);

        // Curves are defined using a path. The path is defined as the
        // quadratic expression from start to end traversing the mid point.
        curvePath = new Path();
        curvePath.moveTo(copyStart.x, copyStart.y);
        curvePath.quadTo( mid.x, mid.y, copyEnd.x, copyEnd.y);

        // TO IMPLEMENT
        // Generate a paint object that you use to draw the curve.
        //....
    }

    /**
     * TO IMPLEMENT
     * draw will draw a curve (defined by curvePath) on the given canvas object
     * @param canvas The canvas to draw the curve on.
     */
    @Override
    public void draw(Canvas canvas){
        //....
    }
}
