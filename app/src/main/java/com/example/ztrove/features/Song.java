package com.example.ztrove.features;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ztrove.AppsList;
import com.example.ztrove.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class Song extends AppCompatActivity {
    RoundedImageView playIcon;
    ImageView backBtn;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_song);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            RoundedImageView playIcon = findViewById(R.id.playIcon);
            ImageView backBtn = findViewById(R.id.songBackbtn);
            mp = MediaPlayer.create(this,R.raw.x);

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        playIcon.setOnClickListener(v -> {
            mp.start();
            Intent i = new Intent(Song.this,SongPlay.class);
            startActivity(i);
            finish();

        });
        backBtn.setOnClickListener(v -> {
            Intent i2=new Intent(Song.this, AppsList.class);
            startActivity(i2);
            finish();
        });

    }
}