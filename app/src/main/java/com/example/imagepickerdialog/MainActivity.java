package com.example.imagepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.imagepickerdialog.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_ImagePicker;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn_ImagePicker = findViewById(R.id.btn_ImagePicker);
        image = findViewById(R.id.image);

        btn_ImagePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int[] imageIds = {R.drawable.ima1, R.drawable.ima2, R.drawable.ima3, R.drawable.ima4};

                        ImagePickerDialog imagePickerDialog = new ImagePickerDialog(
                                MainActivity.this,
                                imageIds,
                                "Select Car Detail"
                        );
                        imagePickerDialog.setOnImageSelectedListener(new MyOnImageSelectedListener());
                        imagePickerDialog.setOnOkButtonClickListener(new ImagePickerDialog.OnOkButtonClickListener() {
                            @Override
                            public void onOkButtonClick(int accelerationProgress, int controlProgress) {
                                imagePickerDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Acceleration Progress: " + accelerationProgress, Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, "Control Progress: " + controlProgress, Toast.LENGTH_SHORT).show();
                                //int accProgress = imagePickerDialog.getAccelerationProgress();
                                //int controlProgress = imagePickerDialog.getControlProgress();
                            }
                        });

                        imagePickerDialog.show();
                    }
                }
        );


    }

    private class MyOnImageSelectedListener implements ImagePickerDialog.OnImageSelectedListener {
        @Override
        public void onImageSelected(ImagePickerDialog imagePickerDialog, int imageId) {
            image.setImageResource(imageId);

        }
    }
}
