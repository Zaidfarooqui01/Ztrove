package com.example.ztrove;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Register extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    FirebaseAuth fa;
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    b1 = findViewById(R.id.registerButton);
                    b2 = findViewById(R.id.backButton);
                    e1 = findViewById(R.id.email);
                    e2 = findViewById(R.id.password2);
                    p1 = findViewById(R.id.progressBar2);
                    fa = FirebaseAuth.getInstance();

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        e2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        b2.setOnClickListener(v -> {
            Intent i = new Intent(Register.this, MainActivity.class);
            startActivity(i);
            finish();
        });
        b1.setOnClickListener(v -> {
            String s1 = e1.getText().toString();
            String s2 = e2.getText().toString();
            p1.setVisibility(View.VISIBLE);
            if (s1.isEmpty()) {
                e1.setError("e-mail is required");
            } else {
                if (s2.isEmpty()) {
                    e2.setError("enter password");
                } else {
                    p1.setVisibility(View.VISIBLE);
                    fa.createUserWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            p1.setVisibility(View.INVISIBLE);
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent k = new Intent(Register.this, MainActivity.class);
                                startActivity(k);
                                finish();
                            } else {
                                String errorMessage = Objects.requireNonNull(task.getException()).getMessage();
                                Toast.makeText(Register.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}