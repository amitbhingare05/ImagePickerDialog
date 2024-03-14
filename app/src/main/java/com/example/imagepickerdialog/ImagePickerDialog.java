package com.example.imagepickerdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class ImagePickerDialog extends Dialog {

    private LinearLayout imagesContainer;
    private TextView txtTitle, txtAccerlation, txtControl;
    private SeekBar seekBarAcc, seekBarControl;
    private Button btn_ok;
    private int accelerationProgress = 0;
    private int controlProgress = 0;

    public interface OnImageSelectedListener {
        void onImageSelected(ImagePickerDialog imagePickerDialog, int imageId);
    }

    public interface OnOkButtonClickListener {
        void onOkButtonClick(int accelerationProgress, int controlProgress);
    }

    private OnImageSelectedListener onImageSelectedListener = null;
    private OnOkButtonClickListener onOkButtonClickListener = null;

    public void setOnImageSelectedListener(OnImageSelectedListener onImageSelectedListener) {
        this.onImageSelectedListener = onImageSelectedListener;
    }

    public void setOnOkButtonClickListener(OnOkButtonClickListener onOkButtonClickListener) {
        this.onOkButtonClickListener = onOkButtonClickListener;
    }

    public ImagePickerDialog(Context context, int[] imageIds, String title) {
        super(context);
        setContentView(R.layout.imagepicker);
        imagesContainer = findViewById(R.id.imagesContainer);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        seekBarAcc = findViewById(R.id.seekBarAcc);
        seekBarControl = findViewById(R.id.seekBarControl);
        txtAccerlation = findViewById(R.id.txtAccerlation);
        txtControl = findViewById(R.id.txtControl);

        btn_ok = findViewById(R.id.btn_ok);

        seekBarAcc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtAccerlation.setText("Acceleration" + String.valueOf(progress));
                accelerationProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtControl.setText("Control" + String.valueOf(progress));
                controlProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        for (int imageId : imageIds) {
            ImageView img = new ImageView(context);
            img.setImageResource(imageId);
            img.setLayoutParams(
                    new ViewGroup.LayoutParams(200, 200)
            );
            img.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onImageSelectedListener != null) {
                                onImageSelectedListener.onImageSelected(
                                        ImagePickerDialog.this,
                                        imageId
                                );
                            }
                        }
                    }
            );
            imagesContainer.addView(img);
        }
        for (int imageId : imageIds) {
            ImageView img = new ImageView(context);
            img.setImageResource(imageId);
            img.setLayoutParams(
                    new ViewGroup.LayoutParams(200, 200)
            );
            img.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onImageSelectedListener != null) {
                                onImageSelectedListener.onImageSelected(
                                        ImagePickerDialog.this,
                                        imageId
                                );
                            }
                        }
                    }
            );
            imagesContainer.addView(img);
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOKbuttonClick();
            }
        });
    }

    private void onOKbuttonClick() {
        int accProgress = accelerationProgress;
        //int controlProgress = seekBarControl.getProgress();

//        if (onOkButtonClickListener != null) {
//            onOkButtonClickListener.onOkButtonClick(accProgress, controlProgress);
//        }
        if (onOkButtonClickListener != null) {
            onOkButtonClickListener.onOkButtonClick(accProgress, controlProgress);
        }
    }

    public int getAccelerationProgress() {
        return seekBarAcc.getProgress();
    }
}