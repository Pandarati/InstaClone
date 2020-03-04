package com.codepath.jmckinley.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private EditText etUserNameSU;
    private EditText etPasswordSU;
    private Button btnSignUpComplete;

    public final String TAG = "SignUpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUserNameSU = findViewById(R.id.etUserNameSU);
        etPasswordSU = findViewById(R.id.etPasswordSU);
        btnSignUpComplete = findViewById(R.id.btnSignUpComplete);

        btnSignUpComplete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Create the ParseUser
                ParseUser user = new ParseUser();
// Set core properties
                user.setUsername(etUserNameSU.getText().toString());
                user.setPassword(etPasswordSU.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Log.i(TAG, "There was trouble completing the sign up", e);
                            Toast.makeText(SignUpActivity.this, "Sign Up Error Occured", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
