package com.example.prakash.ultimatetictactoe.frontend;



import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prakash.ultimatetictactoe.R;
import com.example.prakash.ultimatetictactoe.backend.Backend;
import com.example.prakash.ultimatetictactoe.json.jsonparser;

import org.json.JSONObject;

import java.util.Map;



public class Fifth extends AppCompatActivity implements View.OnClickListener
{
    public class UserResponse
    {
        int currentCell;
        int currentActiveBlock;
        int scoreP1;
        int scoreP2;
        int nextActiveBlock;
        String winner;
        String global_winner;
        String turn;
        String reset_cells;
        String used_cells_raw_string;
        String used_cells[];
        String buttonPressed;
        String disableBlock;
        String reset_block_color;

        UserResponse(Map result)
        {
            winner = (String) result.get("CurrentWinner");
            global_winner = (String) result.get("GlobalWinner");
            currentCell = Integer.parseInt((String) result.get("CurrentCell"));
            currentActiveBlock = Integer.parseInt((String) result.get("CurrentActiveBlock"));
            nextActiveBlock = Integer.parseInt((String) result.get("NextActiveBlock"));
            used_cells_raw_string = (String) result.get("DisableList");
            disableBlock = (String) result.get("DisableBlock");
            used_cells = used_cells_raw_string.split("::::");
            reset_cells = (String) result.get("ResetList");
            scoreP1 = Integer.parseInt((String) result.get("ScoreP1"));
            scoreP2 = Integer.parseInt((String) result.get("ScoreP2"));
            buttonPressed = (String) result.get("ButtonPressed");
            turn = (String) result.get("Turn");
            reset_block_color = (String)result.get("ResetBlockColor");
        }
    }

