package com.aristocrat.ahmed.ateeq.ateeqbackendless;

/*import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
/**
 * Created by aristocrat on 10/11/15.
 */
/*
public class dbConnectivityClass  {


    String ip = "192.168.1.10";
    InputStream is=null;
    String name;
    String email;
    String result=null;
    String line=null;
    int code;

    public void insertdata(String user,String dish,String quantity,String pno)
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("user",user));
        nameValuePairs.add(new BasicNameValuePair("dish",dish));
        nameValuePairs.add(new BasicNameValuePair("quantity",quantity));
        nameValuePairs.add(new BasicNameValuePair("pno",pno));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+ip+"/insertdata.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
           // Toast.makeText(this, "Invalid IP Address",
             //       Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
              //  Toast.makeText(this, "Registered Successfully",
                //        Toast.LENGTH_SHORT).show();
            }
            else
            {
              //  Toast.makeText(this, "Sorry, Try Again",
                   //     Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    public void registerUser(String name,String email,String pass)
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("name",name));
        nameValuePairs.add(new BasicNameValuePair("email",email));
        nameValuePairs.add(new BasicNameValuePair("password",pass));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+ip+"/insertusers.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
//            Toast.makeText(this, "Invalid IP Address",
                    //Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
              //  Toast.makeText(this, "Registered Successfully",
                //        Toast.LENGTH_SHORT).show();
            }
            else
            {
               // Toast.makeText(this, "Sorry, Try Again",
                 //       Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    public void getuser(String password)
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("password",password));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+ip+"/getuser.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
          //  Toast.makeText(this, "Invalid IP Address",
             //       Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            name=(json_data.getString("name"));
            email=(json_data.getString("email"));
            MainActivity.cuname=name;
            MainActivity.cuemailid=email;
            if (name.equals("") ||email.equals("") )
            {
              //  Toast.makeText(this,"User Not fount please Register",Toast.LENGTH_LONG).show();
                return;
            }
            MainActivity.loggedin=true;

        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }

    }
}
*/