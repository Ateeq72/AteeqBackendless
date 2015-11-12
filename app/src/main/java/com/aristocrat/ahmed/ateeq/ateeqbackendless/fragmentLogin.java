package com.aristocrat.ahmed.ateeq.ateeqbackendless;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.aristocrat.ahmed.ateeq.ateeqbackendless.library.DatabaseHandler;
import com.aristocrat.ahmed.ateeq.ateeqbackendless.library.JSONParser;
import com.aristocrat.ahmed.ateeq.ateeqbackendless.library.UserFunctions;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragmentLogin.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragmentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentLogin extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ProgressDialog pDialog;
    private static JSONObject jsondata = new JSONObject();


    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";

    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static  String RESULT= "";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    /* JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://192.168.1.8/login.php";


*/    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentLogin newInstance(String param1, String param2) {
        fragmentLogin fragment = new fragmentLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public fragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void onActivityCreated(Bundle bs)
    {
        super.onActivityCreated(bs);

        Button loginbtn = (Button) getView().findViewById(R.id.loginbutton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText getmailfield = (EditText) getView().findViewById(R.id.getemail);
                EditText getpasswordfield = (EditText) getView().findViewById(R.id.getpassword);

                final String email = getmailfield.getText().toString();
                final String pass = getpasswordfield.getText().toString();
                if (email == null || email.equals("")) {
                    Toast.makeText(getActivity(), "Email Cant be Empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (pass == null || pass.equals("")) {
                    Toast.makeText(getActivity(), "Password Cant be Empty", Toast.LENGTH_LONG).show();
                    return;
                }

                try {

                    MainActivity.ruser = email;
                    MainActivity.rpass = pass;
                    new AttemptLogin().execute();

                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "Error : " + ex, Toast.LENGTH_LONG).show();
                }


            }
        });

        Button regbtn = (Button) getView().findViewById(R.id.loginRegisterBtn);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment register = new fragmentRegister();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, register);
                ft.commit();

            }
        });





    }

    class AttemptLogin extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog *
         */
        int res;
        String name;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
        // TODO Auto-generated method stub
        // here Check for success tag

            String username = MainActivity.ruser;
            String password = MainActivity.rpass;
            UserFunctions userFunctions = new UserFunctions();
            jsondata = userFunctions.loginUser(username,password);


            try {
                if (jsondata != null)
                {
                   res = jsondata.getInt(TAG_SUCCESS);
                    if(res == 1){
                        //logged in! :)

                        DatabaseHandler db = new DatabaseHandler(getActivity());
                        userFunctions.logoutUser(getActivity());
                        MainActivity.cuname = jsondata.getString("cname");
                        MainActivity.cuemailid = jsondata.getString("cemail");
                        db.addUser(MainActivity.cuname,MainActivity.cuemailid);
                        name = jsondata.getString("cname");

                        return RESULT = jsondata.getString(TAG_MESSAGE);



                    }
                    else{
                             return RESULT = jsondata.getString(TAG_MESSAGE) ;
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

                return RESULT;
        }

        /**
         * Once the background process is done we need to Dismiss the progress dialog asap *
         **/
        protected void onPostExecute(String message) {
            pDialog.dismiss();
            if (res == 1) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Loggin Status");
                alert.setMessage(RESULT);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Fragment nav = new fragmentNav();
                        FragmentTransaction ftn = getFragmentManager().beginTransaction();
                        ftn.replace(R.id.nav_view, nav);
                        ftn.commit();

                        Fragment main = new fragmentMain();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrame, main);
                        ft.commit();
                        Toast.makeText(getActivity(),"Welcome!  " + name,Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("Loggin Status");
                    alert.setMessage(RESULT);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();

                }
        }


    }
    }
