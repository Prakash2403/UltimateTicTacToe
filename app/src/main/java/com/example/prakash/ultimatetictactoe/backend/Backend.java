package com.example.prakash.ultimatetictactoe.backend;


import com.example.prakash.ultimatetictactoe.json.jsonparser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;


// TODO handle the case where nextActiveBlock is disabled.
public class Backend
{
    private boolean turn;       //true for player1. false for player2
    private String global_winner;
    private int score[];
    private int boardStatus[][][];
    private int no_terms[];
    private ArrayList<String> result;
    private int currentActiveBlock;
    private int nextActiveBlock;
    private String buttonPressed;
    private StringBuilder used_cells;
    private Stack<JSONObject> history;
    private boolean[] disabledBlocks;
    private String winner;
    private int currentCell;
    private String reset_cells;
    private String reset_block_color;

    public Backend()
    {
        initialize();
    }

    private void initialize()
    {
        turn = true;
        buttonPressed = "Reset";
        global_winner = "None";
        winner= "None";
        score = new int[2];
        no_terms = new int[9];
        result = new ArrayList<>();
        used_cells = new StringBuilder("");
        reset_cells = "All";
        reset_block_color = "None";
        boardStatus = new int[9][3][3];
        disabledBlocks = new boolean[9];
        currentCell = Integer.MAX_VALUE;            //initial value
        currentActiveBlock = Integer.MAX_VALUE;    //initial value
        nextActiveBlock = Integer.MAX_VALUE;       //if next active block is disabled.
        buttonPressed = null;
        history = new Stack<>();

        for(int i=0;i<9;i++)
            for(int j=0;j<3;j++)
                for(int k=0;k<3;k++)
                    boardStatus[i][j][k] = -1;

        Arrays.fill(no_terms, 0);
        Arrays.fill(disabledBlocks, false);

        score[0] = 0;           //Score of player 1
        score[1] = 0;           //Score of player 2
    }

    private void updateScore(int num)
    {
        ++score[num];
    }

    private void updateTurn()
    {
        turn = !turn;
    }

    public JSONObject execute(int cell_number)
    {
        int row, column;
        JSONObject curr_state;

        reset_cells = new String();
        currentActiveBlock = getBlockNumber(cell_number);
        buttonPressed = getButtonPressed(cell_number);
        if(buttonPressed.equals("Reset"))
        {
            executeReset();
            buttonPressed = "Reset";
            curr_state = toJson();
            updateHistory(curr_state);
            return curr_state;
        }
        else if(buttonPressed.equals("Undo"))
        {
            executeUndo();
            buttonPressed="Undo";
            return toJson();
        }
        currentCell = cell_number;
        row = getRowNumber(cell_number);
        column = getColumnNumber(cell_number);

        used_cells.append(cell_number+"::::");
        updateBoard(currentActiveBlock, row, column);
        no_terms[currentActiveBlock]++;

        winner = getWinner(currentActiveBlock);

        switch (winner)
        {
            case "Player 1":
                disabledBlocks[currentActiveBlock] = true;
                updateScore(0);
                break;
            case "Player 2":
                disabledBlocks[currentActiveBlock] = true;
                updateScore(1);
                break;
        }
        nextActiveBlock = getNextActiveBlock(cell_number);
        if(!winner.equals("None"))
            result.add(winner);

        global_winner = findGlobalWinner();
        curr_state = toJson();
        updateTurn();
        updateHistory(curr_state);
        return curr_state;
    }

    private void updateHistory(JSONObject move_json)
    {
        history.push(move_json);
    }

    private String getButtonPressed(int cell_number)
    {
        if(cell_number >= 0 && cell_number <= 80)
            return "GameButton";
        else if(cell_number == 100)
            return "Reset";
        else if(cell_number == 200)
            return "Undo";
        return "None";
    }

    private int getColumnNumber(int cell_number)
    {
        return (cell_number%9)%3;
    }

    private int getRowNumber(int cell_number)
    {
        return (cell_number%9)/3;
    }

    private void updateBoard(int blockNumber, int row, int column)
    {
        if(blockNumber>=0 && blockNumber<=8)
            if(turn)
                boardStatus[blockNumber][row][column] = 0;
            else
                boardStatus[blockNumber][row][column] = 1;
    }

    private String findGlobalWinner()
    {
        int no_drawn = findOccurrences("Drawn", result);
        int no_win_p1 = findOccurrences("Player 1", result);
        int no_win_p2 = findOccurrences("Player 2", result);
        int deciding_factor = (9-no_drawn)/2;

        if(no_win_p1 > deciding_factor)
            return "Player 1";
        else if(no_win_p2 > deciding_factor)
            return "Player 2";
        else if(result.size()==9 && no_win_p1==no_win_p2)
            return "Drawn";
        else
            return "None";
    }

    private int findOccurrences(String str, ArrayList list)
    {
        String curr_item;
        int num_occurrences;
        Iterator itr;
        num_occurrences = 0;
        itr = list.iterator();
        while(itr.hasNext())
        {
            curr_item = (String)itr.next();
            if(curr_item.equals(str))
                num_occurrences++;
        }
        return num_occurrences;
    }

    private int getBlockNumber(int cell_number)
    {
        return cell_number/9;
    }

