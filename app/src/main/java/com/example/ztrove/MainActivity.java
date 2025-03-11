package com.example.ztrove;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
    Button b1;
    TextView t1;
    ProgressBar p1;
    EditText e1,e2;
    FirebaseAuth fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            t1 = findViewById(R.id.toSignup);
            b1 = findViewById(R.id.loginButton);
            e1 = findViewById(R.id.email1);
            e2 = findViewById(R.id.password);
            fa = FirebaseAuth.getInstance();
            p1 = findViewById(R.id.progressBar);

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (e2 != null) {
            e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        t1.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Redirecting to registration page", Toast.LENGTH_SHORT).show();
                finish();
            });
            b1.setOnClickListener(v -> {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                p1.setVisibility(View.VISIBLE);
                if (s1.isEmpty()) {
                    e1.setError("E-mail required");
                }else {
                    if (s2.isEmpty()) {
                        e2.setError("Enter password");
                    } else {
                        p1.setVisibility(View.VISIBLE);
                        fa.signInWithEmailAndPassword(s1, s2).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                p1.setVisibility(View.INVISIBLE);
                                Intent j = new Intent(MainActivity.this, ChooseMenu.class);
                                startActivity(j);
                                Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                p1.setVisibility(View.INVISIBLE);
                                String errorMessage = Objects.requireNonNull(task.getException()).getMessage();
                                Toast.makeText(MainActivity.this, "Wrong credentials  " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
    }