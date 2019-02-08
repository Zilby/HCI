package com.example.hcipaintframework;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.Observer;
import android.graphics.PointF;
import android.view.View;


/**
 * MyCanvasView is a view object that acts as our drawing canvas.
 * Notice it knows about the viewmodel (where the shapes are stored),
 * and the paint state (to know what the controls are set to).
 * MyCanvasController is generated with each new view.
 */
public class MyCanvasView extends View {
    private PaintState_VM model;
    private MyCanvas_VM canvasModel;

    public MyCanvasView(Context context) {
        super(context);
        MyCanvasController controller = new MyCanvasController();
        this.setOnTouchListener(controller);
    }

    public MyCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        MyCanvasController controller = new MyCanvasController();
        this.setOnTouchListener(controller);
    }


    public void setCanvasVM(MyCanvas_VM cvm) {
        canvasModel = cvm;
    }
    public void setStateVM(PaintState_VM pvm) {
        model = pvm;
    }


    /**
     * Given
     * addShape takes a start, end and (optional) guide point
     * and generates a shape based on the current paint state.
     * @param startPt start point from the drag operation (ACTION_DOWN)
     * @param endPt end point from the drag operation (ACTION_UP)
     * @param guidePt point used for curves but null for other shapes (second ACTION_UP event)
     * @return the generated shape based on the paint state. If a curve
     * with no guide point or an error occurs then return null.
     */
    public CanvasShape addShape(PointF startPt, PointF endPt, PointF guidePt){
        if((guidePt == null) && (model.getLiveData().getCurrShape() == PaintState_VM.DCURVE)) {
            return null;
        }
        CanvasShape shape = ShapeFactory.makeShape( model.getLiveData().getCurrShape(), startPt, guidePt, endPt,
                model.getLiveData().getBrushSize(), model.getLiveData().getbColor(),
                model.getLiveData().isFillShape());
        if(shape == null) {
            return shape;
        }
        canvasModel.addShape(shape);
        invalidate();
        return shape;
    }

    /**
     * TO IMPLEMENT
     * Clear removes all shapes from the canvas.
     * This means the image is not valid any more....or invalidated
     */
    public void clear() {
        //....
    }

    /**
     * TO IMPLEMENT
     * draw will call draw on each shape.  Notice that the canvas does not need to know
     * about what the shapes are. It simply (and blindly) calls draw on each shape
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // Next two tests are just preventative so the layout preview works correctly.
        if(canvasModel == null) {
            return;
        }
        ArrayList<CanvasShape> shapeList = canvasModel.getShapes();
        //....
    }
}
