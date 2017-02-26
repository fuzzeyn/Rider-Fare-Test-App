/**
 * Prompts a user to select and purchase bus fare based on information from a JSON
 *
 * File: com.example.mike.riderfare.JsonPasserAcitivty.java
 *
 * Author: Michael Riches
 *
 */

package com.example.mike.riderfare;

//*****************************************************************************
//*************************IMPORT LIBRARIES************************************
//*****************************************************************************

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity that passes a JSON to MainActivity.java
 *
 * @author Michael Riches
 *
 */

public class JsonPasserActivity extends AppCompatActivity {

    //*****************************************************************************
    //*************************CLASS METHODS***************************************
    //*****************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_passer);

        //JSON containing rider and fare information
        String rawJSON = "{\n" +
                "  \"Adult\": {\n" +
                "    \"fares\": [\n" +
                "      { \"description\": \"2.5 Hour Ticket\", \"price\": 2.5 },\n" +
                "      { \"description\": \"1 Day Pass\", \"price\": 5.0 },\n" +
                "      { \"description\": \"30 Day Pass\", \"price\": 100 }\n" +
                "    ],\n" +
                "    \"subtext\": null\n" +
                "  },\n" +
                "  \"Child\": {\n" +
                "    \"fares\": [\n" +
                "      { \"description\": \"2.5 Hour Ticket\", \"price\": 1.5 },\n" +
                "      { \"description\": \"1 Day Pass\", \"price\": 2.0 },\n" +
                "      { \"description\": \"30 Day Pass\", \"price\": 40.0 }\n" +
                "    ],\n" +
                "    \"subtext\": \"Ages 8-17\"\n" +
                "  },\n" +
                "  \"Senior\": {\n" +
                "    \"fares\": [\n" +
                "      { \"description\": \"2.5 Hour Ticket\", \"price\": 1.0 },\n" +
                "      { \"description\": \"1 Day Pass\", \"price\": 2.0 },\n" +
                "      { \"description\": \"30 Day Pass\", \"price\": 40.0 }\n" +
                "    ],\n" +
                "    \"subtext\": \"Ages 60+\"\n" +
                "  }\n" +
                "}";

        //Creates the intent for the main activity and passes the JSON
        Intent newintent = new Intent(this, com.example.mike.riderfare.MainActivity.class);
        newintent.putExtra("json", rawJSON);

        //Prevents user from returning to this activity and launches the primary application
        newintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newintent);
        finish();
    }
}
