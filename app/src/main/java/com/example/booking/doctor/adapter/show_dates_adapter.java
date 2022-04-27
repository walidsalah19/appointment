package com.example.booking.doctor.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking.R;
import com.example.booking.data_class.dates_class;
import com.example.booking.doctor.manage_dates;
import com.example.booking.data_class.move_data;

import java.util.ArrayList;

public class show_dates_adapter extends RecyclerView.Adapter<show_dates_adapter.help> {
    ArrayList<dates_class> arrayList ;
    Fragment fragment;

    public show_dates_adapter(ArrayList<dates_class> arrayList, Fragment fragment) {
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
        holder.name.setText(arrayList.get(position).getName());
        holder.date.setText(arrayList.get(position).getAppointmentdata());
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Bundle b=new Bundle();
                   move_data.setAppointmentid(arrayList.get(position).getAppointmentid());
                   move_data.setP_id(arrayList.get(position).getPationid());
                   /*b.putString("name",arrayList.get(position).getName());
                   b.putString("age",arrayList.get(position).getAge());
                   b.putString("date",arrayList.get(position).getAppointmentdata());
                   b.putString("time",arrayList.get(position).getAppointmenttime());
                   b.putString("b_date",arrayList.get(position).getBookingdate());
                   b.putString("clinic",arrayList.get(position).getClinicAddress());
                   b.putString("doc_id",arrayList.get(position).getDoctoridid());
                   b.putString("hos_id",arrayList.get(position).getHospitalid());
                   b.putString("f_num",arrayList.get(position).getFile_number());*/
                   manage_dates m=new manage_dates();
                   m.setArguments(b);
                   fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_framelayout,m).addToBackStack(null).commitAllowingStateLoss();
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
