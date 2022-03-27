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
import com.example.booking.hospital.hospital_maintanance_clinic;

import java.util.ArrayList;

public class clinic_recycler_adapter extends RecyclerView.Adapter<clinic_recycler_adapter.help>{
     ArrayList<String>arrayList;
     Fragment fragment;

    public clinic_recycler_adapter(ArrayList<String> arrayList, Fragment fragment) {
        this.arrayList = arrayList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public help onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_clinic_recycler_layout,parent,false);

        return new help(v);
    }

    @Override
    public void onBindViewHolder(@NonNull help holder, int position) {
        holder.name.setText(arrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putString("name",arrayList.get(position));
                hospital_maintanance_clinic main=new hospital_maintanance_clinic();
                main.setArguments(b);
                fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.hospital_framelayout,main).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class help extends RecyclerView.ViewHolder
    {
        TextView name;
        public help(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.hospital_clinic_name);
        }
    }
}
