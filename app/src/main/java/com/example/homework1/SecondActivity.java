package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button button_generate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // grab intent
        Intent intent = getIntent();
        Bundle b = this.getIntent().getExtras();

        String[] blanks = b.getStringArray("blanks");
       // String[] array2 = b.getStringArray("value");
        linearLayout = findViewById(R.id.linearLayout_inputs);
        //Log.d("blanks", array2[0]);

        // get title
        TextView textView_title = findViewById(R.id.textView_title);
        textView_title.setText(intent.getStringExtra("title"));
        EditText[] ed = new EditText[blanks.length];
        // for every blank, create a text view and a text input layout
        for (int i = 0; i < blanks.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(blanks[i]);
            linearLayout.addView(textView);
            ed[i] = new EditText(this);
            linearLayout.addView(ed[i]);
        }

        button_generate = findViewById(R.id.button_generate);
        button_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] inputs = new String[ed.length];
                for (int i = 0; i < ed.length; i++) {
                    inputs[i] = ed[i].getEditableText().toString();
                }
                launchNextActivity(v, inputs);
            }

        });
    }



    public void launchNextActivity(View view, String[] inputs) {
        Intent intent = new Intent(this, ThirdActivity.class);
        Bundle b = this.getIntent().getExtras();
       // Log.d("value length", " = " + value.length);
       // Log.d("inputs length", " = " + inputs.length);
        int missing = 0;
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim();
            if (inputs[i].equals("")) {
                missing++;
            }
        }
        if (missing > 0){
            toast();
        }
        else {
            String[] value = b.getStringArray("value");
            intent.putExtra("value", value);
            intent.putExtra("inputs", inputs);
            startActivity(intent);
        }


    }

    public void toast() {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
