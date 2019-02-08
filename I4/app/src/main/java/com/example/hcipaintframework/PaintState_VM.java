package com.example.hcipaintframework;

import android.graphics.Color;
import android.support.annotation.IntDef;
import android.widget.ToggleButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.MutableLiveData;
import java.lang.annotation.RetentionPolicy;
import com.example.hcipaintframework.databinding.ActivityMainBinding;

import java.lang.annotation.Retention;

/**
 * The PaintState_VM is a ViewModel that keeps the state of the system controls when
 * the screen is rotated.  PS_Data objects(below) are stored as "LiveData" or data that persists
 * even when the layout is broken down and re-composed (each time one rotates the screen).
 * There is nothing to implement here but you may need to understand the code.
 **/
public class PaintState_VM extends ViewModel{

    public class PS_Data {
        private @Shape int currShape;

        private int bColor;
        private int brushSize;
        private boolean fillShape;

        public PS_Data() {
            currShape = DLINE;
            bColor = Color.BLUE;
            brushSize = 10;
            fillShape = false;
        }

        public void setCurrShape(@Shape int shape) {
            // Presuming none of these components are null.
            currShape = shape;
        }

        public @Shape int getCurrShape(){
            return currShape;
        }

        public void setFillOn(boolean fill) {
            fillShape = fill;
        }
        public boolean isFillShape() {
            return fillShape;
        }

        public int getBrushSize() {
            return brushSize;
        }

        public void setBrushSize(int size) {
            brushSize = size;
        }

        public void setbColor(int color) {
            bColor = color;
        }

        public int getbColor() {
            return bColor;
        }
    }

    public final MutableLiveData<PS_Data> stateLiveData = new MutableLiveData<>();

    public PaintState_VM() {
        if( stateLiveData.getValue() == null ) {
            stateLiveData.setValue(new PS_Data());
        }
    }
    // An enumeration of the various drawable shapes. Thus a Shape maps to a number
    // between 0 and 3(inclusive)
    public static final int DLINE = 0;
    public static final int DCURVE = 1;
    public static final int DRECT = 2;
    public static final int DOVAL = 3;

    @IntDef({DLINE, DCURVE, DRECT, DOVAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {}

    public PS_Data getLiveData() {
        return stateLiveData.getValue();
    }

}
