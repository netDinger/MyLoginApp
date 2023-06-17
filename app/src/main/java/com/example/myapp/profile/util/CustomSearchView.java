package com.example.myapp.profile.util;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.profile.view.MyView;

public class CustomSearchView extends RelativeLayout {

    public CustomSearchView(Context context) {
        super(context);
        MyView myView = new MyView(context);
        Button button = new Button(context);
        button.setAllCaps(false);
        button.setText("Undo");
        button.setOnClickListener(v -> {
            myView.invalid();
        });

        this.addView(myView);
        this.addView(button);
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        EditText search = findViewById(R.id.search);

        Button button = new Button(context,attrs);
        button.setAllCaps(false);
        button.setText("Undo");

        this.addView(button);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_search,this,true);


        ImageView cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {

        });


    }



}
