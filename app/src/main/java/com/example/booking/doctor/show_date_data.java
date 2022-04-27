package com.example.booking.doctor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.booking.R;
import com.example.booking.data_class.move_data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class show_date_data extends Fragment {

    private EditText name,age,file_num,clinic,bookingdate,date,time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_show_date_data, container, false);

   intialization(v);
        return v;
    }

    private void intialization(View v) {
        name=v.findViewById(R.id.doctor_show_date_name);
        age=v.findViewById(R.id.doctor_show_date_age);
        file_num=v.findViewById(R.id.doctor_show_date_filenum);
        clinic=v.findViewById(R.id.doctor_show_date_clinic);
        bookingdate=v.findViewById(R.id.doctor_show_date_bookindate);
        date=v.findViewById(R.id.doctor_show_date_date);
        time=v.findViewById(R.id.doctor_show_date_time);
        get_data_firebase();
    }
    private void get_data_firebase()
    {

        FirebaseFirestore database=FirebaseFirestore.getInstance();
        database.collection("dates").document(move_data.getAppointmentid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists())
                {
                    name.setText(task.getResult().get("name").toString());
                    age.setText(task.getResult().get("age").toString());
                    file_num.setText(task.getResult().get("file_number").toString());
                    clinic.setText(task.getResult().get("clinicAddress").toString());
                    bookingdate.setText(task.getResult().get("bookingdate").toString());
                    date.setText(task.getResult().get("appointmentdata").toString());
                    time.setText(task.getResult().get("appointmenttime").toString());

                }
            }
        });
    }
}