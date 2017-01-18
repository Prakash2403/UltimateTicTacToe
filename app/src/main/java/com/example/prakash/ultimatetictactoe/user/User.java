package com.example.prakash.ultimatetictactoe.user;

import java.util.Map;

/**
 * Created by prakash on 18/1/17.
 */

public class User
{
    private int currentCell;
    private int currentActiveBlock;
    private int scoreP1;
    private int scoreP2;
    private int nextActiveBlock;
    private String winner;
    private String global_winner;
    private String turn;
    private String reset_cells;
    private String used_cells_raw_string;
    private String used_cells[];
    private String buttonPressed;
    private String disableBlock;
    private String reset_block_color;

    User(Map result)
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
