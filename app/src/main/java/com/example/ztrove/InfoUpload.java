package com.example.ztrove;

import android.app.usage.ConfigurationStats;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoUpload extends AppCompatActivity {
    EditText e1, e2, e3;
    Button b1, b2;
    FirebaseDatabase fd;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_upload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            e1 = findViewById(R.id.editTextText);
            e2 = findViewById(R.id.editTextText2);
            e3 = findViewById(R.id.editTextText3);
            b1 = findViewById(R.id.button);
            b2 = findViewById(R.id.button2);
            fd = FirebaseDatabase.getInstance();
            db = fd.getReference("Guest");

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b2.setOnClickListener(v -> {
            Intent i = new Intent(InfoUpload.this, MainActivity.class);
            startActivity(i);
            finish();
        });
        b1.setOnClickListener(v -> {
            String s1 = e1.getText().toString();
            String s2 = e2.getText().toString();
            String s3 = e3.getText().toString();

            if (!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty()) {
                Guest guest = new Guest(s1, s2, s3);
                db.push().setValue(guest).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(InfoUpload.this, "Database Saved", Toast.LENGTH_SHORT).show();
                        Intent j=new Intent(InfoUpload.this,AppsList.class);
                    } else {
                        Toast.makeText(InfoUpload.this, "Failed to save!!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else{
                Toast.makeText(InfoUpload.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

    }
}