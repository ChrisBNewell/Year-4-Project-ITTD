package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplayMobileUsersActivity extends Activity
{


    static final String URL = "http://projectyear4webservice.azurewebsites.net/api/mobileusers/";
    static final String URL_RESERVATIONS = "http://projectyear4webservice.azurewebsites.net/api/reservations/";


    //node Keys
    static final String KEY_ITEM = "MobileUserDTO";
    static final String KEY_ID = "MobileUserID";
    static final String KEY_NAME = "Username";
    static final String KEY_PASSWORD = "Password";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_layout);

        System.out.println("MSG- android launch DisplayMobileUsersActivity");

        WebAPIConnect connection = new WebAPIConnect();
        JSONArray jsonArray;

        Intent i = getIntent();

        System.out.println("retrieving all Mobile Users");
        jsonArray = connection.GetJSONFromUrl(URL);

        ArrayList mobileUser_details = getListData(jsonArray);
        final ListView evlv = (ListView) findViewById(R.id.eventList);
        evlv.setAdapter(new MobileUsersCustomListAdapter(this, mobileUser_details));

        evlv.setOnItemClickListener(new AdapterView.OnItemClickListener() //when a single item is clicked, move onto the details screen.
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = evlv.getItemAtPosition(position);
                MobileUserItem user = (MobileUserItem) o;

                //Retrieve data to be displayed.
                int mobileUserIDToSend = user.getMobileUserID();

                //build the Intent with the gathered information.
                Intent i = new Intent(getApplicationContext(), DisplayReservationsActivity.class);
                i.putExtra("id", mobileUserIDToSend);

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
            MobileUserItem user = new MobileUserItem();

            try
            {
                json = jsonArray.getJSONObject(i);

                user.setMobileUserID(json.getInt(KEY_ID));
                user.setUserName(json.getString(KEY_NAME));
                user.setPassword(json.getString(KEY_PASSWORD));

                menuItems.add(i,user);
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

        System.out.println("Successfully created Mobile User objects!");
        return menuItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_mobile_users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
