package com.example.chris.year_4_project;

/**
 * Created by Chris on 03/11/2014.
 */
public class EventItem
{
    private String eventName;
    private String eventVenue;
    private String eventDate;

    //Accessor methods
    public String getEventName()
    {
        return eventName;
    }
    public String getEventVenue()
    {
        return eventVenue;
    }
    public String getEventDate()
    {
        return eventDate;
    }

    //Mutator methods
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }
    public void setEventVenue(String eventVenue)
    {
        this.eventVenue = eventVenue;
    }
    public void setEventDate(String eventDate)
    {
        this.eventDate = eventDate;
    }

    //ToString
    public String ToString() //TODO: SECOND PASS ON METHOD CALLS - NECESSARY?
    {
        return "Name: " + getEventName() + ", Venue: " + getEventVenue() + ", Date: " + getEventDate();
    }
}
