package com.example.myapp.profile.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.profile.contract.SignupContract;
import com.example.myapp.profile.presenter.SignupPresenter;
import com.example.myapp.profile.util.DefaultConfig;
import com.google.firebase.FirebaseApp;

import java.util.Calendar;

public class Signup extends AppCompatActivity implements SignupContract.View {

    int saveMode = Activity.MODE_PRIVATE;

    private Button signupBtn;
    private EditText userName;
    private EditText password;
    private TextView birthdate,birthTime;

    private SignupContract.Presenter presenter;
    private ProgressDialog dialog;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private TimePickerDialog.OnTimeSetListener onTimeSetListener;
    private Calendar cal;
    private int year,month,day,hour,min;

    private final int DialogType = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_signup);

        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);

        presenter = new SignupPresenter();
        presenter.setView(this);

        signupBtn = findViewById(R.id.signupBtn);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        birthdate = findViewById(R.id.birthdate);
        birthTime = findViewById(R.id.birthTime);

        signupBtn.setOnClickListener(v -> {
            showLoading("Singing Up...","Please Wait");
            presenter.onSignup(userName.getText().toString(),
                    password.getText().toString());

          });
        SharedPreferences sharedPreferences = getSharedPreferences("PREF",MODE_PRIVATE);

        SharedPreferences sharedPreference = getPreferences(MODE_PRIVATE);

        onDateSetListener = (view, year, month, dayOfMonth) -> {

            int mnth = month+1;
            birthdate.setText(year+"/"+mnth+"/"+dayOfMonth);
        };

        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                birthTime.setText(hourOfDay+":"+minute);
            }
        };

        birthdate.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(this,onDateSetListener,year,month,day);
            dialog.show();
        });

        birthTime.setOnClickListener(v-> {
           TimePickerDialog dialog = new TimePickerDialog(this,onTimeSetListener,hour,min,false);
           dialog.show();
        });
    }


    @Override
    public void onSignupSuccess() {
        dialog.hide();
        Toast.makeText(this, "Signup Success!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent();
        i.setClass(this, MessageActivity.class);
        startActivity(i);
    }

    @Override
    public void onSignupFailure() {
        dialog.hide();
        Toast.makeText(this, "Signup Fail!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(String title,String msg) {
        dialog = new ProgressDialog(this);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

    @Override
    protected void onStop() {
        dialog = null;
        super.onStop();
    }


}