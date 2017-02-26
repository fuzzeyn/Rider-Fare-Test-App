/**
 * Prompts a user to select and purchase bus fare based on information from a JSON
 *
 * File: com.example.mike.riderfare.ConfirmSelectionAcitivty.java
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
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity that gets what how many tickets the user wants to purchase and finalizes the choice
 *
 * @author Michael Riches
 *
 */

public class ConfirmSelectionActivity extends AppCompatActivity {

    //*****************************************************************************
    //*************************CLASS MEMBERS***************************************
    //*****************************************************************************

    TextView RiderView;
    TextView FareView;
    TextView AmountView;
    Button PurchaseButton;
    Button AddButton;
    Button SubtractButton;
    int tickets;
    double price;

    //*****************************************************************************
    //*************************CLASS METHODS***************************************
    //*****************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_selection);

        //Check if the received intent is valid
        Intent oldintent = getIntent();
        if(oldintent == null)
        {
            finish();
        }

        //Initialize local variables
        tickets = 1;
        RiderView = (TextView) findViewById(R.id.RiderTextView);
        FareView = (TextView) findViewById(R.id.FareTextView);
        AddButton = (Button) findViewById(R.id.addButton);
        SubtractButton = (Button) findViewById(R.id.subtractButton);

        //Creates a Utility object to parse the JSON into a list of Rider objects
        String rawJSON = oldintent.getStringExtra("json");
        Util utilObject = new Util();
        List<Rider> riders = new ArrayList<Rider>(utilObject.parseRidersFromJSON(rawJSON));

        //Updates the view to display information based on user choices
        RiderView.setText(riders.get(oldintent.getIntExtra("subposition",0)).getName());
        FareView.setText(riders.get(oldintent.getIntExtra("subposition",0)).getFares()
                .get(oldintent.getIntExtra("position", 0)).getDescription());
        price = riders.get(oldintent.getIntExtra("subposition",0))
                .getFares().get(oldintent.getIntExtra("position", 0)).getCost();
        adjustView();


        //Listens if the user wants to purchase more tickets
        AddButton.setOnClickListener(new View.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View v) {
                                              tickets++;
                                              adjustView();
                                          }
                                      }
        );

        //Listens if the user wants to purchase less tickets (min 1)
        SubtractButton.setOnClickListener(new View.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v) {
                                             if(tickets>1) {
                                                 tickets--;
                                                 adjustView();
                                             }
                                         }
                                     }
        );

    }

    /**
     * Updates activity text to match the requested number of tickets by the user
     * */
    protected void adjustView()
    {
        //Initialize local variables
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        AmountView = (TextView) findViewById(R.id.textView_amount);
        PurchaseButton = (Button) findViewById(R.id.purchaseButton);
        AmountView.setText(String.valueOf(tickets));

        //Update view
        if(tickets == 1)
        {
            PurchaseButton.setText("Buy " + String.valueOf(tickets)
                    + " Ticket - " + formatter.format(price * tickets));
        }
        else
        {
            PurchaseButton.setText("Buy " + String.valueOf(tickets)
                    + " Tickets - " + formatter.format(price * tickets));
        }
    }


}
