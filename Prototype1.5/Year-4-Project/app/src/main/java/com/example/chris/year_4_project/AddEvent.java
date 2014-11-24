package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddEvent extends Activity {

    static final String URL = "http://projectyear4webservice.azurewebsites.net/api/events/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final EditText eventNameTxt = (EditText) findViewById(R.id.AddItem_eventName_Input);
        final EditText eventVenueTxt = (EditText) findViewById(R.id.AddItem_eventVenue_Input);
        final EditText eventDateTxt = (EditText) findViewById(R.id.AddItem_eventDate_Input);


        Button GetAllEventsButton = (Button) findViewById(R.id.AddItem_ok_button);
        GetAllEventsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplayEvents.class);
                WebAPIConnect apiConnect = new WebAPIConnect();

                String newEventName = eventNameTxt.getText().toString();
                String newEventVenue = eventVenueTxt.getText().toString();
                String newEventDate = eventDateTxt.getText().toString();

                EventItem newEvent = new EventItem();
                newEvent.setEventName(newEventName);
                newEvent.setEventVenue(newEventVenue);
                newEvent.setEventDate(newEventDate);
                apiConnect.sendJSONtoURL(URL, newEvent.toJSON());

                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
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
