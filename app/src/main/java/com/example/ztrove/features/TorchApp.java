package com.example.ztrove.features;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ztrove.AppList2;
import android.Manifest;
import com.example.ztrove.R;

public class TorchApp extends AppCompatActivity {
    Button on, off, back;
    ImageView iv;
    CameraManager cm;

    @SuppressLint({"MissingInflatedId", "NewApi"}) // Suppress lint warnings for demo purposes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_torch_app);

        on = findViewById(R.id.button15);
        off = findViewById(R.id.button16);
        back = findViewById(R.id.button18);
        cm = (CameraManager) getSystemService(CAMERA_SERVICE);

        // Handle edge-to-edge UI insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back button functionality
        back.setOnClickListener(v -> {
            Intent intent = new Intent(TorchApp.this, AppList2.class);
            startActivity(intent);
            finish();
        });

        // Turn on the torch
        on.setOnClickListener(v -> {
                try {
                    String cameraId = cm.getCameraIdList()[0];
                    cm.setTorchMode(cameraId, true);
                    iv.setImageResource(R.drawable.flahon);

                } catch (CameraAccessException e) {
                    Toast.makeText(this, "Failed to access the Flash light", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
        });

        // Turn off the torch
        off.setOnClickListener(v -> {
                try {
                    String cameraId = cm.getCameraIdList()[0];
                    cm.setTorchMode(cameraId, false);
                    Toast.makeText(this, "Torch turned OFF", Toast.LENGTH_SHORT).show();
                    iv.setImageResource(R.drawable.flahoff);
                } catch (CameraAccessException e) {
                    Toast.makeText(this, "Failed to access the camera", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
        });
    }
}
