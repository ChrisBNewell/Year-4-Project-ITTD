package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//THE LOG IN ACTIVITY FOR A USER TO ENTER A USERNAME AND PASSWORD AND HAVE THOSE CREDENTIALS CHECKED TO SEE IF THEY ARE VALID
public class MobileUserLoginActivity extends Activity
{
    //NEW WEBSITE URL
    static final String URL = "http://year4projectcbn2.azurewebsites.net/api/";
    static final String URL2 = "http://year4projectcbn2.azurewebsites.net/api/reservations/";

    //USER DATA NODE KEYS
    static final String KEY_USERNAME = "Username";
    static final String KEY_PASSWORD = "Password";
    static final String KEY_ID = "MobileUserID";
    static final String KEY_EMAIL = "UserEmail";

    WebAPIConnect connection = new WebAPIConnect();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_user_login);

        Button log_inButton = (Button) findViewById(R.id.loginButton_Confirm);
        final EditText username_Field = (EditText)findViewById(R.id.usernameField);
        final EditText password_Field = (EditText)findViewById(R.id.passwordField);


        Intent i = getIntent();
        final int EventItemID = i.getIntExtra("id", 0);
        final String source = i.getStringExtra("source");


        if(source.equalsIgnoreCase("Reservation"))
        {
            log_inButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    JSONArray jsonArray;

                    String userNameAttempt = username_Field.getText().toString();
                    String passwordAttempt = password_Field.getText().toString();
                    String login_Query = "MobileUsers?Username=" + userNameAttempt + "&" + "Password=" + passwordAttempt;
                    //jsonArray = connection.GetJSONFromUrl(URL + login_Query);

                    MobileUserItem mob = getUserData(URL + login_Query);
                    if(mob != null)
                    {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        WebAPIConnect apiConnect = new WebAPIConnect();
                        ReservationItem newReservation = new ReservationItem();
                        newReservation.setEventID(EventItemID);
                        newReservation.setAttendeeID(mob.getMobileUserID());
                        apiConnect.sendJSONtoURL(URL2, newReservation.toJSON());


                        apiConnect.GetJSONFromUrl(URL + "/events/" + EventItemID);
                        startActivity(i);
                    }
                }
            });
        }
        else
        {
            log_inButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    JSONArray jsonArray;

                    String userNameAttempt = username_Field.getText().toString();
                    String passwordAttempt = password_Field.getText().toString();
                    String login_Query = "MobileUsers?Username=" + userNameAttempt + "&" + "Password=" + passwordAttempt;
                    //jsonArray = connection.GetJSONFromUrl(URL + login_Query);

                    MobileUserItem mob = getUserData(URL + login_Query);
                    if(mob != null)
                    {
                        Intent i = new Intent(getApplicationContext(), MobileUserMenuActivity.class);
                        i.putExtra("id", mob.getMobileUserID());
                        i.putExtra("email", mob.getMobileUserEmail());
                        startActivity(i);

                    }
                }
            });
        }

    }

    private MobileUserItem getUserData(String loginQuery)
    {
        System.out.println("Logging in as User!");

        //ArrayList userItems = new ArrayList();
        MobileUserItem mobItem = new MobileUserItem();

        try
        {
            WebAPIConnect connection = new WebAPIConnect();
            JSONObject json = connection.retrieveJSONObjectFromURL(loginQuery);

            System.out.println("Creating User object!");
            System.out.println("getting username");
            mobItem.setUserName(json.getString(KEY_USERNAME));
            System.out.println("getting password");
            mobItem.setPassword(json.getString(KEY_PASSWORD));
            System.out.println("getting ID");
            mobItem.setMobileUserID(json.getInt(KEY_ID));
            System.out.println("getting Email");
            mobItem.setMobileUserEmail(json.getString(KEY_EMAIL));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Login Failed, Please check your information", Toast.LENGTH_LONG).show();
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Login Failed, Please check your information", Toast.LENGTH_LONG).show();
            return null;
        }

       /* connection.DeleteEvent(URL + "events/", eventID);
        Intent i = new Intent(getApplicationContext(), DisplayEvents.class);
        finish();
        startActivity(i);*/

        return mobItem;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mobile_user_login, menu);
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
        if (id == R.id.action_settings)
        {
           return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

