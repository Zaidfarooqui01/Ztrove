package com.example.ztrove.features;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ztrove.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class SongPlay extends AppCompatActivity {
    MediaPlayer mp2;
    RoundedImageView pauseIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_song_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            mp2 = MediaPlayer.create(this,R.raw.x);
            RoundedImageView ri2=findViewById(R.id.pauseIcon);
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pauseIcon.setOnClickListener(v -> {
            mp2.pause();
            Intent i2 = new Intent(SongPlay.this, Song.class);
            startActivity(i2);
            finish();
        });
    }
}