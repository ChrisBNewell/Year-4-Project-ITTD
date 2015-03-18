package com.example.chris.year_4_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.AsyncTask;
import com.google.android.gms.gcm.*;
import com.microsoft.windowsazure.messaging.*;
import com.microsoft.windowsazure.notifications.NotificationsManager;

/*
GCM & Windows azure notification hubs imports
 */

public class MainActivity extends Activity {

    /*
     Windows azure notification hub variables;
     */
    private String SENDER_ID = "80558211852";
    private GoogleCloudMessaging gcm;
    private NotificationHub hub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationsManager.handleNotifications(this, SENDER_ID, MyHandler.class);

        gcm = GoogleCloudMessaging.getInstance(this);

        String connectionString = "Endpoint=sb://year4projectnotificationhub-ns.servicebus.windows.net/;SharedAccessKeyName=DefaultListenSharedAccessSignature;SharedAccessKey=bh/wNm4vNf7tQZFz9qYj6DI8udLetRDHjsNl9ps4y2A=";
        hub = new NotificationHub("year4projectnotificationhub", connectionString, this);

        registerWithNotificationHubs();


        Button GetAllEventsButton = (Button) findViewById(R.id.GetAllEventsButton);
        GetAllEventsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplayEvents.class);
                startActivity(i);
            }
        });

        Button LoginButton = (Button) findViewById(R.id.LogInButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MobileUserLoginActivity.class);
                i.putExtra("source","Main");
                startActivity(i);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void registerWithNotificationHubs() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... params) {
                try {
                    String regid = gcm.register(SENDER_ID);
                    hub.register(regid);
                } catch (Exception e) {
                    return e;
                }
                return null;
            }
        }.execute(null, null, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
