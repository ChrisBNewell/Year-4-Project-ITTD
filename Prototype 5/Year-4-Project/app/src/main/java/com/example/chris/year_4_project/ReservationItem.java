package com.example.chris.year_4_project;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chris on 26/01/2015.
 */
//RESERVATION OBJECT CLASS
public class ReservationItem
{
    private int attendeeID;
    private int eventID;
    private String attendee;
    private String eventReservation;

    //ACCESSOR METHODS
    public int getAttendeeID() { return attendeeID; }
    public int geteventID() { return eventID; }
    public String getAttendee() { return attendee; }
    public String getEventReservation() { return eventReservation; }

    //MUTATOR METHODS
    public void setAttendeeID(int theAttendeeID) { attendeeID = theAttendeeID; }
    public void setEventID(int theEventID) { eventID = theEventID; }
    public void setAttendee(String theAttendee) { attendee = theAttendee; }
    public void setEventReservation(String theEventReservation) { eventReservation = theEventReservation; }

    public String toJSON()
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("MobileUserID", getAttendeeID());
            jsonObject.put("EventID", geteventID());


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
