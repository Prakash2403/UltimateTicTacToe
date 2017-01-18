package com.example.prakash.ultimatetictactoe.frontend;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.example.prakash.ultimatetictactoe.R;


public class Popup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        try
        {
            getActionBar().hide();
        }
        catch(NullPointerException e)
        {

        }
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w*0.9), (int) (h*0.9));
    }
}