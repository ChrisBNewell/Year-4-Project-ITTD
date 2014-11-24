package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class DisplayEvents extends Activity {

    static final String URL = "http://projectyear4webservice.azurewebsites.net/api/events";


    //node Keys
    static final String KEY_ITEM = "Event";
    static final String KEY_ID = "eventID";
    static final String KEY_NAME = "eventName";
    static final String KEY_VENUE = "eventVenue";
    static final String KEY_DATE = "eventDate";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_layout);

        System.out.println("MSG- android launch EventListActivity");

        WebAPIConnect connection = new WebAPIConnect();
        JSONArray jsonArray;

        Intent i = getIntent();
        if(i.getExtras() != null) //intent came from BookSearchActivity
        {
            System.out.println("Launching search by criteria");
            String queryString = constructQuery(i);
            System.out.println("query by: " + queryString);
            jsonArray = connection.GetJSONFromUrl(URL + queryString);
        }
        else
        {
            System.out.println("retrieving all Events");
            jsonArray = connection.GetJSONFromUrl(URL);
        }


        ArrayList event_details = getListData(jsonArray);
        final ListView evlv = (ListView) findViewById(R.id.eventList);
        evlv.setAdapter(new CustomListAdapter(this, event_details));

        evlv.setOnItemClickListener(new OnItemClickListener() //when a single item is clicked, move onto the details screen.
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = evlv.getItemAtPosition(position);
                EventItem event = (EventItem) o;

                //Retrieve data to be displayed.
                String eventNameToSend = event.getEventName();
                String eventVenueToSend = event.getEventVenue();
                String eventDateToSend = event.getEventDate();


                //build the Intent with the gathered information.
                Intent i = new Intent(getApplicationContext(), SingleEventListItem.class);
                i.putExtra("name", eventNameToSend);
                i.putExtra("venue", eventVenueToSend);
                i.putExtra("date", eventDateToSend);


                startActivity(i);
            }
        });
    }

    private String constructQuery(Intent i)
    {
        String queryData = "";
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("?");
        String searchBy = i.getStringExtra("selection").toString();
        String searchCriteria = i.getStringExtra("criteria").toString();
        strBuild.append(searchBy + "=" + searchCriteria);

		/* THIS CODE IS USED FOR WHEN A SEARCH FOR MULTIPLE CRITERIA IS MADE.
		if(!i.getStringExtra("title").equals(""))
		{
			String bookItemTitle = i.getStringExtra("title").toString();
			strBuild.append("Title=" + bookItemTitle + "&");
		}
		if(!i.getStringExtra("genre").equals(""))
		{
			String bookItemGenre = i.getStringExtra("genre");
			strBuild.append("Genre=" + bookItemGenre + "&");
		}
		if(!i.getStringExtra("author").equals(""))
		{
			String bookItemAuthor = i.getStringExtra("author");
			strBuild.append("Author=" + bookItemAuthor + "&");
		}

		*/


        //Replace spaces with '+' character for query
        queryData = strBuild.toString().replace(' ', '+');
        return queryData;
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
}
