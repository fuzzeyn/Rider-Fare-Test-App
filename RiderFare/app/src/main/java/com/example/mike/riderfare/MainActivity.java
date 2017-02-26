/**
 * Prompts a user to select and purchase bus fare based on information from a JSON
 *
 * File: com.example.mike.riderfare.MainActivity.java
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activity that gets what kind of rider the tickets will be purchased for
 *
 * @author Michael Riches
 *
 */

public class MainActivity extends AppCompatActivity {

    //*****************************************************************************
    //*************************CLASS MEMBERS***************************************
    //*****************************************************************************

    ListView riderListView;
    SimpleAdapter listAdapter;

    //*****************************************************************************
    //*************************CLASS METHODS***************************************
    //*****************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if the received intent is valid
        Intent oldintent = getIntent();
        if(oldintent == null)
        {
            finish();
        }

        //Initialize local variables
        riderListView = (ListView) findViewById(R.id.riderListView);
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        //Creates a Utility object to parse the JSON into a list of Rider objects
        Util utilObject = new Util();
        final String rawJSON = oldintent.getStringExtra("json");
        List<Rider> riders = new ArrayList<Rider>(utilObject.parseRidersFromJSON(rawJSON));

        //Iterates through the Rider list to create map list for the ListView
        for (Rider rider : riders) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("name", rider.getName());
            datum.put("description", rider.getDescription());
            data.add(datum);
        }

        //Creates and applies an adapter to display the rider information to the user
        listAdapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2,
                new String[] {"name", "description"},
                new int[] {android.R.id.text1, android.R.id.text2});
        riderListView.setAdapter(listAdapter);

        //Listens for the user to select the rider type
        riderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newintent = new Intent(com.example.mike.riderfare.MainActivity.this,
                        com.example.mike.riderfare.SelectFareActivity.class);

                //Passes the original JSON and selection choice to the new activity
                newintent.putExtra("json", rawJSON);
                newintent.putExtra("position", position);
                startActivity(newintent);
            }
        });
    }
}
