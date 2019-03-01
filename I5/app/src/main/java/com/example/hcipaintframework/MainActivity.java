package com.example.hcipaintframework;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.Observer;
import android.databinding.DataBindingUtil;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.hcipaintframework.BuildConfig.*;
import com.example.hcipaintframework.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PaintState_VM stateModel;
    private PaintState_Controller stateController;
    private MyCanvasView canvas;
    static final Integer[] brushSizes = new Integer[] { 8, 9, 10, 11, 12};

    /**
     * GIVEN
     * onStart sets up the models and callbacks for the various components. It is called
     * after onCreate. Thus we can assume "binding" has already been defined
     */
    @Override
    protected void onStart() {
        super.onStart();
        if(binding != null) {
            PaintState_VM stateModel = ViewModelProviders.of(this).get(PaintState_VM.class);
            MyCanvas_VM canvasModel
                    = ViewModelProviders.of(this).get(MyCanvas_VM.class);
            canvas.setCanvasVM(canvasModel);
            binding.SpinBrush.setOnItemSelectedListener(stateController);
            binding.ColorPicker.setOnClickListener(new ColorPickerListener(this, stateModel));
            binding.TogBLine.setOnClickListener(stateController);
            binding.TogBCurve.setOnClickListener(stateController);
            binding.TogBRect.setOnClickListener(stateController);
            binding.TogBOval.setOnClickListener(stateController);

            // Map the view IDs with the control to make intelligent model calls
            // and to let the buttons work as radio toggle buttons.

            // TO IMPLEMENT (I4): for each radio button (or whatever control type you used)
            // you need to add it to the state controller.  This way you can programmatically
            // control which toggle button is enabled.
            stateController.registerRadioButton(binding.TogBLine, PaintState_VM.DLINE);
            stateController.registerRadioButton(binding.TogBCurve, PaintState_VM.DCURVE);
            stateController.registerRadioButton(binding.TogBRect, PaintState_VM.DRECT);
            stateController.registerRadioButton(binding.TogBOval, PaintState_VM.DOVAL);
            stateController.setControlState(binding.TogBLine);

            binding.FillSwitch.setOnCheckedChangeListener(stateController);
            binding.ClearButton.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    canvas.clear();
                }
            });

        }

    }

    /**
     * GIVEN
     * When an activity is created, a bundle of state information is passed in.
     * The activity hasn't started yet so you can only get the generated components but
     * not hook up callbacks (that's for onStart
     * @param savedInstanceState bundle of information about activity state.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setFontSpinner();

        PaintState_VM stateModel = ViewModelProviders.of(this).get(PaintState_VM.class);
        MyCanvas_VM canvasModel
            = ViewModelProviders.of(this).get(MyCanvas_VM.class);

        canvas = binding.canvasView;
        canvas.setCanvasVM(canvasModel);
        canvas.setStateVM(stateModel);
        stateController = new PaintState_Controller(this, stateModel, binding);
    }

    /**
     * GIVEN
     * Programmatically setting the brush size values in the spinner
     * rather than simply using XML
     * @return Spinner View that was altered.
    */
    private Spinner setFontSpinner() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, brushSizes);

        binding.SpinBrush.setAdapter(adapter);
        return binding.SpinBrush;
    }
}
