package com.example.myapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.R;


/**
 *
 * Events (User Interactions) ->user click, long press,back press, gesture
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
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


        loginBtn = findViewById(R.id.loginBtn);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        app_logo = findViewById(R.id.appLogo);



        userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    Toast.makeText(MainActivity.this, "UserName", Toast.LENGTH_SHORT).show();
            }
        });


        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserTyping = true;
                //sendTypingStatusToServer();
                Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                isUserTyping = false;
            }
        });


        //implicit intent = occur outside of the application
        //explicit intent = occur inside my application



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle  b = new Bundle();
//                b.putString("username",userName.getText().toString());
               // Log.d(TAG,b.getString("username"));
                Intent i = new Intent();
                i.setClass(MainActivity.this, HomeActivity.class);
                //i.putExtras(b);
                startActivity(i);



                //check if password == "password"
                if(userName.getText().toString().equals("android") ) {

                }else{
                   Toast.makeText(MainActivity.this,
                                   userName.getText().toString(),
                                   Toast.LENGTH_SHORT)
                           .show();
                }
            }
        });
        
        app_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();


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
