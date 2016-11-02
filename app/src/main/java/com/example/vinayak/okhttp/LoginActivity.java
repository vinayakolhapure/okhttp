package com.example.vinayak.okhttp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinayak.okhttp.com.example.vinayak.okhttp.model.LoginUser;

import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity implements LoginAsync.ISetupData {

    private final OkHttpClient client = new OkHttpClient();
    //String intentEmail,intentPassword;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.editTextEmail);
        passwordText = (EditText) findViewById(R.id.editTextPwd);

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                new LoginAsync(LoginActivity.this).execute(email,password);
            }
        });

        findViewById(R.id.btnSignUpInLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Signup.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setupData(LoginUser user) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("KEY", user.getToken());
        editor.putString("NAME",user.getUserFname() + " " + user.getUserLname());
        editor.commit();
        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(LoginActivity.this,Chat.class);
        startActivity(intent);
    }
}
