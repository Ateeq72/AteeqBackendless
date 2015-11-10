package com.aristocrat.ahmed.ateeq.ateeqbackendless;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


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

    // TODO: Rename and change types of parameters
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
                CheckBox staycheck = (CheckBox) getView().findViewById(R.id.stay);
                final boolean stays = staycheck.isChecked();
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

                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                //Your implementation goes here
                                InputStream is = null;
                                String line = null;
                                String result = null;

                                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                                nameValuePairs.add(new BasicNameValuePair("password", pass));

                                try {
                                    HttpClient httpclient = new DefaultHttpClient();
                                    HttpPost httppost = new HttpPost("http://192.168.1.10/getuser.php");
                                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                                    HttpResponse response = httpclient.execute(httppost);
                                    HttpEntity entity = response.getEntity();
                                    is = entity.getContent();
                                    Log.e("pass 1", "connection success ");
                                } catch (Exception e) {
                                    Log.e("Fail 1", e.toString());
                                    //  Toast.makeText(this, "Invalid IP Address",
                                    //       Toast.LENGTH_LONG).show();
                                }

                                try {
                                    BufferedReader reader = new BufferedReader
                                            (new InputStreamReader(is, "iso-8859-1"), 8);
                                    StringBuilder sb = new StringBuilder();
                                    while ((line = reader.readLine()) != null) {
                                        sb.append(line + "\n");
                                    }
                                    is.close();
                                    result = sb.toString();
                                    Log.e("pass 2", "connection success ");
                                } catch (Exception e) {
                                    Log.e("Fail 2", e.toString());
                                }

                                try {
                                    JSONObject json_data = new JSONObject(result);
                                    String name = (json_data.getString("name"));
                                    String femail = (json_data.getString("email"));
                                    MainActivity.cuname = name;
                                    MainActivity.cuemailid = femail;
                                    if (name.equals("") || femail.equals("")) {
                                        Toast.makeText(getActivity(),"User Not fount please Register",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    MainActivity.loggedin = true;

                                } catch (Exception e) {
                                    Log.e("Fail 3", e.toString());
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();



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

}
