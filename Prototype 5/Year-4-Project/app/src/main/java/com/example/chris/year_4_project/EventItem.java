package com.example.chris.year_4_project;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chris on 03/11/2014.
 */
//EVENT OBJECT CLASS
public class EventItem
{
    private int eventID;
    private String eventName;
    private String eventVenue;
    private String eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String creatorEmail;

    //Accessor methods
    public int getEventID() { return eventID; }
    public String getEventName(){ return eventName; }
    public String getEventVenue(){ return eventVenue; }
    public String getEventDate(){ return eventDate; }
    public String getCreatorEmail(){ return creatorEmail; }
    public String getEventStartTime(){ return eventStartTime; }
    public String getEventEndTime(){ return eventEndTime; }

    //Mutator methods
    public void setEventID(int eventID) { this.eventID = eventID; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public void setEventVenue(String eventVenue) { this.eventVenue = eventVenue; }
    public void setEventDate(String eventDate){ this.eventDate = eventDate; }
    public void setCreatorEmail(String creatorEmail) { this.creatorEmail = creatorEmail; }
    public void setEventStartTime(String eventStartTime){ this.eventStartTime = eventStartTime; }
    public void setEventEndTime(String eventEndTime){ this.eventEndTime = eventEndTime; }

    //ToString
    public String ToString()
    {
        return "Name: " + getEventName() + ", Venue: " + getEventVenue() + ", Date: " + getEventDate() + ", StartTime: " + getEventStartTime() + ", EndTime: " + getEventEndTime();
    }

    public String toJSON()
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("eventName", getEventName());
            jsonObject.put("eventVenue", getEventVenue());
            jsonObject.put("eventDate", getEventDate() + " " + getEventStartTime());
            jsonObject.put("creatorEmail", getCreatorEmail());

            System.out.println(jsonObject.toString());

            return jsonObject.toString();
        }
        catch(JSONException e)
        {
            e.printStackTrace();

            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
