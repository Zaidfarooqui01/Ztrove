package com.example.ztrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ztrove.features.SensorApp;
import com.example.ztrove.features.Snapshot;
import com.example.ztrove.features.Song;
import com.example.ztrove.features.Videoapp;
import com.example.ztrove.features.VoiceCalculator;
import com.example.ztrove.features.WifiApp;
import com.makeramen.roundedimageview.RoundedImageView;

public class AppsList extends AppCompatActivity {
    RoundedImageView iv1,iv2,iv3,iv4,iv5,iv6;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apps_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            b1=(Button)findViewById(R.id.button5);//back
            b2=(Button)findViewById(R.id.button6);//Next
            RoundedImageView iv1=findViewById(R.id.imageViewbl);
            RoundedImageView iv2=findViewById(R.id.imageView2);
            RoundedImageView iv3=findViewById(R.id.imageView3);
            RoundedImageView iv4=findViewById(R.id.imageView4);
            RoundedImageView iv5=findViewById(R.id.imageView5);
            RoundedImageView iv6=findViewById(R.id.imageView6);

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(AppsList.this, VoiceCalculator.class);
                Toast.makeText(AppsList.this, "Welcome to calculator", Toast.LENGTH_SHORT).show();
                startActivity(i1);
                finish();
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(AppsList.this, Song.class);
                startActivity(i2);
                Toast.makeText(AppsList.this, "Welcome to media player", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(AppsList.this, Snapshot.class);
                Toast.makeText(AppsList.this, "Welcome to Camera app", Toast.LENGTH_SHORT).show();
                startActivity(i3);
                finish();
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(AppsList.this, SensorApp.class);
                startActivity(i4);
                finish();
            }
        });
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(AppsList.this, Videoapp.class);
                startActivity(i5);
                finish();
            }
        });
        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6 = new Intent(AppsList.this, WifiApp.class);
                startActivity(i6);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AppsList.this,ChooseMenu.class);
                startActivity(i);
                Toast.makeText(AppsList.this, "returning to Main menu", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(AppsList.this,AppList2.class);
                startActivity(j);
                finish();
            }
        });

    }
}