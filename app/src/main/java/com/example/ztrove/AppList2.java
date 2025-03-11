package com.example.ztrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ztrove.features.AxelometerApp;
import com.example.ztrove.features.BlutoothApp;
import com.example.ztrove.features.TorchApp;

public class AppList2 extends AppCompatActivity {
    ImageView iv1,iv2, iv3, iv4;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_list2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            iv1=(ImageView)findViewById(R.id.imageView9);
            iv2=(ImageView)findViewById(R.id.imageView7);
            iv3=(ImageView)findViewById(R.id.imageViewbl);
            iv4=(ImageView)findViewById(R.id.imageViewAx);
            b1=(Button)findViewById(R.id.button8);
            b2=(Button)findViewById(R.id.button7);

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fl = new Intent(AppList2.this, TorchApp.class);
                startActivity(fl);
                finish();
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ttsi = new Intent(AppList2.this, TorchApp.class);
                startActivity(ttsi);
                finish();
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intblue = new Intent(AppList2.this, BlutoothApp.class);
                startActivity(intblue);
                finish();
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(AppList2.this, AxelometerApp.class);
                startActivity(k);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AppList2.this,AppsList.class);
                startActivity(i);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(AppList2.this,ThanksPage.class);
                startActivity(j);
                finish();
            }
        });
    }
}