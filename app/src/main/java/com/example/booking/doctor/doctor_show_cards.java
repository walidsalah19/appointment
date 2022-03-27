package com.example.booking.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking.R;
import com.example.booking.doctor.adapter.show_cards_adapter;
import com.example.booking.doctor.adapter.show_dates_adapter;

import java.util.ArrayList;

public class doctor_show_cards extends Fragment {

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
        View v= inflater.inflate(R.layout.fragment_doctor_show_cards, container, false);
        recyclerview_methode(v);
        return v;
    }
    private void recyclerview_methode(View view) {
        recyclerview=view.findViewById(R.id.doctor_show_cards_recycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList=new ArrayList<String>();
        arrayList.add("عيادة الباطنه ");
        arrayList.add("عيادة الباطنه ");
        arrayList.add("عيادة الباطنه ");
        arrayList.add("عيادة الباطنه ");
        show_cards_adapter adapter=new show_cards_adapter(arrayList,this);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}