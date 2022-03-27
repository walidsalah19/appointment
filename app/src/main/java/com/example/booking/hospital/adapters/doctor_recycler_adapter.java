package com.example.booking.hospital.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking.R;
import com.example.booking.hospital.hospital_maintanace_doctor;
import com.example.booking.hospital.hospital_maintanance_clinic;

import java.util.ArrayList;

public class doctor_recycler_adapter extends RecyclerView.Adapter<doctor_recycler_adapter.helper>{
    ArrayList<String> arrayList;
    Fragment fragment;

    public doctor_recycler_adapter(ArrayList<String> arrayList, Fragment fragment) {
        this.arrayList = arrayList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public helper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_show_doctor_recycler,parent,false);


        return new helper(v);
    }

    @Override
    public void onBindViewHolder(@NonNull helper holder, int position) {
        holder.name.setText(arrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.hospital_framelayout,new hospital_maintanace_doctor()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class helper extends RecyclerView.ViewHolder
    {
        TextView name;
        public helper(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.hospital_show_doctor_name_recycler);
        }
    }
}
