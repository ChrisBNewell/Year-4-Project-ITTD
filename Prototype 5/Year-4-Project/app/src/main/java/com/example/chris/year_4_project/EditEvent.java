package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//THIS ACTIVITY ALLOWS A USER TO ENTER NEW DATA FOR A PREVIOUSLY CREATED EVENT, IT IS THEN STORED IN THE DATABASE AND OVERWRITES THE OLD VERSION
public class EditEvent extends Activity
{
    static final String URL = "http://year4projectcbn2.azurewebsites.net/api/events/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        Intent i = getIntent();
        final int eventItemID = i.getIntExtra("eventID", 0);
        final String eventItemName = i.getStringExtra("eventName");
        final String eventItemVenue = i.getStringExtra("eventVenue");
        final String eventItemDate = i.getStringExtra("eventDate");

        System.out.println("Editing Event: " + eventItemID + ", " + eventItemName + ", " + eventItemVenue + ", " + eventItemDate);
        final EditText editEventNameTxt = (EditText) findViewById(R.id.EditItem_eventName_Input);
        final EditText editEventVenueTxt = (EditText) findViewById(R.id.EditItem_eventVenue_Input);
        final EditText editEventDateTxt = (EditText) findViewById(R.id.EditItem_eventDate_Input);


        editEventNameTxt.setText(eventItemName);
        editEventVenueTxt.setText(eventItemVenue);
        editEventDateTxt.setText(eventItemDate);


        Button editEventButton = (Button) findViewById(R.id.EditItem_ok_button);
        editEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                WebAPIConnect apiConnect = new WebAPIConnect();

                String updatedEventName = editEventNameTxt.getText().toString();
                String updatedEventVenue = editEventVenueTxt.getText().toString();
                String updatedEventDate = editEventDateTxt.getText().toString();

                EventItem updateEvent = new EventItem();
                updateEvent.setEventID(eventItemID);
                updateEvent.setEventName(updatedEventName);
                updateEvent.setEventVenue(updatedEventVenue);
                updateEvent.setEventDate(updatedEventDate);

                System.out.println("EDIT EVENT NEW EVENT! : Event ID " + updateEvent.getEventID() + ", Event Name " + updateEvent.getEventName() + ", Event Venue " + updateEvent.getEventVenue() + ", Event Date " + updateEvent.getEventDate());

                apiConnect.EditEvent(URL, eventItemID, updateEvent.toJSON());

                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_event, menu);
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
