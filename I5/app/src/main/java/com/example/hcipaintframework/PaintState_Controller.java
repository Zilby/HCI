package com.example.hcipaintframework;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.widget.AdapterView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import android.util.Pair;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import com.example.hcipaintframework.databinding.ActivityMainBinding;

// Notice that the controller could have been included in the model class
// (and that may have been better) but I wanted to demonstrate separation of concerns.
// There is no PaintStateView class because it actually consists of a series of views
// defined in the XML that all need to speak to each other.
public class PaintState_Controller implements AdapterView.OnItemSelectedListener,
        View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {
    private PaintState_VM vm;
    // Storing the controls that get forcibly turned off and on when one is clicked.
    private ArrayList<ToggleButton> buttonList;
    private ArrayList<Pair<Integer, Integer>> togglePairs;
    private Context mContext;


    public PaintState_Controller(Context c, PaintState_VM model, ActivityMainBinding binding) {
        this.mContext = c;
        this.vm = model;
        togglePairs = new ArrayList<>();
        buttonList = new ArrayList<>();
    }

    //public void addIDControlPair(int id, int shapeID) {
    //    togglePairs.add(new Pair(id, shapeID));
    //}
    public void registerRadioButton(ToggleButton tb, int shapeID) {
        togglePairs.add(new Pair(tb.getId(), shapeID));
        buttonList.add(tb);
    }

    /**
     * GIVEN
     * getShapeIdFromView takes a toggle button view and
     * determines the shape value associated with that button.
     *
     * @param view of the toggle button clicked
     * @return the int value of the shape associated with the button.
     */
    public int getShapeIdFromView(View view) {
        int id = view.getId();
        for (Pair<Integer, Integer> p : togglePairs) {
            if (p.first.intValue() == id) {
                return p.second.intValue();
            }
        }
        return 0;
    }

    /**
     * GIVEN
     * onItemSelected simply handles item click events relating to the combobox
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if ((view instanceof TextView) && (vm != null)) {
            TextView tView = (TextView) view;
            try {
                String numTxt = tView.getText().toString();
                System.out.println("Field value is: " + numTxt);
                if (numTxt != null) {
                    // Not sure why Integer.parseValue isn't working so went the long way.
                    Integer brushSize = Integer.valueOf(numTxt);
                    vm.getLiveData().setBrushSize(brushSize.intValue());
                }
            } catch (NullPointerException e) {
                System.out.println("Brush size change error");
            }
        }
        System.out.println("Selected element");
    }

    // GIVEN: handles check button clicks
    public void onCheckedChanged(CompoundButton button, boolean check) {
        vm.getLiveData().setFillOn(check);
    }

    // GIVEN: in case nothing is selected, give feedback
    public void onNothingSelected(AdapterView<?> adapterView) {
        System.out.println("Nothing selected");
    }

    /**
     * GIVEN
     * onClick method for the toggle buttons only. When clicked, only one toggle button should be
     * checked and the model should be updated.
     * View arg0: the toggle button that was clicked.
     */
    public void onClick(View arg0) {
        if ((arg0 instanceof ToggleButton) && (vm != null)) {
            int shapeID = getShapeIdFromView(arg0);
            vm.getLiveData().setCurrShape(shapeID);
            if (shapeID == PaintState_VM.DCURVE) {
                Toast toast = Toast.makeText(this.mContext.getApplicationContext(), "Curves need 3 points", Toast.LENGTH_SHORT);
                toast.show();
            }
            setControlState((ToggleButton) arg0);
        } // end of setShape toggle control.

        System.out.println("Button " + arg0.toString() + " clicked");
    }

    /**
     * This function makes the controls work like radio buttons. Each toggle button
     * (in buttonList) has setChecked(false) called.  The tbOn button is has checked
     * set to true
     *
     * @param tbOn: The Toggle button clicked.  Call getId on it to find its ID
     */
    public void setControlState(ToggleButton tbOn) {
        for (ToggleButton b : buttonList) {
            b.setChecked(b.getId() == tbOn.getId());
        }
    }
}