    private void executeReset()
    {
        initialize();
    }

    private void executeUndo()
    {
        Map previous_values;
        Map current_values;
        JSONObject previous_move;
        JSONObject current_move;
        String used_cells_raw_string_array[];
        String pre_nab;
        if(history.isEmpty())
        {
            initialize();
            return;
        }
        current_move = history.pop();

        if(history.isEmpty())
        {
            initialize();
            return;
        }
        turn = !turn;
        previous_move = history.peek();
        current_values = jsonparser.parseJson(current_move);
        previous_values = jsonparser.parseJson(previous_move);
        nextActiveBlock = Integer.parseInt((String)previous_values.get("NextActiveBlock"));
        currentActiveBlock = Integer.parseInt((String)previous_values.get("CurrentActiveBlock"));
        winner = (String)current_values.get("CurrentWinner");
        pre_nab = (String)current_values.get("CurrentActiveBlock");
        reset_cells = (String)current_values.get("CurrentCell");
        reset_block_color = (String)current_values.get("NextActiveBlock");
        used_cells_raw_string_array = used_cells.toString().split("::::");
        used_cells = new StringBuilder();
        for(int i=0; i<used_cells_raw_string_array.length;i++)
            if(!used_cells_raw_string_array[i].equals(reset_cells))
                used_cells.append(used_cells_raw_string_array[i]+"::::");
        score[0] = Integer.parseInt((String)previous_values.get("ScoreP1"));
        score[1] = Integer.parseInt((String)previous_values.get("ScoreP2"));

        // boardstatus is a 3D array.
        System.out.println("Print it"+pre_nab);
        boardStatus[Integer.parseInt(pre_nab)]
                [getRowNumber(Integer.parseInt(reset_cells))]
                [getColumnNumber(Integer.parseInt(reset_cells))] = -1;
        if(!winner.equals("None"))
        {
            disabledBlocks[Integer.parseInt(pre_nab)]=false;
            result.remove(0);
        }
    }

    private void print(int[][][] boardStatus)
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<3;j++)
            {
                for(int k=0;k<3;k++)
                {
                    System.out.print(boardStatus[i][j][k]+"\t");
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    private JSONObject toJson()
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("CurrentActiveBlock", currentActiveBlock);
            jsonObject.put("CurrentCell", currentCell);
            jsonObject.put("NextActiveBlock", nextActiveBlock);
            jsonObject.put("CurrentWinner", winner);
            jsonObject.put("GlobalWinner", global_winner);
            jsonObject.put("Turn", getTurn());
            jsonObject.put("DisableBlock", getDisableBlockString());
            jsonObject.put("DisableList", used_cells);
            jsonObject.put("ResetList", reset_cells);
            jsonObject.put("ScoreP1", score[0]);
            jsonObject.put("ScoreP2", score[1]);
            jsonObject.put("ResetBlockColor", reset_block_color);
            jsonObject.put("ButtonPressed", buttonPressed);
        }
        catch (JSONException jsonException)
        {
            System.out.println("Exception in converting to JSON");
        }
        return jsonObject;
    }

    private String getDisableBlockString()
    {
        String result="";
        for(int i=0;i<9;i++)
            if(disabledBlocks[i])
            {
                result = result+i;
            }
        return result;
    }

    private int getNextActiveBlock(int cell_number)
    {
        int nab = cell_number%9;
        if(disabledBlocks[nab])
            return Integer.MAX_VALUE;
        return no_terms[cell_number/9] == 9 ? Integer.MAX_VALUE : nab;
    }

    private String getWinner(int block_num)
    {
        for(int i=0;i<3;i++)
        {
            if(boardStatus[block_num][i][0] == boardStatus[block_num][i][1] &&
                    boardStatus[block_num][i][1] == boardStatus[block_num][i][2] )
            {
                if(!(boardStatus[block_num][i][0]==-1))
                    return getPlayerID(boardStatus[block_num][i][0]);
            }
            else if(boardStatus[block_num][0][i] == boardStatus[block_num][1][i] &&
                    boardStatus[block_num][1][i] == boardStatus[block_num][2][i] )
            {
                if(!(boardStatus[block_num][0][i]==-1))
                    return getPlayerID(boardStatus[block_num][0][i]);
            }
            else if(boardStatus[block_num][0][0] == boardStatus[block_num][1][1] &&
                    boardStatus[block_num][1][1] == boardStatus[block_num][2][2] )
            {
                if(!(boardStatus[block_num][0][0]==-1))
                    return getPlayerID(boardStatus[block_num][1][1]);
            }
            else if(boardStatus[block_num][0][2] == boardStatus[block_num][1][1] &&
                    boardStatus[block_num][1][1] == boardStatus[block_num][2][0] )
            {
                if(!(boardStatus[block_num][0][2]==-1))
                    return getPlayerID(boardStatus[block_num][1][1]);
            }
        }
        if(no_terms[block_num] == 9)
            return "Drawn";
        return "None";
    }

    private String getPlayerID(int num)
    {
        if(num == 0)
            return "Player 1";
        return "Player 2";
    }

    private String getTurn()
    {
        if(turn)
            return "Player 1";
        return "Player 2";
    }
}
