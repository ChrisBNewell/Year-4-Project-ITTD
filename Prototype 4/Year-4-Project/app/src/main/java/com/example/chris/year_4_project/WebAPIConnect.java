package com.example.chris.year_4_project;

/**
 * Created by Chris on 03/11/2014.
 */

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class WebAPIConnect
{

    public static void EditEvent(String url, int eventID, String objectToSend)
    {
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPut httpPut = new HttpPut(url + eventID);
            StringEntity temp = new StringEntity(objectToSend);
            httpPut.setEntity(temp);

            System.out.println("***********" + eventID + objectToSend + "***********");

            httpPut.setHeader("Accept","application/json");
            httpPut.setHeader("Content-Type","application/json");

            HttpResponse httpResponse = httpClient.execute(httpPut);
            StatusLine statusLine = httpResponse.getStatusLine();
            System.out.println();
            System.out.println("url used for this POST: " + url + eventID + "/n" + "Response from server after POST: " + httpResponse + ", StatusLine " + statusLine);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static HttpResponse DeleteEvent(String url, int eventIDToDelete)
    {
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            System.out.println("Attempting to Delete Event ID: " + eventIDToDelete);
            HttpDelete httpDelete = new HttpDelete(url + eventIDToDelete);

            //httpDelete.setEntity();
            //StringEntity temp = new StringEntity(eventIDToDelete);
            //httpDelete.setEntity(temp);

            httpDelete.setHeader("Accept","application/json");
            httpDelete.setHeader("Content-Type","application/json");

            HttpResponse httpResponse = (HttpResponse) httpClient.execute(httpDelete);
            StatusLine statusLine = httpResponse.getStatusLine();
            System.out.println("Response from server after POST: " + httpResponse + ", Status Line: " + statusLine);

            return httpResponse;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse sendJSONtoURL(String url, String objectToSend)
    {
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            StringEntity temp = new StringEntity(objectToSend);
            httpPost.setEntity(temp);

            httpPost.setHeader("Accept","application/json");
            httpPost.setHeader("Content-Type","application/json");

            HttpResponse httpResponse = httpClient.execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            System.out.println("url used for this POST: " + url);
            System.out.println("Response from server after POST: " + httpResponse + ", Status Line: " + statusLine);
            return httpResponse;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray GetJSONFromUrl(String url)
    {
        HttpEntity httpEntity = null;

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            System.out.println("executing httpGet");
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
                System.out.println("Converting entity response to json Array");
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("Entity Response: ", entityResponse);
                jsonArray = new JSONArray(entityResponse);
                System.out.println("finished Converting entity response to json Array");

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

    public JSONObject retrieveJSONObjectFromURL(String url)
    {
        HttpEntity httpEntity = null;
        JSONObject jsonObj = null;
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
            return null;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }

        if(httpEntity != null)
        {
            try
            {
                System.out.println("Converting entity response to jsonObject");
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("Entity Response: ", entityResponse);
                jsonObj = new JSONObject(entityResponse);
                System.out.println("finished Converting entity response to jsonObject");

            }
            catch(JSONException e)
            {
                e.printStackTrace();
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

        return jsonObj;
    }
}
