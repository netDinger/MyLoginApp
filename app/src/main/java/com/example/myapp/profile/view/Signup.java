package com.example.myapp.profile.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.profile.util.DefaultConfig;

public class Signup extends AppCompatActivity {


    int saveMode = Activity.MODE_PRIVATE;

    private Button signupBtn;
    private EditText userName;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SharedPreferences sharedPreferences = getSharedPreferences(DefaultConfig.UserPref,saveMode);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();

        signupBtn = findViewById(R.id.signupBtn);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prefEditor.putString(DefaultConfig.UserNameKey,userName.getText().toString());
                prefEditor.putString(DefaultConfig.PasswordKey,password.getText().toString());
                prefEditor.apply();
                startActivity(new Intent(Signup.this, LoginActivity.class));
                Toast.makeText(Signup.this, "Success!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}