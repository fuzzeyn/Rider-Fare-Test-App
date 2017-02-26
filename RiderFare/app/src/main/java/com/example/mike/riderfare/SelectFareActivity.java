/**
 * Prompts a user to select and purchase bus fare based on information from a JSON
 *
 * File: com.example.mike.riderfare.SelectFareActivity.java
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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activity that gets what kind of ticket the user will purchase
 *
 * @author Michael Riches
 *
 */

public class SelectFareActivity extends AppCompatActivity {

    //*****************************************************************************
    //*************************CLASS MEMBERS***************************************
    //*****************************************************************************

    ListView fareListView;
    SimpleAdapter listAdapter;

    //*****************************************************************************
    //*************************CLASS METHODS***************************************
    //*****************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_fare);

        //Check if the received intent is valid
        final Intent oldintent = getIntent();
        if(oldintent == null)
        {
            finish();
        }

        //Initialize local variables
        fareListView = (ListView) findViewById(R.id.fareListView);
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        //Creates a Utility object to parse the JSON into a list of Rider objects
        Util utilObject = new Util();
        final String rawJSON = oldintent.getStringExtra("json");
        List<Rider> riders = new ArrayList<Rider>(utilObject.parseRidersFromJSON(rawJSON));

        //Iterates through the fare options of the selected rider to create map list
        for (Fare fare : riders.get(oldintent.getIntExtra("position", 0)).getFares()) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("description", fare.getDescription());
            datum.put("cost", formatter.format(fare.getCost()));
            data.add(datum);
        }

        //Creates and applies an adapter to display the fare information to the user
        listAdapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2,
                new String[] {"description", "cost"},
                new int[] {android.R.id.text1, android.R.id.text2});
        fareListView.setAdapter(listAdapter);

        //Listens for the user to select the fare type
        fareListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int subposition = oldintent.getIntExtra("position", 0);
                Intent i = new Intent(com.example.mike.riderfare.SelectFareActivity.this,
                        com.example.mike.riderfare.ConfirmSelectionActivity.class);

                //Passes the original JSON and selection choices to the new activity
                i.putExtra("json", rawJSON);
                i.putExtra("subposition", subposition);
                i.putExtra("position", position);
                startActivity(i);
            }
        });
    }
}
