package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;


//THIS ACTIVITY DISPLAYS A LIST OF EVENTS CREATED BY A SPECIFIC USER, THIS ACTIVITY CAN ONLY BE ACCESSED ONCE THE USER LOGS IN

public class DisplayCreatedEventsActivity extends Activity {

    static final String URL = "http://year4projectcbn2.azurewebsites.net/api/Events?email=";

    //node Keys
    static final String KEY_ITEM = "EventDTO";
    static final String KEY_ID = "EventID";
    static final String KEY_NAME = "EventName";
    static final String KEY_VENUE = "EventVenue";
    static final String KEY_DATE = "EventDate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_layout);

        System.out.println("MSG- android launch DisplayCreatedEventsActivity");

        WebAPIConnect connection = new WebAPIConnect();
        JSONArray jsonArray;

        Intent i = getIntent();
        String userEmail = i.getStringExtra("email");
        System.out.println("retrieving all Created Events");
        jsonArray = connection.GetJSONFromUrl(URL + userEmail);


        ArrayList event_details = getListData(jsonArray);
        final ListView evlv = (ListView) findViewById(R.id.eventList);
        evlv.setAdapter(new CustomListAdapter(this, event_details));

        Button returnButton = (Button) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        evlv.setOnItemClickListener(new AdapterView.OnItemClickListener() //when a single item is clicked, move onto the details screen.
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = evlv.getItemAtPosition(position);
                EventItem event = (EventItem) o;

                //Retrieve data to be displayed.
                int eventIDToSend = event.getEventID();
                String eventNameToSend = event.getEventName();
                String eventVenueToSend = event.getEventVenue();
                String eventDateToSend = event.getEventDate();

                //build the Intent with the gathered information.
                Intent i = new Intent(getApplicationContext(), DisplaySingleCreatedEventListItemActivity.class);
                i.putExtra("id", eventIDToSend);
                i.putExtra("name", eventNameToSend);
                i.putExtra("venue", eventVenueToSend);
                i.putExtra("date", eventDateToSend);

                startActivity(i);
            }
        });

    }


    private ArrayList getListData(JSONArray jsonArray)
    {
        System.out.println("Getting List Data");
        ArrayList menuItems = new ArrayList();
        for(int i  = 0; i < jsonArray.length(); i++)
        {
            JSONObject json = null;
            EventItem event = new EventItem();

            try
            {
                json = jsonArray.getJSONObject(i);

                event.setEventID(json.getInt(KEY_ID));
                event.setEventName(json.getString(KEY_NAME));
                event.setEventVenue(json.getString(KEY_VENUE));
                event.setEventDate(json.getString(KEY_DATE));

                menuItems.add(i,event);
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

        System.out.println("Successfully created event objects!");
        return menuItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_created_events, menu);
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
