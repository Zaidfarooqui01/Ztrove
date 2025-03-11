package com.example.ztrove.features;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


import com.example.ztrove.AppsList;
import com.example.ztrove.R;

public class VoiceCalculator extends AppCompatActivity {

    private TextView resultView,resultEditText, back1;
    private Button buttonDivide, buttonMultiply, buttonMinus, buttonPlus, buttonEquality;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    private Button buttonDelete, buttonClear, buttonPoint;
    private TextToSpeech ts;
    private double valueOne = Double.NaN;
    private char CURRENT_ACTION;
    private String input = "";

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultEditText = findViewById(R.id.textViewResult);
        back1 = findViewById(R.id.back1);
        resultView = findViewById(R.id.resultView);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonEquality = findViewById(R.id.buttonEquality);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        buttonPoint = findViewById(R.id.buttonPoint);

        // Initialize TextToSpeech
        ts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    ts.setLanguage(Locale.ENGLISH);
                    ts.setSpeechRate(0.8f);
                } else {
                    Toast.makeText(VoiceCalculator.this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(VoiceCalculator.this, AppsList.class);
                startActivity(i);
                finish();
            }
        });


        button1.setOnClickListener(view -> appendToInput("1"));
        button2.setOnClickListener(view -> appendToInput("2"));
        button3.setOnClickListener(view -> appendToInput("3"));
        button4.setOnClickListener(view -> appendToInput("4"));
        button5.setOnClickListener(view -> appendToInput("5"));
        button6.setOnClickListener(view -> appendToInput("6"));
        button7.setOnClickListener(view -> appendToInput("7"));
        button8.setOnClickListener(view -> appendToInput("8"));
        button9.setOnClickListener(view -> appendToInput("9"));
        button0.setOnClickListener(view -> appendToInput("0"));
        buttonPoint.setOnClickListener(view -> appendToInput("."));
        buttonPlus.setOnClickListener(view -> appendToInput("+"));
        buttonMinus.setOnClickListener(view -> appendToInput("-"));
        buttonMultiply.setOnClickListener(view -> appendToInput("*"));
        buttonDivide.setOnClickListener(view -> appendToInput("/"));

        buttonEquality.setOnClickListener(view -> calculateResult());

        buttonDelete.setOnClickListener(view -> {
            if (input.length() > 0) {
                input = input.substring(0, input.length() - 1);
                resultEditText.setText(input);
            }
        });

        buttonClear.setOnClickListener(view -> {
            input = "";
            resultEditText.setText("");
            resultView.setText("");
        });
    }

    private void appendToInput(String value) {
        input += value;
        resultEditText.setText(input);
    }

    private void calculateResult() {
        try {
            valueOne = evaluateExpression(input);
            String result = String.valueOf(valueOne);
            resultView.setText(result);
            ts.speak("The result is " + result, TextToSpeech.QUEUE_FLUSH, null, null);
            valueOne = Double.NaN;
            CURRENT_ACTION = '0';
            input = result;
        } catch (Exception e) {
            resultEditText.setText("Error");
            input = "";
        }
    }

    private double evaluateExpression(String expression) {
        // Simple implementation for evaluating expressions
        return new ExpressionEvaluator().evaluate(expression);
    }

    @Override
    protected void onDestroy() {
        if (ts != null) {
            ts.stop();
            ts.shutdown();
        }
        super.onDestroy();
    }
}
