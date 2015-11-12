package com.aristocrat.ahmed.ateeq.ateeqbackendless;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aristocrat.ahmed.ateeq.ateeqbackendless.library.UserFunctions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragmentRegister.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragmentRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentRegister extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ProgressDialog pDialog;
    public JSONObject jsondata = new JSONObject();
    public static final String TAG_SUCCESS = "success";
    public static  final String TAG_MESSAGE = "message";


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentRegister newInstance(String param1, String param2) {
        fragmentRegister fragment = new fragmentRegister();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public fragmentRegister() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
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
        Button regbtn =(Button) getActivity().findViewById(R.id.registerbutton);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText getmailfield = (EditText) getView().findViewById(R.id.getEmailRegister);
                final EditText getpassfield = (EditText) getView().findViewById(R.id.getPassRegister);
                final EditText getNamefield = (EditText) getView().findViewById(R.id.getNameReg);
                final EditText getpassconfield = (EditText) getView().findViewById(R.id.getConfPassReg);

                final String name = getNamefield.getText().toString();

                final String email = getmailfield.getText().toString();
                final String pass = getpassfield.getText().toString();
                String cpass = getpassconfield.getText().toString();
                if ( name.equals("") )
                {
                    Toast.makeText(getActivity(),"Name Can't be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( email.equals(""))
                {
                    Toast.makeText(getActivity(),"Email Can't be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.equals(""))
                {
                    Toast.makeText(getActivity(),"Password Can't be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pass.equals(cpass))
                {
                    Toast.makeText(getActivity(),"Password dont match!",Toast.LENGTH_SHORT).show();
                    return;
                }
                MainActivity.rname= name;
                MainActivity.ruser=email;
                MainActivity.rpass=pass;


                new register().execute();


            }
        });

    }

    private class register extends AsyncTask<String ,String, String> {

        String RESULT;
        int res;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Registering... Please Wait.");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            UserFunctions userFunctions = new UserFunctions();
            Log.d("request!", "starting");
            jsondata = userFunctions.registerUser(MainActivity.rname,MainActivity.ruser,MainActivity.rpass);
            if (jsondata != null)
            {
                try {
                    res =  jsondata.getInt(TAG_SUCCESS);
                    if (res == 1)
                    {
                        RESULT = jsondata.getString(TAG_MESSAGE);
                        Log.d("connection", "success");
                        return RESULT;
                    }
                    else {
                        RESULT = jsondata.getString(TAG_MESSAGE);
                        Log.d("registration", "failed");
                        return RESULT;

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            return RESULT;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();
            if(res == 1) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Registration Status");
                alert.setMessage(RESULT + "\nPlease Login!");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Fragment login = new fragmentLogin();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrame, login);
                        ft.commit();
                       }
                });
                alert.show();
            }
            else
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Registration Status");
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
