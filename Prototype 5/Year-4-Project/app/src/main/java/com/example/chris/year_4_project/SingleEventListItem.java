package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//THIS ACTIVITY IS USED TO DISPLAY A SINGLE EVENT IN GREATER DETAIL AFTER IT HAS BEEN CLICKED FROM THE MAIN LIST OF EVENTS IN THE DisplayEvents ACTIVITY
public class SingleEventListItem extends Activity
{
    static final String URL = "http://year4projectcbn2.azurewebsites.net/api/events/";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event_list_item);

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

        Button CreateReservationButton = (Button) findViewById(R.id.addReservation_button);
        CreateReservationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MobileUserLoginActivity.class);
                i.putExtra("source","Reservation");
                i.putExtra("id", EventItemID);
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
