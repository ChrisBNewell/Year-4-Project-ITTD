//package com.example.chris.year_4_project;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//
//public class MobileUserLoginActivity extends Activity
//{
//
//    /* NEW WEBSITE URL  */
//    static final String URL = "http://projectyear4webservice.azurewebsites.net/api/MobileUsers";
//
//    /* USER DATA NODE KEYS  */
//    static final String KEY_USERNAME = "UserName";
//    static final String KEY_PASSWORD = "Password";
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mobile_user_login);
//
//        Button log_inButton = (Button) findViewById(R.id.loginButton_Confirm);
//        final EditText username_Field = (EditText)findViewById(R.id.usernameField);
//        final EditText password_Field = (EditText)findViewById(R.id.passwordField);
//
//        log_inButton.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                WebAPIConnect connection = new WebAPIConnect();
//                JSONArray jsonArray;
//
//                String userNameAttempt = username_Field.getText().toString();
//                String passwordAttempt = password_Field.getText().toString();
//                String login_Query = "?Username=" + userNameAttempt + "&" + "Password=" + passwordAttempt;
//                //jsonArray = connection.GetJSONFromUrl(URL + login_Query);
//                getUserData(URL + login_Query);
//            }
//        });
//    }
//    private void getUserData(String loginQuery)
//    {
//        System.out.println("Getting User Data!");
//
//        ArrayList userItems = new ArrayList();
//        MobileUserItem mobItem = new MobileUserItem();
//
//        try
//        {
//            WebAPIConnect parser = new WebAPIConnect();
//            JSONObject json = parser.retrieveJSONObjectFromURL(loginQuery);
//
//            System.out.println("Creating User object!");
//            System.out.println("getting username");
//            mobItem.setUserName(json.getString(KEY_USERNAME));
//            System.out.println("getting password");
//            mobItem.setPassword(json.getString(KEY_PASSWORD));
//        }
//        catch (JSONException e)
//        {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "Login Failed, Please check your information", Toast.LENGTH_LONG).show();
//            return;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "Login Failed, Please check your information", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        Intent i = new Intent(getApplicationContext(),MobileUserBooksActivity.class);
//        i.putExtra("books", mobItem.getEventList());
//        finish();
//        startActivity(i);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_mobile_user_login, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings)
//        {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//}
