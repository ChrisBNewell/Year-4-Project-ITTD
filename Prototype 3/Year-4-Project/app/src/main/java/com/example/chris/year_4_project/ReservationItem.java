package com.example.chris.year_4_project;

/**
 * Created by Chris on 26/01/2015.
 */
public class ReservationItem
{
    private String attendee;
    private String eventReservation;



    public String getAttendee()
    {
        return attendee;
    }
    public String getEventReservation()
    {
        return eventReservation;
    }

    public void setAttendee(String theAttendee)
    {
        attendee = theAttendee;
    }
    public void setEventReservation(String theEventReservation)
    {
        eventReservation = theEventReservation;
    }
}
