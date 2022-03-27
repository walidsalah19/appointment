package com.example.booking.doctor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking.R;
import com.example.booking.doctor.manage_dates;
import com.example.booking.doctor.show_date_data;

import java.util.ArrayList;

public class show_dates_adapter extends RecyclerView.Adapter<show_dates_adapter.help> {
    ArrayList<String> arrayList ;
    Fragment fragment;

    public show_dates_adapter(ArrayList<String> arrayList, Fragment fragment) {
        this.arrayList = arrayList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public help onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_show_dates, parent, false);

        return new help(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull help holder, int position) {
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_framelayout,new manage_dates()).addToBackStack(null).commitAllowingStateLoss();
               }
           });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class help extends RecyclerView.ViewHolder
    {
        TextView name,date;
        public help(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.doctor_showname_recycler);
            date=itemView.findViewById(R.id.doctor_showdate_recycler);
        }
    }
}
