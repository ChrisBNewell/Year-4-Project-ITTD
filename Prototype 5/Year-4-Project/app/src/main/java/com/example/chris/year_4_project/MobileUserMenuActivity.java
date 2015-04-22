package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//THIS IS THE MENU THAT ALLOWS A LOGGED IN USER TO CREATE A NEW EVENT, VIEW RESERVATIONS TO OTHER PEOPLE'S EVENTS AND VIEW CREATED EVENTS
public class MobileUserMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_user_menu);


        Intent i = getIntent();
        final String userEmail = i.getStringExtra("email");
        final int userID = i .getIntExtra("id", 0);

        Button GetAllCreatedEventsButton = (Button) findViewById(R.id.GetCreatedEventsButton);
        GetAllCreatedEventsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplayCreatedEventsActivity.class);
                i.putExtra("email", userEmail);
                startActivity(i);
            }
        });

        Button GetAllReservationsButton = (Button) findViewById(R.id.GetReservationsButton);
        GetAllReservationsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplayReservationsActivity.class);
                i.putExtra("id", userID);
                startActivity(i);
            }
        });

        Button AddEventsButton = (Button) findViewById(R.id.AddEventsButton);
        AddEventsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddEvent.class);
                i.putExtra("email",userEmail);
                i.putExtra("id", userID);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mobile_user_menu, menu);
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
