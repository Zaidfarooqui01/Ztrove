package com.example.ztrove.features;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ztrove.R;

public class AxelometerApp extends AppCompatActivity implements SensorEventListener {
    private MediaPlayer mp;
    private SensorManager sm;
    private Sensor accelerometerSensor;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_axelometer_app);

        // Initialize components
        mp = MediaPlayer.create(this, R.raw.y);
        img = findViewById(R.id.imageBird);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register the listener
        if (accelerometerSensor != null) {
            sm.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "Accelerometer not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];

        if (Math.abs(x) > 1) { // Detect significant tilt
            if (!mp.isPlaying()) {
                mp.start();
            }
            img.setImageResource(R.drawable.tiltbird);
        } else {
            if (mp.isPlaying()) {
                mp.pause();
            }
            img.setImageResource(R.drawable.bird);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Optional: Handle accuracy changes if needed
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener to save resources
        sm.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Re-register the listener
        if (accelerometerSensor != null) {
            sm.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release MediaPlayer resources
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }
}
