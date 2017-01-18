package com.example.prakash.ultimatetictactoe.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.prakash.ultimatetictactoe.R;


public class Options extends AppCompatActivity implements View.OnClickListener{

    CheckBox sound;
    CheckBox instructions;
    private Boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.options);
        sound = (CheckBox) findViewById(R.id.sound);
        instructions = (CheckBox) findViewById(R.id.instruction);
        sound.setOnClickListener(this);
        instructions.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.instruction:
                executeInstruction();
                break;

            case R.id.sound:
                executeSound();
                break;
        }
    }

    public void executeSound()
    {
        Toast.makeText(Options.this, "Under development", Toast.LENGTH_SHORT).show();
    }


    public void executeInstruction()
    {
        if(instructions.isChecked())
            PooP();
    }

        public void PooP()
    {
        startActivity(new Intent(Options.this, Popup.class));
    }
}