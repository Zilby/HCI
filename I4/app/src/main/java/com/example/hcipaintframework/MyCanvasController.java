package com.example.hcipaintframework;

import android.view.View;
import android.graphics.PointF;
import android.view.DragEvent;
import android.view.MotionEvent;

// Separated from MyCanvasView and handles the callbacks
public class MyCanvasController implements View.OnTouchListener{
    private PointF startPt;
    private PointF endPt;
    private boolean isDragOn;
    private boolean isNeedingPt;
    private MyCanvas_VM canvasVM;

    public MyCanvasController() {
        startPt = new PointF();
        endPt = new PointF();
        isNeedingPt = false;
        isDragOn = false;
    }

    /**
     * GIVEN
     * resetTouch resets the touch data from a fresh touch and drag event.
     */
    public void resetTouch(){
        isDragOn = false;
        isNeedingPt = false;
        startPt.set(0, 0);
        endPt.set(0, 0);
    }

    /**
     * TO IMPLEMENT
     * onTouch takes a view and a motion event and determines touch locations used
     * to create new shapes.
     * Recall that each shape needs two points and curves need a third point.
     * You will be using the MyCanvasView function "addShape" to generate a shape there
     * and to store it (MyCanvasView has access to the viewmodel).
     * @param v: View v should ONLY be the MyCanvasView object
     * @param event: The motion event. Should handle ACTION_UP, ACTION_DOWN, and ACTION_MOVE
     * @return true if the motion event is handled successfully.
     */
    public boolean onTouch(View v, MotionEvent event) {
        MyCanvasView view = (MyCanvasView) v;
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //....
                break;
            case MotionEvent.ACTION_DOWN:
                //....
                break;
            case MotionEvent.ACTION_MOVE:
                // Currently does nothing but could be used to dynamic drawing.
                break;
        }
        return true;
    }
}
