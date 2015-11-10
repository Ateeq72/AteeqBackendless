package com.aristocrat.ahmed.ateeq.ateeqbackendless;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragmentQuantity.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragmentQuantity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentQuantity extends Fragment implements fragmentOrder.OnFragmentInteractionListener {
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
     * @return A new instance of fragment fragmentQuantity.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentQuantity newInstance(String param1, String param2) {
        fragmentQuantity fragment = new fragmentQuantity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public fragmentQuantity() {
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
        return inflater.inflate(R.layout.fragment_quantity, container, false);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

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
        String[] list = getResources().getStringArray(R.array.quantity_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,list);
        Spinner sp = (Spinner) getView().findViewById(R.id.quantity_spinner);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item = parent.getItemAtPosition(pos).toString();
                MainActivity.quantity=item;

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Button btn = (Button) getView().findViewById(R.id.nextbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment order = new fragmentOrder();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame,order);
                ft.commit();
            }
        });

    }

    public void onBackPressed() {
       FragmentTransaction ft =getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame,getParentFragment());
        ft.commit();
    }

}
