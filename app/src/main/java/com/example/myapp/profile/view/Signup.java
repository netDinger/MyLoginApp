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
import com.example.myapp.profile.contract.SignupContract;
import com.example.myapp.profile.presenter.SignupPresenter;
import com.example.myapp.profile.util.DefaultConfig;
import com.google.firebase.FirebaseApp;

public class Signup extends AppCompatActivity implements SignupContract.View {


    int saveMode = Activity.MODE_PRIVATE;

    private Button signupBtn;
    private EditText userName;
    private EditText password;

    private SignupContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_signup);

        presenter = new SignupPresenter();
        presenter.setView(this);

        SharedPreferences sharedPreferences = getSharedPreferences(DefaultConfig.UserPref,saveMode);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();

        signupBtn = findViewById(R.id.signupBtn);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignup(userName.getText().toString(),
                        password.getText().toString());

                Toast.makeText(Signup.this, "Signing Up!!!", Toast.LENGTH_SHORT).show();
//                prefEditor.putString(DefaultConfig.UserNameKey,userName.getText().toString());
//                prefEditor.putString(DefaultConfig.PasswordKey,password.getText().toString());
//                prefEditor.apply();
               // startActivity(new Intent(Signup.this, LoginActivity.class));
               }
        });
    }


    @Override
    public void onSignupSuccess() {
        Intent i = new Intent();
        i.setClass(this,HomeActivity.class);
        startActivity(i);
    }

    @Override
    public void onSignupFailure() {
        Toast.makeText(this, "Signup Fail!!!", Toast.LENGTH_SHORT).show();
    }


}