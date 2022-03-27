package com.example.booking.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.booking.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link manage_dates#newInstance} factory method to
 * create an instance of this fragment.
 */
public class manage_dates extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public manage_dates() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment manage_dates.
     */
    // TODO: Rename and change types and number of parameters
    public static manage_dates newInstance(String param1, String param2) {
        manage_dates fragment = new manage_dates();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
       View v= inflater.inflate(R.layout.fragment_manage_dates, container, false);
       show_dates(v);
       show_cards(v);

       return v;
    }

    private void show_cards(View v) {
        Button card=v.findViewById(R.id.button_show_card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_framelayout,new show_card_data()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

    private void show_dates(View v) {
        Button card=v.findViewById(R.id.button_show_dates_data);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_framelayout,new show_date_data()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

}