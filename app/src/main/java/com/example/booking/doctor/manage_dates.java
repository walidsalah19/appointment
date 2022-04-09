package com.example.booking.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.booking.R;

public class manage_dates extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        String p_id=getArguments().getString("p_id");
        Button card=v.findViewById(R.id.button_show_card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putString("p_id",p_id);
                show_card_data s=new show_card_data();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_framelayout,s).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

    private void show_dates(View v) {
        String a_id=getArguments().getString("Appointmentid");
        Button card=v.findViewById(R.id.button_show_dates_data);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putString("Appointmentid",a_id);
                show_date_data s=new show_date_data();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_framelayout,s).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

}