package com.example.prakash.ultimatetictactoe.frontend;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.prakash.ultimatetictactoe.R;


public class Second extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Button start;
        Button options;
        Button aboutus;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.second);
        start = (Button)findViewById(R.id.start);
        options = (Button)findViewById(R.id.options);
        aboutus = (Button)findViewById(R.id.about_us);
        start.setOnClickListener(this);
        options.setOnClickListener(this);
        aboutus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.start:
                startActivity(new Intent(Second.this, Third.class));
                overridePendingTransition(R.anim.left_in, R.anim.left_fi);
                break;

            case R.id.options:
                startActivity(new Intent(Second.this, Options.class));
                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_fi);
                break;

            case R.id.about_us:
                startActivity(new Intent(Second.this, AboutUs.class));
                overridePendingTransition(R.anim.right_in, R.anim.right_fi);
                break;
        }
    }
}