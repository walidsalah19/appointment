package com.example.booking.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking.R;
import com.example.booking.doctor.adapter.show_dates_adapter;
import com.example.booking.hospital.adapters.clinic_recycler_adapter;

import java.util.ArrayList;

public class doctore_show_dates extends Fragment {

    private RecyclerView recyclerview;
    private ArrayList<String> arrayList;
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
        arrayList=new ArrayList<String>();
        arrayList.add("عيادة الباطنه ");
        arrayList.add("عيادة الباطنه ");
        arrayList.add("عيادة الباطنه ");
        arrayList.add("عيادة الباطنه ");
        show_dates_adapter adapter=new show_dates_adapter(arrayList,this);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}