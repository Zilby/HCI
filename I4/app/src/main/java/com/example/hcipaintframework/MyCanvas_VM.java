package com.example.hcipaintframework;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyCanvas_VM extends ViewModel {
    // Notice we are using LiveData: this gives us the ability to rotate the screen and NOT lose
    // state data. getValue will return an ArrayList of CanvasShape objects and this ArrayList is
    // not destroyed when the view is destroyed.
    private final MutableLiveData<ArrayList<CanvasShape>> shapeLiveData = new MutableLiveData<>();

    public MyCanvas_VM() {
        if( shapeLiveData.getValue() == null ) {
            shapeLiveData.setValue(new ArrayList<CanvasShape>());
        }
    }

    /**
     * GIVEN
     * This method replicates the list of shapes so external classes cannot modify the
     * shape list directly. Go through add and clear shape methods to do that.
     * Useful for painting the shapes.
     * @return the ArrayList of shapes.
     */
    public ArrayList<CanvasShape> getShapes() {
        return new ArrayList<CanvasShape>(shapeLiveData.getValue());
    }

    /**
     * GIVEN
     * addShape adds shape to the list of shapes stored.
     * @param shape
     */
    public void addShape(CanvasShape shape) {
        shapeLiveData.getValue().add(shape);
    }

    /**
     * GIVEN
     * clearShapes removes all shapes from the stored list of shapes.
     * This ultimately clears everything on the canvas.
     */
    public void clearShapes() {
        shapeLiveData.getValue().clear();
    }
}