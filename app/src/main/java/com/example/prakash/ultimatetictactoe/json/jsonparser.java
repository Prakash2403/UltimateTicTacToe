package com.example.prakash.ultimatetictactoe.json;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class jsonparser
{
    public static Map<String, String> parseJson(JSONObject json_data)
    {
        HashMap<String, String> map;
        JSONObject jsonObject;
        jsonObject = json_data;
        map = new HashMap<String, String>();
        try
        {
            map.put("CurrentWinner", jsonObject.getString("CurrentWinner"));
            map.put("GlobalWinner", jsonObject.getString("GlobalWinner"));
            map.put("NextActiveBlock", jsonObject.getString("NextActiveBlock"));
            map.put("ScoreP1", jsonObject.getString("ScoreP1"));
            map.put("ScoreP2", jsonObject.getString("ScoreP2"));
            map.put("DisableBlock", jsonObject.getString("DisableBlock"));
            map.put("ButtonPressed", jsonObject.getString("ButtonPressed"));
            map.put("Turn", jsonObject.getString("Turn"));
            map.put("CurrentCell", jsonObject.getString("CurrentCell"));
            map.put("DisableList", jsonObject.getString("DisableList"));
            map.put("ResetList", jsonObject.getString("ResetList"));
            map.put("ResetBlockColor", jsonObject.getString("ResetBlockColor"));
            map.put("CurrentActiveBlock", jsonObject.getString("CurrentActiveBlock"));
        }
        catch(JSONException jsonException)
        {
            System.out.println(jsonException);
        }
        return map;
    }
}
/*
jsonObject.put("CurrentCell", currentCell);
            jsonObject.put("CurrentActiveBlock", currentActiveBlock);
            jsonObject.put("NextActiveBlock", nextActiveBlock);
            jsonObject.put("CurrentWinner", winner);
            jsonObject.put("GlobalWinner", global_winner);
            jsonObject.put("Turn", getTurn());
            jsonObject.put("DisableList", used_cells);
            jsonObject.put("ResetList", reset_cells);
            jsonObject.put("ScoreP1", score[0]);
            jsonObject.put("ScoreP2", score[1]);
            jsonObject.put("ButtonPressed", buttonPressed);
 */

