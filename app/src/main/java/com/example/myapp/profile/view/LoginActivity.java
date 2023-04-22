package com.example.myapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.profile.util.DefaultConfig;


/**
 *
 * Events (User Interactions) ->user click, long press,back press, gesture
 *
 *
 */
public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
     private TextView signupBtn;
    private EditText userName;
    private EditText password;
    private ImageView app_logo;
    private ImageButton imgBtn;
    private Boolean isUserTyping = false;

    private static final String TAG = "afsa";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences(DefaultConfig.UserPref,MODE_PRIVATE);

        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signup);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        app_logo = findViewById(R.id.appLogo);

        //implicit intent = occur outside of the application
        //explicit intent = occur inside my application


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(LoginActivity.this,Signup.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if password == getPassword()
                if(userName.getText().toString().equals(preferences.getString(DefaultConfig.UserNameKey,"#user1291248!@!*@")) ) {
                    Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                }else{
                   Toast.makeText(LoginActivity.this,
                                   userName.getText().toString() + "is Wrong User!",
                                   Toast.LENGTH_SHORT)
                           .show();
                }
            }
        });
        

    }




    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"msg");
        Log.i(TAG,"msg");
        Log.v(TAG,"msg");
        Log.e(TAG,"msg");
        Log.w(TAG,"msg");
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
