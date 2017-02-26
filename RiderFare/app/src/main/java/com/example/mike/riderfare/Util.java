/**
 * Prompts a user to select and purchase bus fare based on information from a JSON
 *
 * File: com.example.mike.riderfare.Util.java
 *
 * Author: Michael Riches
 *
 */

package com.example.mike.riderfare;

//*****************************************************************************
//*************************IMPORT LIBRARIES************************************
//*****************************************************************************

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility Class
 *
 * Contains methods required across multiple activities
 *
 * @author Michael Riches
 *
 */
public class Util {

    /**
     * Parses a JSON into the list of Rider objects
     *
     * @param rawJSON JSON String to be parsed
     * @return The List of Rider objects
     */
    public List<Rider> parseRidersFromJSON(String rawJSON)
    {
        //Initialize list to be returned at the end
        List<Rider> riders = new ArrayList<>();

        //Parses the JSON
        try {
            //Converts JSON String into a JSON object to be parsed
            JSONObject json = new JSONObject(rawJSON);

            //Iterates through the contents of the JSON object
            Iterator<?> keys = json.keys();
            while(keys.hasNext())
            {
                String key = (String)keys.next();
                if ( json.get(key) instanceof JSONObject )
                {
                    //Creates a new Rider object to be added to the list
                    Rider newRider = new Rider(key, json.getJSONObject(key).getString("subtext"));
                    JSONArray jsonFares = json.getJSONObject(key).getJSONArray("fares");

                    //Iterates through the Rider's fares and adds them to the new Rider object
                    for (int n = 0; n < jsonFares.length(); n++)
                    {
                        JSONObject currentNode = jsonFares.getJSONObject(n);
                        Fare newFare = new Fare(currentNode.getString("description"),
                                currentNode.getDouble("price"));
                        newRider.addFare(newFare);

                        //Replaces null String values with blank values
                        if(newRider.getDescription() == "null")
                        {
                            newRider.setDescription("");
                        }
                    }

                    //Adds the new rider to the list
                    riders.add(new Rider(newRider));
                }
            }
        }
        catch (JSONException e){
            //If there is an issue with parsing the JSON, throws an exception
            throw new RuntimeException(e);
        }

        //Returns the rider list
        return riders;
    }
}
