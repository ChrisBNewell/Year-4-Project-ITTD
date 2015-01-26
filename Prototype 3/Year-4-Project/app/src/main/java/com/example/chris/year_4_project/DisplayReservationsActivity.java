package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplayReservationsActivity extends Activity
{

    static final String URL = "http://projectyear4webservice.azurewebsites.net/api/reservations/";

    //node Keys
    static final String KEY_ITEM = "ReservationDTO";
    static final String KEY_ATTENDEE = "Attendee";
    static final String KEY_EVENTRESERVATION = "EventReservation";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_layout);

        System.out.println("MSG- android launch DisplayReservationsActivity");

        WebAPIConnect connect = new WebAPIConnect();
        JSONArray jsonArray;

        Intent i = getIntent();
        System.out.println("retrieving all Reservations for MobileUser");
        jsonArray = connect.GetJSONFromUrl(URL + i.getIntExtra("id", 0));

        ArrayList Reservation_details = getListData(jsonArray);
        final ListView evlv = (ListView) findViewById(R.id.eventList);
        evlv.setAdapter(new ReservationsCustomListAdapter(this, Reservation_details));


    }

    private ArrayList getListData(JSONArray jsonArray)
    {
        System.out.println("Getting List Data");
        ArrayList menuItems = new ArrayList();
        for(int i  = 0; i < jsonArray.length(); i++)
        {
            JSONObject json = null;
            ReservationItem reservationItem = new ReservationItem();

            try
            {
                json = jsonArray.getJSONObject(i);

                reservationItem.setAttendee(json.getString(KEY_ATTENDEE));
                reservationItem.setEventReservation(json.getString(KEY_EVENTRESERVATION));

                menuItems.add(i,reservationItem);
                //System.out.println("Number of items retrieved so far: " + i);
            }
            catch(JSONException e)
            {
                System.out.println("ERROR: Could not create objects from JSON data!");
                e.printStackTrace();
            }
            catch(Exception e)
            {
                System.out.println("ERROR: The data could not be retrieved!");
                e.printStackTrace();
            }
        }


        int count = 0;
        for(Object obj : menuItems)
        {
            //System.out.println(menuItems.get(count).toString());
            count++;
        }

        System.out.println("Successfully created Reservation objects!");
        return menuItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_reservations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
