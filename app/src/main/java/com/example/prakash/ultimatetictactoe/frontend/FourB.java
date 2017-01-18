package com.example.prakash.ultimatetictactoe.frontend;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prakash.ultimatetictactoe.R;


public class FourB extends AppCompatActivity implements View.OnClickListener
{
    EditText editText;
    EditText editText2;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.four_b);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        String player1;
        String player2;
        Intent i = new Intent(FourB.this, Fifth.class);
        player2 = editText.getText().toString();
        player1 = editText2.getText().toString();
        if(player1.equals("")||player2.equals(""))
        {
            Toast.makeText(FourB.this, "Enter name of players", Toast.LENGTH_SHORT).show();
            editText2.setHint("Enter First player name");
            editText.setHint("Enter Second player name");
            return;
        }
        i.putExtra("Player1Name", player1);
        i.putExtra("Player2Name", player2);
        startActivity(i);
       overridePendingTransition(R.anim.right_in, R.anim.right_fi);
    }
}