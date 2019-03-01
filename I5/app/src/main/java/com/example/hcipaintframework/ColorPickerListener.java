package com.example.hcipaintframework;

import android.graphics.Color;
import android.view.View;
import android.content.Context;
import android.app.Dialog;
import top.defaults.colorpicker.ColorPickerPopup;

/**
 * Given class that uses the provided Color Picker dialog.
 * No need to edit or understand.  Just use it.
 */
public class ColorPickerListener implements View.OnClickListener {

    private Context mContext;
    private PaintState_VM mStateModel;
    public ColorPickerListener(Context c, PaintState_VM model) {
        mContext = c;
        mStateModel = model;
    }
    @Override
    public void onClick(View v) {
        new ColorPickerPopup.Builder(mContext)
                .initialColor(Color.RED) // Set initial color
                .enableAlpha(true) // Enable alpha slider or not
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(v, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        mStateModel.getLiveData().setbColor(color);
                    }

                    @Override
                    public void onColor(int color, boolean fromUser) {
                        mStateModel.getLiveData().setbColor(color);
                    }
                });
    }

}
