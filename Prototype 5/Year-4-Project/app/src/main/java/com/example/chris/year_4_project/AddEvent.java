package com.example.chris.year_4_project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v4.app.FragmentActivity;
//import com.example.chris.year_4_project.DatePickerFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

//THIS ACTIVITY PERFORMS AN ADD OPERATION OF A NEWLY CREATED EVENT OBJECT TO THE PROJECT DATABASE HOSTED BY AZURE,
//IT ALSO SETS A NEW RESERVATION FOR THE MOBILE USER TO THE NEW EVENT.

public class AddEvent extends Activity {

    static String dateData;
    static final String URL = "http://year4projectcbn2.azurewebsites.net/api/events/";
    static final String URL2 = "http://year4projectcbn2.azurewebsites.net/api/reservations/";

    //USER DATA NODE KEYS
    static final String KEY_EVENTID = "EventID";

    static String eventDate;
    static String eventTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final EditText eventNameTxt = (EditText) findViewById(R.id.AddItem_eventName_Input);
        final EditText eventVenueTxt = (EditText) findViewById(R.id.AddItem_eventVenue_Input);
        final TextView eventDateTxt = (TextView) findViewById(R.id.AddItem_eventDate_Txt);
        final TextView eventTimeTxt = (TextView) findViewById(R.id.AddItem_eventTime_Txt);
        //final DatePicker eventDatePicker = (DatePicker) findViewById(R.id.AddItem_eventDate_Input);
        final Button dateButton = (Button) findViewById(R.id.datePickerButton);
        final Button timeButton = (Button) findViewById(R.id.timePickerButton);

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

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        Button addNewEventButton = (Button) findViewById(R.id.AddItem_ok_button);
        addNewEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CalendarHandler.class);
                WebAPIConnect apiConnect = new WebAPIConnect();

                String newEventName = eventNameTxt.getText().toString();
                String newEventVenue = eventVenueTxt.getText().toString();
                //String newEventDate = eventDateTxt.getText().toString();
                String newEventDate = eventDate + " " + eventTime;
                //String newEventDate = eventDatePicker.getYear() + "/" + eventDatePicker.getMonth() + "/"  + eventDatePicker.getDayOfMonth();
                createNewReservation(getCreatedEventID(createNewEvent(newEventName, newEventVenue, newEventDate, currentMobileUserEmail)), currentMobileUserID);

                i.putExtra("eventName", newEventName);
                i.putExtra("eventVenue", newEventVenue);
                //i.putExtra("eventEmail", newEvent);
                i.putExtra("eventDate", newEventDate);

                startActivity(i);
            }
        });
    }

    //fragment for the Time Picker UI Component dialog window - default current time is now and format is 24 hours
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            eventTime = hourOfDay + ":" + minute;
        }
    }

    //fragment for the Date Picker UI Component dialog window - default date is today
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            eventDate = day + "/" + month + "/" + year;
        }
    }

    public JSONObject createNewEvent(String newEventName, String newEventVenue, String newEventDate, String currentMobileUserEmail)
    {
        String responseStr;
        JSONObject responseJson;

        EventItem newEvent = new EventItem();
        newEvent.setEventName(newEventName);
        newEvent.setEventVenue(newEventVenue);
        newEvent.setEventDate(newEventDate);
        newEvent.setCreatorEmail(currentMobileUserEmail);

        WebAPIConnect apiConnect = new WebAPIConnect();
        HttpResponse response = apiConnect.sendJSONtoURL(URL, newEvent.toJSON());

        try
        {
            responseStr = EntityUtils.toString(response.getEntity());
            System.out.println("AddEvent ResponseStr: " + responseStr);
            responseJson = new JSONObject(responseStr);
            return responseJson;
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void createNewReservation(int createdEventID, int currentMobileUserID)
    {
        ReservationItem newReservation = new ReservationItem();
        newReservation.setEventID(createdEventID);
        newReservation.setAttendeeID(currentMobileUserID);

        WebAPIConnect apiConnect = new WebAPIConnect();
        apiConnect.sendJSONtoURL(URL2, newReservation.toJSON());
    }

    private int getCreatedEventID(JSONObject response)
    {
       int createdeventID;

        try
        {
            WebAPIConnect connection = new WebAPIConnect();
            JSONObject json = response;
            System.out.println("Retrieving created event ID!");
            createdeventID = json.getInt(KEY_EVENTID);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }

        return createdeventID;
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


