package com.example.prakash.ultimatetictactoe.frontend;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.prakash.ultimatetictactoe.R;


public class Third extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton onD, onW;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.third);
        onD = (ImageButton)findViewById(R.id.imageButton4);
        onD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Third.this, FourB.class));
                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_fi);
            }
        });
        onW = (ImageButton) findViewById(R.id.imageButton5);
        onW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Third.this, "Under Developement", Toast.LENGTH_SHORT).show();
            }
        });
    }
}