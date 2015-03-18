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
    static final String URL2 = "http://projectyear4webservice.azurewebsites.net/api/reservations/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final EditText eventNameTxt = (EditText) findViewById(R.id.AddItem_eventName_Input);
        final EditText eventVenueTxt = (EditText) findViewById(R.id.AddItem_eventVenue_Input);
        final EditText eventDateTxt = (EditText) findViewById(R.id.AddItem_eventDate_Input);

        //Get Email and ID from logged in user
        Intent i = getIntent();
        final String currentMobileUserEmail = i.getStringExtra("email");
        final int currentMobileUserID = i.getIntExtra("id", 0);

        Button returnButton = (Button) findViewById(R.id.AddItem_cancel_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        Button addNewEventButton = (Button) findViewById(R.id.AddItem_ok_button);
        addNewEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                WebAPIConnect apiConnect = new WebAPIConnect();

                String newEventName = eventNameTxt.getText().toString();
                String newEventVenue = eventVenueTxt.getText().toString();
                String newEventDate = eventDateTxt.getText().toString();

                EventItem newEvent = new EventItem();
                newEvent.setEventName(newEventName);
                newEvent.setEventVenue(newEventVenue);
                newEvent.setEventDate(newEventDate);
                newEvent.setCreatorEmail(currentMobileUserEmail);
                apiConnect.sendJSONtoURL(URL, newEvent.toJSON());


                ReservationItem newReservation = new ReservationItem();
                newReservation.setEventID(0);
                newReservation.setAttendeeID(currentMobileUserID);
                apiConnect.sendJSONtoURL(URL2, newReservation.toJSON());

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
