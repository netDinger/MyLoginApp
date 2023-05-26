package com.example.myapp.profile.view;

import static android.view.Gravity.CENTER_VERTICAL;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.profile.util.DefaultConfig;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


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
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private static final String TAG = "afsa";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signup);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        app_logo = findViewById(R.id.appLogo);

        signupBtn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,Signup.class)));

        loginBtn.setOnClickListener(v -> {

            firebaseAuth.signInWithEmailAndPassword(userName.getText().toString(),password.getText().toString())
                    .addOnSuccessListener(authResult ->
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class)))
                    .addOnFailureListener(e ->
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
//
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
