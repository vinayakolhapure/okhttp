    package com.example.vinayak.okhttp;

    import android.content.SharedPreferences;
    import android.preference.PreferenceManager;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    public class Signup extends AppCompatActivity implements SignupAsync.ISetupData{

        EditText fname;
        EditText lname;
        EditText password;
        EditText repeat;
        EditText email;
        Button signUpButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            fname = (EditText) findViewById(R.id.et_fname);
            lname = (EditText) findViewById(R.id.et_lname);
            email = (EditText) findViewById(R.id.et_email);
            password = (EditText) findViewById(R.id.et_password);
            repeat = (EditText) findViewById(R.id.et_repeat_pass);
            signUpButton = (Button) findViewById(R.id.sign_up_button);

            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(fname.getText().toString().equals(null) || email.getText().toString().equals(null) ||
                            repeat.getText().toString().equals(null)) {
                        Toast.makeText(Signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        new SignupAsync(Signup.this).execute(email.getText().toString(),password.getText().toString(),
                                fname.getText().toString(),lname.getText().toString());
                    }
                }
            });

        }

        @Override
        public void setupData(String token) {
            Toast.makeText(Signup.this, "Sign up done", Toast.LENGTH_SHORT).show();
            if(token!=null){
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Signup.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("KEY", token);
                Log.d("demo",fname.getText().toString() + " IS THE NAME");
                editor.putString("NAME", fname.getText().toString()+ " " + lname.getText().toString());
                editor.commit();

                finish();
            }
        }
    }
