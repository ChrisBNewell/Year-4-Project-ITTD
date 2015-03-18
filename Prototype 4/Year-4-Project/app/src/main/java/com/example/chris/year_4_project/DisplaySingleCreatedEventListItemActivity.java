package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chris.year_4_project.EditEvent;
import com.example.chris.year_4_project.MobileUserLoginActivity;
import com.example.chris.year_4_project.R;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DisplaySingleCreatedEventListItemActivity extends Activity
{
    static final String URL = "http://projectyear4webservice.azurewebsites.net/api/events/";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_created_list_item_layout);

        TextView txtEventItemName = (TextView) findViewById(R.id.eventItem_name);
        TextView txtEventItemVenue = (TextView) findViewById(R.id.eventItem_venue);
        TextView txtEventItemDate = (TextView) findViewById(R.id.eventItem_date);

        Intent i = getIntent();
        final int EventItemID = i.getIntExtra("id", 0);
        final String EventItemName = i.getStringExtra("name");
        final String EventItemVenue = i.getStringExtra("venue");
        final String EventItemDate = i.getStringExtra("date");

        txtEventItemName.setText(EventItemName);
        txtEventItemVenue.setText(EventItemVenue);
        txtEventItemDate.setText(EventItemDate);

        Button deleteEventButton = (Button) findViewById(R.id.delete_button);
        deleteEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebAPIConnect connect = new WebAPIConnect();
                connect.DeleteEvent(URL, EventItemID);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("id", EventItemID);
                startActivity(i);
            }
        });

        Button editEventButton = (Button) findViewById(R.id.edit_button);
        editEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EditEvent.class);
                i.putExtra("eventID", EventItemID);
                i.putExtra("eventName", EventItemName);
                i.putExtra("eventVenue", EventItemVenue);
                i.putExtra("eventDate", EventItemDate);
                System.out.println("Starting EditEvent: Editing Event: " + EventItemID + ", " + EventItemName + ", " + EventItemVenue + ", " + EventItemDate);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_event_list_item, menu);
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
