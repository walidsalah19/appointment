package com.example.booking.hospital;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking.R;
import com.example.booking.data_class.clinic_data;
import com.example.booking.hospital.adapters.clinic_recycler_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class manage_clinic extends Fragment {

  private RecyclerView recyclerview;
  private ArrayList<clinic_data> arrayList;
  private FloatingActionButton add_clinic;
  private FirebaseFirestore database;
  private FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manage_clienice, container, false);
        firebase_tool();
        recyclerview_methode(v);
        add_new_clinic(v);
        get_clinic_databases();
        return v;
    }
    private void firebase_tool()
    {
        auth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();
    }
    private void recyclerview_methode(View view) {
        recyclerview=view.findViewById(R.id.hospital_clinic_recycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList=new ArrayList<clinic_data>();


    }
    private void add_new_clinic(View v)
    {
        add_clinic=v.findViewById(R.id.hospital_add_clinic);
        add_clinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.hospital_framelayout,new add_new_clinic()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    private void get_clinic_databases(){
        String h_id=auth.getCurrentUser().getUid().toString();
        database.collection("hospital").document(h_id).collection("clinic").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isSuccessful())
               {
                   for (QueryDocumentSnapshot snapshot:task.getResult())
                   {
                         clinic_data data=new clinic_data(snapshot.get("name").toString(),snapshot.get("clinic_id").toString());
                         arrayList.add(data);

                   }
                   clinic_recycler_adapter adapter=new clinic_recycler_adapter(arrayList,manage_clinic.this);
                   recyclerview.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
               }
            }
        });
    }
}