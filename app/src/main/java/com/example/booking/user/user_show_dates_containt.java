package com.example.booking.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.booking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class user_show_dates_containt extends Fragment {

    private String hospitalid,clinicid,doctorid;
    private TextView hospital,clinic,doctor,date,time,booking;
    private FirebaseFirestore database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user_show_dates_containt, container, false);

        database=FirebaseFirestore.getInstance();
        intalize(v);
        get_hospital_name();
        get_doctor_name();
        return v;
    }

    private void get_doctor_name() {
        doctorid=getArguments().getString("doctorid");
        database.collection("doctors").document(doctorid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                doctor.setText( task.getResult().get("name").toString());
            }
        });

    }

    private void get_hospital_name() {
        hospitalid=getArguments().getString("hospitalid");
         database.collection("hospital").document(hospitalid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                 hospital.setText(   task.getResult().get("name").toString());
             }
         });

    }

    private void intalize(View v)
    {
        hospital=v.findViewById(R.id.user_show_data_hospital_);
        clinic=v.findViewById(R.id.user_show_data_clinic);
        doctor=v.findViewById(R.id.user_show_data_doctor);
        date=v.findViewById(R.id.user_show_data_date);
        time=v.findViewById(R.id.user_show_data_time);
        booking=v.findViewById(R.id.user_show_data_booking_);


       clinic.setText(getArguments().getString("clinicAddress"));
        date.setText(getArguments().getString("appointmentdata"));
        time.setText(getArguments().getString("appointmenttime"));
        booking.setText(getArguments().getString("bookingdate"));
    }


}