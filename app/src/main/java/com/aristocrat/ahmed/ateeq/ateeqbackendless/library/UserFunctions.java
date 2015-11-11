package com.aristocrat.ahmed.ateeq.ateeqbackendless.library;

/**
 * Created by aristocrat on 11/11/15.
 */
/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */


        import java.util.ArrayList;
        import java.util.List;

        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONObject;

        import android.content.Context;

        import com.aristocrat.ahmed.ateeq.ateeqbackendless.dbDetails;

public class UserFunctions {

    private JSONParser jsonParser;

    private static String loginURL = "http://" +dbDetails.ip + "/" + dbDetails.loginserver;
    private static String registerURL = "http://" +dbDetails.ip + "/" + dbDetails.regserver;
    private static String orderURL = "http://" +dbDetails.ip + "/" + dbDetails.orderserver;

    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }

    /**
     * function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }

    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String name, String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        // return json
        return json;
    }

    public JSONObject orderstuff(String user,String dish,String quantity,String pno)
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("user", user));
        params.add(new BasicNameValuePair("dish", dish));
        params.add(new BasicNameValuePair("quantity", quantity));
        params.add(new BasicNameValuePair("pno", pno));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(orderURL, params);
        // return json
        return json;

    }

    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }

    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }

}
