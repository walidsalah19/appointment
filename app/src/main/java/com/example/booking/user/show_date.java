package com.example.booking.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking.R;
import com.example.booking.data_class.dates_class;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class show_date extends Fragment {

    private RecyclerView recyclerview;
    ArrayList<dates_class> arrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_show_date, container, false);
        intialize_recycler(v);
        add_new_date(v);
        return v;
    }
    private void intialize_recycler(View v)
    {
        arrayList=new ArrayList<>();
      recyclerview=v.findViewById(R.id.user_show_date);
      recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
      arrayList.add(new dates_class("date 1","20","الباطنة","doctor 1","hospital 1","1","6.55",
              "1/9/2022","2"));
        arrayList.add(new dates_class("date 1","20","الباطنة","doctor 1","hospital 1","1","6.55",
                "1/9/2022","2"));
        arrayList.add(new dates_class("date 1","20","الباطنة","doctor 1","hospital 1","1","6.55",
            "1/9/2022","2"));
        arrayList.add(new dates_class("date 1","20","الباطنة","doctor 1","hospital 1","1","6.55",
            "1/9/2022","2"));
        arrayList.add(new dates_class("date 1","20","الباطنة","doctor 1","hospital 1","1","6.55",
            "1/9/2022","2"));
        arrayList.add(new dates_class("date 1","20","الباطنة","doctor 1","hospital 1","1","6.55",
            "1/9/2022","2"));
        arrayList.add(new dates_class("date 1","20","الباطنة","doctor 1","hospital 1","1","6.55",
            "1/9/2022","2"));
        show_date_adapter adapter=new show_date_adapter(arrayList,this);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void add_new_date(View v)
    {
        FloatingActionButton floatingactionbutton=v.findViewById(R.id.add_new_date);
        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.user_framelayout,new booking_date()).addToBackStack(null).commit();
            }
        });
    }
}