package com.example.booking.hospital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking.R;
import com.example.booking.hospital.adapters.clinic_recycler_adapter;
import com.example.booking.hospital.adapters.doctor_recycler_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class manage_doctors extends Fragment {

    private RecyclerView recyclerview;
    private ArrayList<String> arrayList;
    private FloatingActionButton add_doctor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manage_doctors, container, false);

        recyclerview_methode(v);
        add_new_doctor(v);
        return v;
    }
    private void recyclerview_methode(View view) {
        recyclerview=view.findViewById(R.id.hospital_doctor_recycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList=new ArrayList<String>();
        arrayList.add("طبيب 1 ");
        arrayList.add("طبيب 2 ");
        arrayList.add("طبيب 3 ");
        arrayList.add("طبيب 4 ");
        doctor_recycler_adapter adapter=new doctor_recycler_adapter(arrayList,this);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void add_new_doctor(View v)
    {
        add_doctor=v.findViewById(R.id.hospital_add_doctors);
        add_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.hospital_framelayout,new add_new_doctor()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
}