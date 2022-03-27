package com.example.booking.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking.R;
import com.example.booking.data_class.dates_class;

import java.util.ArrayList;

public class show_date_adapter extends RecyclerView.Adapter<show_date_adapter.helper> {
 ArrayList<dates_class> arr;
 Fragment fragment;

    public show_date_adapter(ArrayList<dates_class> arr, Fragment fragment) {
        this.arr = arr;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public helper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.user_show_dates_layout, parent, false);
        return new helper(v);
    }

    @Override
    public void onBindViewHolder(@NonNull helper holder, int position) {
      holder.room_number.setText(arr.get(position).getRoom_number());
      holder.time.setText(arr.get(position).getTime());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.user_framelayout,new user_show_dates_containt()).addToBackStack(null).commitAllowingStateLoss();
          }
      });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class helper extends RecyclerView.ViewHolder
    {
        TextView time,room_number;
        public helper(@NonNull View itemView) {
            super(itemView);
            time=(TextView) itemView.findViewById(R.id.user_show_date_time_recycler);
            room_number=(TextView) itemView.findViewById(R.id.user_show_date_roomnumber_recycler);
        }
    }
}
