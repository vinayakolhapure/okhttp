package com.example.vinayak.okhttp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class Chat extends AppCompatActivity implements GetMessagesAsync.SetupData{

    String token = null;
    ListView lv;
    TextView header;
    ImageButton logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String token = preferences.getString("KEY","null");
        String userName = preferences.getString("NAME","null");
        Log.d("demo",token);

        logoutButton = (ImageButton) findViewById(R.id.logOutButton);
        header = (TextView) findViewById(R.id.tv_chat_header);
        header.setText(userName);

        if(token.equals(null) || token == null || token.equals("null")) {
            Intent intent = new Intent(this,LoginActivity.class);
            intent.putExtra("TOKEN",token);
            startActivity(intent);
        } else if(!(token.equals(null) || token.equals("null"))){
            lv = (ListView) findViewById(R.id.listView);
            new GetMessagesAsync(this).execute(token);
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                getData.edit().remove("KEY").commit();
                Intent intent = new Intent(Chat.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setupData(ArrayList<Message> messages) {
        if(messages!=null) {
            ListViewAdapter adapter = new ListViewAdapter(Chat.this,R.layout.item_row_layout, messages);
            adapter.setNotifyOnChange(true);
            if(lv!=null)
                lv.setAdapter(adapter);
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        lv = (ListView) findViewById(R.id.listView);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String token = preferences.getString("KEY","null");
        Log.d("demo",token);
        if(!(token.equals("null") || token.equals(null)))
            new GetMessagesAsync(this).execute(token);
        Log.d("demo","on post resume");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("demo", "on resume");
    }
}
