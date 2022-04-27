package com.example.booking.user;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.booking.R;
import com.example.booking.data_class.move_data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class booking_assest_class {
    private View v;
    private Spinner hospital,clinic,doctor;
    private FirebaseFirestore database;
    private String user_id;
    Fragment fragment;
    public booking_assest_class(View v,Fragment fragment) {
       this.v = v;
       this.fragment = fragment;
        hospital = (Spinner) v.findViewById(R.id.user_booking_data_hospital);
        clinic =v.findViewById(R.id.user_booking_data_clinic);
        doctor =v.findViewById(R.id.user_booking_data_doctor);
        database=FirebaseFirestore.getInstance();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        user_id=auth.getCurrentUser().getUid().toString();
    }
    public void hospital()
    {
        List<String> hospital_name = new ArrayList<String>();
        List<String> hospital_id_l = new ArrayList<String>();
        database.collection("hospital").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult()) {
                        hospital_id_l.add(snapshot.get("HospitalID").toString());
                        hospital_name.add(snapshot.get("name").toString());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(fragment.getActivity(), R.layout.spinner_item, hospital_name);
                    hospital.setAdapter(dataAdapter);
                }
            }
        });
        hospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (! hospital_id_l.isEmpty()) {
                    move_data.setHospital_id(hospital_id_l.get(i));
                    get_selected_clinic(hospital_id_l.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void get_selected_clinic(String hospital_id) {
        List<String> c_id = new ArrayList<String>();
        ArrayList<String> clinic_name=new ArrayList<String>();
        database.collection("clinic").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult()) {
                        String hos_id=snapshot.get("hospital_id").toString();
                        if (hos_id.equals(hospital_id)) {
                            c_id.add(snapshot.get("clinic_id").toString());
                            clinic_name.add(snapshot.get("name").toString());
                        }
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(fragment.getActivity(), R.layout.spinner_item, clinic_name);
                    clinic.setAdapter(dataAdapter);
                }
            }
        });
        clinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                move_data.setClinic(adapterView.getItemAtPosition(i).toString());
                get_selected_doctor(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void get_selected_doctor(String c_name) {
        ArrayList<String> docotr_name=new ArrayList<String>();
        ArrayList<String> d_id=new ArrayList<String>();
        database.collection("doctors").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult()) {
                        String hos_id=snapshot.get("hospitalId").toString();
                        String c_n=snapshot.get("clinicname").toString();
                        if (hos_id.equals(move_data.getHospital_id()) && c_n.equals(c_name)) {
                            docotr_name.add(snapshot.get("name").toString());
                            d_id.add(snapshot.get("doctorid").toString());
                        }
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(fragment.getActivity(), R.layout.spinner_item,  docotr_name);
                    doctor.setAdapter(dataAdapter);
                }
            }
        });
        doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (d_id.size()>0) {
                    move_data.setDoctor_id( d_id.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void get_patient_data()
    {
        database.collection("patient").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists())
                {
                   move_data.setUser_name( task.getResult().get("name").toString());
                    move_data.setUser_age(task.getResult().get("age").toString());
                }
            }
        });
    }
    public String get_date()
    {
       return new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
    }

}
