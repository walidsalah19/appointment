package com.example.booking.doctor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking.R;
import com.example.booking.data_class.dates_class;
import com.example.booking.doctor.adapter.show_dates_adapter;
import com.example.booking.hospital.adapters.clinic_recycler_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class doctore_show_dates extends Fragment {

    private RecyclerView recyclerview;
    private ArrayList<dates_class> arrayList;
    private FirebaseFirestore database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_doctore_show_dates, container, false);
        recyclerview_methode(v);
        return v;
    }
    private void recyclerview_methode(View view) {
        recyclerview=view.findViewById(R.id.doctor_show_date_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList=new ArrayList<dates_class>();
        get_firebase_data();
    }
    private void get_firebase_data() {
         database=FirebaseFirestore.getInstance();
         String doc_id=get_doctor_id.getDoc_id();
         database.collection("dates").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(@NonNull Task<QuerySnapshot> task) {
                  if (task.isSuccessful())
                  {
                      for (QueryDocumentSnapshot snapshot:task.getResult())
                      {
                          String id=snapshot.get("doctorid").toString();
                          if (doc_id.equals(id))
                          {
                              dates_class date=new dates_class(snapshot.get("name").toString(),snapshot.get("age").toString(),snapshot.get("clinicAddress").toString()
                              ,id,snapshot.get("hospitalid").toString(),snapshot.get("pationid").toString(),snapshot.get("appointmentdata").toString(),
                                      snapshot.get("bookingdate").toString(),snapshot.get("appointmenttime").toString(),snapshot.get("appointmentid").toString(),snapshot.get("file_number").toString());
                          arrayList.add(date);

                          }
                      }
                      show_dates_adapter adapter=new show_dates_adapter(arrayList,doctore_show_dates.this);
                      recyclerview.setAdapter(adapter);
                      adapter.notifyDataSetChanged();
                  }
             }
         });
    }
}