package com.example.booking.doctor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking.R;
import com.example.booking.doctor.show_card_data;
import com.example.booking.doctor.show_date_data;

import java.util.ArrayList;

public class show_cards_adapter extends RecyclerView.Adapter<show_cards_adapter.helper> {
    ArrayList<String> arrayList ;
    Fragment fragment;

    public show_cards_adapter(ArrayList<String> arrayList, Fragment fragment) {
        this.arrayList = arrayList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public helper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_recycler_show_cards, parent, false);

        return new helper(v);
    }

    @Override
    public void onBindViewHolder(@NonNull helper holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_framelayout,new show_card_data()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class helper extends RecyclerView.ViewHolder
    {
        TextView name,number;

        public helper(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.doctor_showname_recycler);
            number=itemView.findViewById(R.id.doctor_showdate_recycler);
        }
    }
}