    TextView scoreP1;
    TextView scoreP2;
    ImageButton breset;
    ImageButton bundo;
    Backend backend;
    private ImageButton[] ImageButtons;
    private TableLayout tables[];
    String P1Name;
    String P2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent i;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fifth);
        i = getIntent();
        bind();
        initialize();
        P1Name = i.getStringExtra("Player1Name");
        P2Name = i.getStringExtra("Player2Name");
    }

    private void bind()
    {
        scoreP1 = (TextView)findViewById(R.id.textView3);
        scoreP2 = (TextView)findViewById(R.id.textView4);
        backend = new Backend();
        breset = (ImageButton)findViewById(R.id.imageButton4);
        bundo = (ImageButton)findViewById(R.id.imageButton3);
        ImageButtons =
                new ImageButton[]{
                        (ImageButton) findViewById(R.id.b00), (ImageButton) findViewById(R.id.b01),
                        (ImageButton) findViewById(R.id.b02), (ImageButton) findViewById(R.id.b03),
                        (ImageButton) findViewById(R.id.b04), (ImageButton) findViewById(R.id.b05),
                        (ImageButton) findViewById(R.id.b06), (ImageButton) findViewById(R.id.b07),
                        (ImageButton) findViewById(R.id.b08), (ImageButton) findViewById(R.id.b09),
                        (ImageButton) findViewById(R.id.b10), (ImageButton) findViewById(R.id.b11),
                        (ImageButton) findViewById(R.id.b12), (ImageButton) findViewById(R.id.b13),
                        (ImageButton) findViewById(R.id.b14), (ImageButton) findViewById(R.id.b15),
                        (ImageButton) findViewById(R.id.b16), (ImageButton) findViewById(R.id.b17),
                        (ImageButton) findViewById(R.id.b18), (ImageButton) findViewById(R.id.b19),
                        (ImageButton) findViewById(R.id.b20), (ImageButton) findViewById(R.id.b21),
                        (ImageButton) findViewById(R.id.b22), (ImageButton) findViewById(R.id.b23),
                        (ImageButton) findViewById(R.id.b24), (ImageButton) findViewById(R.id.b25),
                        (ImageButton) findViewById(R.id.b26), (ImageButton) findViewById(R.id.b27),
                        (ImageButton) findViewById(R.id.b28), (ImageButton) findViewById(R.id.b29),
                        (ImageButton) findViewById(R.id.b30), (ImageButton) findViewById(R.id.b31),
                        (ImageButton) findViewById(R.id.b32), (ImageButton) findViewById(R.id.b33),
                        (ImageButton) findViewById(R.id.b34), (ImageButton) findViewById(R.id.b35),
                        (ImageButton) findViewById(R.id.b36), (ImageButton) findViewById(R.id.b37),
                        (ImageButton) findViewById(R.id.b38), (ImageButton) findViewById(R.id.b39),
                        (ImageButton) findViewById(R.id.b40), (ImageButton) findViewById(R.id.b41),
                        (ImageButton) findViewById(R.id.b42), (ImageButton) findViewById(R.id.b43),
                        (ImageButton) findViewById(R.id.b44), (ImageButton) findViewById(R.id.b45),
                        (ImageButton) findViewById(R.id.b46), (ImageButton) findViewById(R.id.b47),
                        (ImageButton) findViewById(R.id.b48), (ImageButton) findViewById(R.id.b49),
                        (ImageButton) findViewById(R.id.b50), (ImageButton) findViewById(R.id.b51),
                        (ImageButton) findViewById(R.id.b52), (ImageButton) findViewById(R.id.b53),
                        (ImageButton) findViewById(R.id.b54), (ImageButton) findViewById(R.id.b55),
                        (ImageButton) findViewById(R.id.b56), (ImageButton) findViewById(R.id.b57),
                        (ImageButton) findViewById(R.id.b58), (ImageButton) findViewById(R.id.b59),
                        (ImageButton) findViewById(R.id.b60), (ImageButton) findViewById(R.id.b61),
                        (ImageButton) findViewById(R.id.b62), (ImageButton) findViewById(R.id.b63),
                        (ImageButton) findViewById(R.id.b64), (ImageButton) findViewById(R.id.b65),
                        (ImageButton) findViewById(R.id.b66), (ImageButton) findViewById(R.id.b67),
                        (ImageButton) findViewById(R.id.b68), (ImageButton) findViewById(R.id.b69),
                        (ImageButton) findViewById(R.id.b70), (ImageButton) findViewById(R.id.b71),
                        (ImageButton) findViewById(R.id.b72), (ImageButton) findViewById(R.id.b73),
                        (ImageButton) findViewById(R.id.b74), (ImageButton) findViewById(R.id.b75),
                        (ImageButton) findViewById(R.id.b76), (ImageButton) findViewById(R.id.b77),
                        (ImageButton) findViewById(R.id.b78), (ImageButton) findViewById(R.id.b79),
                        (ImageButton) findViewById(R.id.b80)
                };

        tables =
                new TableLayout[]{
                        (TableLayout) findViewById(R.id.table0),
                        (TableLayout) findViewById(R.id.table1),
                        (TableLayout) findViewById(R.id.table2),
                        (TableLayout) findViewById(R.id.table3),
                        (TableLayout) findViewById(R.id.table4),
                        (TableLayout) findViewById(R.id.table5),
                        (TableLayout) findViewById(R.id.table6),
                        (TableLayout) findViewById(R.id.table7),
                        (TableLayout) findViewById(R.id.table8),
                };
    }


    private void initialize()
    {
        scoreP1.setText("0");
        scoreP2.setText("0");
        for (ImageButton ImageButton : ImageButtons)
        {
            ImageButton.setOnClickListener(this);
            ImageButton.setBackgroundResource(R.drawable.clearimage);
        }
        for(TableLayout tableLayout : tables)
            tableLayout.setBackgroundColor(Color.BLACK);
        breset.setOnClickListener(this);
        breset.setEnabled(true);
        bundo.setOnClickListener(this);
        bundo.setEnabled(true);
    }

    private void enableAll()
    {
        for (android.widget.ImageButton ImageButton : ImageButtons) ImageButton.setEnabled(true);
    }

    private void disableAll()
    {
        for (ImageButton ImageButton : ImageButtons) ImageButton.setEnabled(false);
    }

    private void enable(int id)
    {
        ImageButtons[id].setEnabled(true);
    }

    private void disable(int id)
    {
        ImageButtons[id].setEnabled(false);
    }


    @Override
    public void onClick(View v)
    {
        int index;
        ImageButton curr_button;
        curr_button = (ImageButton) findViewById(v.getId());
        index = getIndex(curr_button);
        runFrontEnd(index);
    }

    public void runFrontEnd(int index)
    {
        JSONObject response;
        Map result;
        response = backend.execute(index);
        result = jsonparser.parseJson(response);
        UserResponse userResponse = new UserResponse(result);
        operate(userResponse);
    }

    private void operate(UserResponse userResponse)
    {
        if(userResponse.buttonPressed.equals("Reset"))
        {
            initialize();
            enableAll();
            return;
        }
        else if(userResponse.buttonPressed.equals("Undo"))
        {
            if(userResponse.reset_cells.equals("All"))
                initialize();
            else
            {
                ImageButtons[Integer.parseInt(userResponse.reset_cells)].
                        setBackgroundResource(R.drawable.clearimage);
            }
            if(!userResponse.reset_block_color.equals("None"))
            {
                if(Integer.parseInt(userResponse.reset_block_color) != Integer.MAX_VALUE)
                    tables[Integer.parseInt(userResponse.reset_block_color)].
                        setBackgroundColor(Color.BLACK);
            }
        }
        if(userResponse.buttonPressed.equals("GameButton"))
        {
            if (userResponse.turn.equals("Player 1"))
            {
                ImageButtons[userResponse.currentCell].setBackgroundResource(R.drawable.cross);
            }
            else
            {
                ImageButtons[userResponse.currentCell].setBackgroundResource(R.drawable.o);
            }
        }
        changeTableColor(userResponse.nextActiveBlock, userResponse.currentActiveBlock ,
                userResponse.turn, userResponse.buttonPressed);
        disableAll();
        enableBlock(userResponse.nextActiveBlock);
        disableWinnerBlocks(userResponse.disableBlock);
        disableUsedCells(userResponse.used_cells);
        setText(scoreP1,Integer.toString(userResponse.scoreP1));
        setText(scoreP2,Integer.toString(userResponse.scoreP2));

        if(userResponse.winner.equals("Player 1"))
            showToast(P1Name+" won this round");
        else if(userResponse.winner.equals("Player 2"))
            showToast(P2Name+" won this round");

        if(!userResponse.global_winner.equals("None"))
            gameOver(userResponse.global_winner);
    }

    private void changeTableColor(int nextActiveBlock, int currentActiveBlock, String player,
                                  String buttonPressed)
    {
        int color;
        if(player.equals("Player 1"))
            color = Color.GREEN;
        else
            color = Color.RED;

        if(player.equals("Player 1") && buttonPressed.equals("Undo"))
            color = Color.RED;
        else if(player.equals("Player 2") && buttonPressed.equals("Undo"))
            color = Color.GREEN;


        if(currentActiveBlock!=Integer.MAX_VALUE)
            tables[currentActiveBlock].setBackgroundColor(Color.BLACK);
        if(nextActiveBlock!=Integer.MAX_VALUE)
            tables[nextActiveBlock].setBackgroundColor(color);
    }

    private void setText(TextView tv, String s)
    {
        tv.setText(s);
    }

    private void disableUsedCells(String[] used_cells)
    {
        for (String used_cell : used_cells)
        {
            try {
                disable(Integer.parseInt(used_cell));
            } catch (NumberFormatException e)
            {

            }
        }
    }

    private void disableWinnerBlocks(String disableBlock)
    {
        for(int i=0;i<disableBlock.length();i++)
            disableBlock(disableBlock.charAt(i)-48);
    }

    private void enableBlock(int nextActiveBlock)
    {
        if(nextActiveBlock!=Integer.MAX_VALUE)
            for(int i = nextActiveBlock * 9; i < (nextActiveBlock + 1) * 9; i++)
                enable(i);
        else
        {
            enableAll();
        }
    }

    private void disableBlock(int id)
    {
        for(int i=id*9;i<(id+1)*9;i++)
            disable(i);
    }

    private void gameOver(String global_winner)
    {
        disableAll();
        AlertDialog ad = new AlertDialog.Builder(Fifth.this)
                .setTitle("WINNER!!!")
                .setMessage(getGlobalWinnerName(global_winner))
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Restart Game
                        initialize();
                        enableAll();
                        backend.execute(100);
                    }
                })
                .setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Fifth.this, Second.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                })
                .setIcon(R.drawable.trophy)
                .show();
        ad.setCanceledOnTouchOutside(false);
    }

    private String getGlobalWinnerName(String global_winner)
    {
        if(global_winner.equals("Player 1"))
            return P1Name+" wins";
        else if(global_winner.equals("Player 2"))
            return P2Name+" wins";
        else if(global_winner.equals("Drawn"))
            return "Match Drawn";
        return "None";
    }

    private void showToast(String winner)
    {
        Toast.makeText(Fifth.this, winner, Toast.LENGTH_SHORT).show();
    }

    private int getIndex(ImageButton b)
    {
        for(int i=0;i<ImageButtons.length;i++)
        {
            if(ImageButtons[i].equals(b))
            {
                return i;
            }
        }
        if(b.equals(breset))
            return 100;
        if(b.equals(bundo))
            return 200;
        return -1;
    }
}