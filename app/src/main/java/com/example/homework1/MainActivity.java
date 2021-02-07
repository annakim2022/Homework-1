package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button button_getStarted;

    private static final String api_url = "http://madlibz.herokuapp.com/api/random";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_getStarted = findViewById(R.id.button_getStarted);
        button_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextActivity(v);
            }
        });

    }
    public void launchNextActivity(View view) {

    client.addHeader("Accept", "application/json");
    client.get(api_url, new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            try {
                JSONObject json = new JSONObject(new String(responseBody));
                JSONArray blanks = json.getJSONArray("blanks");

                //Log.d("blanks", json.getJSONArray("blanks").toString());
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String[] s = new String[json.getJSONArray("blanks").length()];
                for (int i = 0; i < json.getJSONArray("blanks").length(); i++) { // for every blank
                // add to string array
                    s[i] = blanks.getString(i);
                   // Log.d("blanks", blanks.getString(i));
                    intent.putExtra("blanks", s);
                }

                JSONArray value = json.getJSONArray("value");
                String[] v = new String[json.getJSONArray("value").length()];
                for (int i = 0; i < json.getJSONArray("value").length(); i++) { // for every blank
                    // add to string array
                    v[i] = value.getString(i);
                    // Log.d("value", value.getString(i));
                    intent.putExtra("value", v);
                }

                String title = json.getString("title");
                intent.putExtra("title", title);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Log.e("api response", new String(responseBody));
        }
    });


    }
}