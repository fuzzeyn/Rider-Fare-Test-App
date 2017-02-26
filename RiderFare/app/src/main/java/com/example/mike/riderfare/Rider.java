/**
 * Prompts a user to select and purchase bus fare based on information from a JSON
 *
 * File: com.example.mike.riderfare.Rider.java
 *
 * Author: Michael Riches
 *
 */

package com.example.mike.riderfare;

//*****************************************************************************
//*************************IMPORT LIBRARIES************************************
//*****************************************************************************

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a Bus Rider
 *
 * Contains methods to put and get details related to a Rider
 * Contains a list of fares a rider can pay
 *
 * @author Michael Riches
 *
 */

public class Rider {
    //*****************************************************************************
    //*************************CLASS MEMBERS***************************************
    //*****************************************************************************
    private String name;
    private String description;
    private List<Fare> fares;

    /**
     * Default constructor for a Rider containing NULL values
     */
    public Rider()
    {
        name = "NULL";
        description = "NULL";
        fares = new ArrayList<Fare>();
    }

    /**
     * Constructor for a Rider without fare information
     *
     * @param name The name of the type of Rider
     * @param description Description of the type of Rider
     */
    public Rider(String name, String description)
    {
        this.name = name;
        this.description = description;
        fares = new ArrayList<Fare>();
    }

    /**
     * Constructor for a Rider including fare information
     *
     * @param name The name of the type of Rider
     * @param description Description of the type of Rider
     * @param fares List of fares pertaining to the Rider
     */
    public Rider(String name, String description, List<Fare> fares)
    {
        this.name = name;
        this.description = description;
        this.fares = new ArrayList<Fare>();
        for (Fare fare: fares) {
            this.fares.add(new Fare(fare));
        }
    }

    /**
     * Copy Constructor for a Rider
     *
     * @param rider Another Rider to be copied
     */
    public Rider(Rider rider)
    {
        this.name = rider.name;
        this.description = rider.description;
        this.fares = new ArrayList<Fare>();
        for (Fare fare: rider.getFares()) {
            this.fares.add(new Fare(fare));
        }
    }

    /**
     * Get the name of the Rider type
     *
     * @return Name of Rider Type
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the description of the Rider type
     *
     * @return Description of the Rider Type
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get the list of fares pertaining to the Rider type
     *
     * @return List of Fares for the Rider type
     */
    public List<Fare> getFares()
    {
        return fares;
    }

    /**
     * Add a new fare to the list of fares
     *
     * @param fare New Fare object to be added
     */
    public void addFare(Fare fare)
    {
        fares.add(new Fare(fare));
    }

    /**
     * Sets a new description for the Rider type
     *
     * @param description The new description of the Rider type
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Sets the new name for the Rider type
     *
     * @param name The new name of the Rider type
     */
    public void setName(String name)
    {
        this.name = name;
    }


}
