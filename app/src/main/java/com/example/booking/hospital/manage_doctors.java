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
import com.example.booking.data_class.doctor_data;
import com.example.booking.hospital.adapters.doctor_recycler_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class manage_doctors extends Fragment {

    private RecyclerView recyclerview_m;
    private ArrayList<doctor_data> arrayList_m;
    private FloatingActionButton add_doctor_m;

    private FirebaseAuth auth_m;
    private FirebaseFirestore database_m;
    private String h_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manage_doctors, container, false);
        auth_m=FirebaseAuth.getInstance();
        database_m=FirebaseFirestore.getInstance();
       h_id =auth_m.getCurrentUser().getUid().toString();
        recyclerview_methode(v);
        add_doctor(v);
        get_doctors_databases();
        return v;
    }
    private void recyclerview_methode(View view) {
        recyclerview_m=view.findViewById(R.id.hospital_doctor_recycler);
        recyclerview_m.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList_m=new ArrayList<doctor_data>();
    }
    private void add_doctor(View v)
    {
        add_doctor_m=v.findViewById(R.id.hospital_add_doctors);
        add_doctor_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putString("h_id",h_id);
                new_doctor d=new new_doctor();
                d.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.hospital_framelayout,d).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    private void get_doctors_databases(){

        database_m.collection("doctors").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult())
                    {
                        String hospital_id=snapshot.get("hospitalId").toString();
                        if (h_id.equals(hospital_id)) {
                            doctor_data data = new doctor_data(snapshot.get("name").toString(), snapshot.get("clinicname").toString(), snapshot.get("clinicaddress").toString(),
                                    snapshot.get("doctorid").toString(), snapshot.get("roomnumber").toString(), snapshot.get("workinghours").toString(), hospital_id
                                    , snapshot.get("email").toString(), snapshot.get("password").toString());
                            arrayList_m.add(data);
                        }
                    }
                    doctor_recycler_adapter adapter=new doctor_recycler_adapter(arrayList_m,manage_doctors.this);
                    recyclerview_m.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}