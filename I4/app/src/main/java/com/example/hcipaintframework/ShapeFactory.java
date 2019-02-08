package com.example.hcipaintframework;
import android.graphics.PointF;
public class ShapeFactory {

    /**
     * TO IMPLEMENT
     * makeShape generates a new CanvasShape object based on the provided shape type,
     * touch points, bush size, color and whether the shape should be filled.
     * Simply call the constructor from each shape class.
     * @param shape
     * @param mouseStart
     * @param mouseMid
     * @param mouseEnd
     * @param brush
     * @param color
     * @param fill
     * @return
     */
    public static CanvasShape makeShape(@PaintState_VM.Shape int shape, PointF mouseStart,
                                        PointF mouseMid, PointF mouseEnd,
                                        int brush, int color, boolean fill) {
        if( mouseStart == null || mouseEnd == null) {
            return null;
        }
        //....;
        return null; // Just to compile.
    }

    /**
     * GIVEN
     * Returns a new point with values equal to the smallest x and smallest y value
     * from the provided points.
     * @param mStart
     * @param mEnd
     * @return PointF with values strictly from mStart and mEnd
     **/
    public static PointF getMinPt(PointF mStart, PointF mEnd) {
        PointF retVal = new PointF(mStart.x, mStart.y);
        if(mStart.x > mEnd.x){
            retVal.x = mEnd.x;
        }
        if(mStart.y > mEnd.y) {
            retVal.y = mEnd.y;
        }
        return retVal;
    }

    /**
     * GIVEN
     * Returns a new point with values equal to the largest x and largest y value
     * from the provided points.
     * @param mStart
     * @param mEnd
     * @return PointF with values strictly from mStart and mEnd
     **/
    public static PointF getMaxPt(PointF mStart, PointF mEnd) {
        PointF retVal = new PointF(mStart.x, mStart.y);
        if(mStart.x < mEnd.x){
            retVal.x = mEnd.x;
        }
        if(mStart.y < mEnd.y) {
            retVal.y = mEnd.y;
        }
        return retVal;
    }

}
