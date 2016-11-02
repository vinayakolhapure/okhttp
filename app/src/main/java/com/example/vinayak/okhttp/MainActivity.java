package com.example.vinayak.okhttp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String token = preferences.getString("KEY","null");
        Log.d("demo",token);

        if(token.equals(null) || token == null) {
            Intent intent = new Intent(this,Signup.class);
            intent.putExtra("TOKEN",token);
            startActivity(intent);
        } else {
            //Toast.makeText(MainActivity.this, "Chat Activity", Toast.LENGTH_SHORT).show();
            Intent chatIntent = new Intent(this,Chat.class);
            chatIntent.putExtra("TOKEN",token);
            startActivity(chatIntent);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("demo","On post resume");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("demo","On resume");
    }
}


