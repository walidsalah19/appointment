package com.example.booking.user;

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
import com.example.booking.doctor.doctore_show_dates;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class show_date extends Fragment {

    private RecyclerView recyclerview;
    ArrayList<dates_class> arrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_show_date, container, false);
        intialize_recycler(v);
        add_new_date(v);
        return v;
    }
    private void intialize_recycler(View v)
    {
        arrayList=new ArrayList<>();
      recyclerview=v.findViewById(R.id.user_show_date);
      recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
      get_data();
    }
    private void get_data()
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseFirestore database=FirebaseFirestore.getInstance();
        String id=auth.getCurrentUser().getUid().toString();
        database.collection("dates").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult())
                    {
                        String id_p=snapshot.get("pationid").toString();
                        if (id.equals(id_p))
                        {
                            dates_class date=new dates_class(snapshot.get("name").toString(),snapshot.get("age").toString(),snapshot.get("clinicAddress").toString()
                                    ,snapshot.get("doctorid").toString(),snapshot.get("hospitalid").toString(),id_p,snapshot.get("appointmentdata").toString(),
                                    snapshot.get("bookingdate").toString(),snapshot.get("appointmenttime").toString(),snapshot.get("appointmentid").toString(),snapshot.get("file_number").toString());
                            arrayList.add(date);

                        }
                    }
                    show_date_adapter adapter=new show_date_adapter(arrayList,show_date.this);
                    recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void add_new_date(View v)
    {
        FloatingActionButton floatingactionbutton=v.findViewById(R.id.add_new_date);
        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.user_framelayout,new booking_date()).addToBackStack(null).commit();
            }
        });
    }
}