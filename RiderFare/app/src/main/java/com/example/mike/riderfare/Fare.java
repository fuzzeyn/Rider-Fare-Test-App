/**
 * Prompts a user to select and purchase bus fare based on information from a JSON
 *
 * File: com.example.mike.riderfare.Fare.java
 *
 * Author: Michael Riches
 *
 */

package com.example.mike.riderfare;

/**
 * A class representing the Fare for a Bus Rider
 *
 * Contains methods to put and get details related to a bus fare
 *
 * @author Michael Riches
 *
 */
public class Fare {
    //*****************************************************************************
    //*************************CLASS MEMBERS***************************************
    //*****************************************************************************
    private String description;
    private double cost;

    /**
     * Default constructor for a bus fare containing NULL values
     */
    public Fare()
    {
        description = "NULL";
        cost = 0;
    }

    /**
     * Constructor for a bus fare with non-NULL values
     *
     * @param description Description of the Fare type
     * @param cost Cost of the bus fare
     */
    public Fare(String description, double cost)
    {
        this.description = description;
        this.cost = cost;
    }

    /**
     * Copy constructor for a Fare
     *
     * @param fare Another Fare to be copied
     */
    public Fare(Fare fare)
    {
        this.description = fare.description;
        this.cost = fare.cost;
    }

    /**
     * Get the description of the Fare
     *
     * @return Description of the Fare
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get the cost of the Fare
     *
     * @return Cost of the Fare
     */
    public double getCost()
    {
        return cost;
    }

    /**
     * Set the description of the Fare
     *
     * @param description Description of the Fare
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Set the cost of the Fare
     *
     * @param cost Cost of the Fare
     */
    public void setCost(double cost)
    {
        this.cost = cost;
    }

}
