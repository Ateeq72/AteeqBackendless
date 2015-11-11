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
import android.widget.TextView;
import android.widget.Toast;

import com.aristocrat.ahmed.ateeq.ateeqbackendless.dbDetails;
import com.aristocrat.ahmed.ateeq.ateeqbackendless.library.UserFunctions;
import com.aristocrat.ahmed.ateeq.ateeqbackendless.library.DatabaseHandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragmentOrder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragmentOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentOrder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ProgressDialog pDialog;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private JSONObject jsondata = new JSONObject();
    private JSONArray jsonarray = new JSONArray();
    private static String RESULT = "";
    private static final String TAG_SUCCESS = "flag";
    private static final String TAG_MESSAGE = "message";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentOrder.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentOrder newInstance(String param1, String param2) {
        fragmentOrder fragment = new fragmentOrder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public fragmentOrder() {
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
        return inflater.inflate(R.layout.fragment_order, container, false);
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

        TextView dishse = (TextView) getView().findViewById(R.id.dishtextView);
        TextView quantityse = (TextView) getView().findViewById(R.id.quantity_textView);
        dishse.setText(MainActivity.dish);
        quantityse.setText(MainActivity.quantity);

        final Button finalizabtn = (Button) getView().findViewById(R.id.finalize_button);
        finalizabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText pnofield = (EditText) getView().findViewById(R.id.fphoneEdittext);
                String pno = pnofield.getText().toString();
                MainActivity.pno = pno;

                Toast.makeText(getActivity(), MainActivity.cuname+"  " + MainActivity.cuemailid +" "+ MainActivity.dish +" " + MainActivity.quantity +" "+ pno, Toast.LENGTH_LONG).show();
                new storeorderdb().execute();
                if(MainActivity.cuname.equals("") || MainActivity.cuemailid.equals(""))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("Order Error");
                    alert.setMessage("Please Login to order!");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    return;
                }

            }
        });
    }

    private class storeorderdb extends AsyncTask<String, String, String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Placing Order!. Please Wait.");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            UserFunctions userfunc = new UserFunctions();

            jsondata =  userfunc.orderstuff(MainActivity.cuname,MainActivity.dish,MainActivity.quantity,MainActivity.pno);
            try {
                if (!jsondata.getString(TAG_MESSAGE).equals("")) {
                    String res = jsondata.getString(TAG_SUCCESS);
                    if(Integer.parseInt(res) == 1)
                    {
                     //order succes!
                        return RESULT = "succes";
                    }
                    else{
                        String msg = jsondata.getString(TAG_MESSAGE);
                        return RESULT = msg;
                    }

                }
            }
            catch (Exception ex)
            {

            }

            return RESULT;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (RESULT == "succes")
            {
                Fragment main = new fragmentMain();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame,main);
                ft.commit();
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Order Status");
                alert.setMessage(RESULT);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();

            }
            else{
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Order Status");
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
