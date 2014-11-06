package com.example.chris.year_4_project;

/**
 * Created by Chris on 03/11/2014.
 */
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

public class WebAPIConnect
{
    public JSONArray GetJSONFromUrl(String url)
    {
        HttpEntity httpEntity = null;

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
        }
        catch(ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        JSONArray jsonArray = null;

        if(httpEntity != null)
        {
            try
            {
                System.out.println("Converting entity response to jsonarray");
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("Entity Response: ", entityResponse);
                jsonArray = new JSONArray(entityResponse);
                System.out.println("finished Converting entity response to jsonarray");

            }
            catch(JSONException e)
            {
                e.printStackTrace();
                Toast.makeText( null, "Login Failed, Please check your information", Toast.LENGTH_LONG).show();

                return null;
            }
            catch(IOException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }

        return jsonArray;
    }
}
