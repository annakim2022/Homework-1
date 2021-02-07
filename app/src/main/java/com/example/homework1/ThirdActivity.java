package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ThirdActivity extends AppCompatActivity {
    private TextView textView_MadLib;
    private Button button_goHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Bundle b = this.getIntent().getExtras();

        String[] value = b.getStringArray("value");
        String[] inputs = b.getStringArray("inputs");

        String MadLib = "";
        textView_MadLib = findViewById(R.id.textView_MadLib);
        for (int i = 0; i < inputs.length; i++) {
            MadLib += value[i];
            MadLib += inputs[i];
        }
        if (value.length == inputs.length + 2) {
            MadLib += value[value.length-2];
        }
        textView_MadLib.setText(MadLib);
        textView_MadLib.setTextSize(30);

        button_goHome = findViewById(R.id.button_goHome);
        button_goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
