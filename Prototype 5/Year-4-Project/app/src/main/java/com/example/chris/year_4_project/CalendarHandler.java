package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import java.util.Calendar;

/**
 * Created by Chris on 22/04/2015.
 */
//THIS CLASS HANDLES ADDING NEW EVENT DATA TO THE ANDROID CALENDAR
public class CalendarHandler extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        int[] eventDate;

        Intent i = getIntent();
        String calendarEventTitle = i.getStringExtra("eventName");
        String calendarEventVenue = i.getStringExtra("eventVenue");
        //String calendarEventEmail = i.getStringExtra("eventEmail");
        String calendarEventEmail = "whatever@test.ie";
        String calendarEventDate = i.getStringExtra("eventDate");

        eventDate = getEventDateFromString(calendarEventDate);
        for(int j = 0; j < eventDate.length; j++)
        {
            System.out.println("realIndex: " + j + ", " + eventDate[j]);
        }
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(eventDate[2], eventDate[1], eventDate[0], eventDate[3], eventDate[4]);
        //beginTime.set(2012, 0, 19, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(eventDate[2], eventDate[1], eventDate[0], eventDate[3] + 1, eventDate[4]);

        addToCalendar(calendarEventTitle, calendarEventVenue, calendarEventEmail, beginTime, endTime);
    }

    public int[] getEventDateFromString(String calendarEventDate)
    {
        int[] eventDate = new int[5];
        int currentIndex = 0;
        String currentBlock = "";
        char currentInt;
        System.out.println("Full String" + calendarEventDate);
        for(int i = 0; i < calendarEventDate.length(); i++)
        {
            currentInt = calendarEventDate.charAt(i);
            System.out.println("Current Int: " + currentInt);
            if( currentInt == ' ')
            {
                eventDate[currentIndex] = Integer.parseInt(currentBlock);
                currentIndex++;
                currentBlock = "";
            }
            else if(Character.isDigit(currentInt))
            {
                currentBlock += currentInt;
            }
            else
            {
                eventDate[currentIndex] = Integer.parseInt(currentBlock);
                currentIndex++;
                currentBlock = "";
            }
        }
        eventDate[currentIndex] = Integer.parseInt(currentBlock);
        for(int i = 0; i < eventDate.length; i++)
        {
            System.out.println("Index: " + i + ", " + eventDate[i]);
        }
        return eventDate;
    }

    /*
    public int[] getEventDateFromString(String calendarEventDate)
    {
        int[] eventDate = new int[5];
        int currentIndex = 0;
        int currentBlock = 0;
        char currentInt;

        for(int i = 0; i < calendarEventDate.length(); i++)
        {
           currentInt = calendarEventDate.charAt(i);
            if(currentInt == '/' || currentInt == ':' || currentInt == ' ')
            {
                eventDate[currentIndex] = currentBlock;
                currentBlock = 0;
                currentIndex++;
            }
            else
            {
                if(currentBlock == 0)
                {
                    //currentBlock = calendarEventDate.charAt(i);
                    currentBlock = Character.getNumericValue(calendarEventDate.charAt(i));
                }
                else
                {
                    currentBlock += Character.getNumericValue(calendarEventDate.charAt(i));
                }
            }
        }

        for(int i = 0; i < eventDate.length; i++)
        {
            System.out.println(eventDate[i]);
        }
        return eventDate;
    }
*/
    public void addToCalendar(String calendarEventTitle, String calendarEventVenue, String calendarEventEmail, Calendar beginTime, Calendar endTime)
    {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(Events.TITLE, calendarEventTitle)
                //.putExtra(Events.DESCRIPTION, "Group class")
                .putExtra(Events.EVENT_LOCATION, calendarEventVenue)
                .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, calendarEventEmail);
        startActivity(intent);
    }
}
