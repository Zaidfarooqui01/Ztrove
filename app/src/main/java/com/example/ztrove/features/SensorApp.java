package com.example.ztrove.features;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.metrics.Event;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ztrove.R;

public class SensorApp extends AppCompatActivity implements SensorEventListener{
    private MediaPlayer mp;
    private SensorManager sm;
    private Sensor s;
    private Switch s1;
    private ImageView imgChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sensor_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            mp=MediaPlayer.create(this,R.raw.x);
            sm=(SensorManager)getSystemService(SENSOR_SERVICE);
            s=sm.getDefaultSensor(Sensor.TYPE_LIGHT);
            s1=(Switch)findViewById(R.id.switch1);
            imgChange=findViewById(R.id.imgChange);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0]>1){
            if (!mp.isPlaying()){
            mp.start();
            s1.setChecked(true);
            imgChange.setImageResource(R.drawable.playing);
            }
        }else {
            if (mp.isPlaying()) {
                mp.pause();
                s1.setChecked(false);
                imgChange.setImageResource(R.drawable.paused);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            mp = null; }
        sm.unregisterListener(this);
    }
}