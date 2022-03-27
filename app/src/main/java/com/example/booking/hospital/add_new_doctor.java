package com.example.booking.hospital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.booking.R;

import java.util.ArrayList;
import java.util.List;


public class add_new_doctor extends Fragment {


    private Spinner doctor_clinic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_add_new_doctor, container, false);
        spinner_program(v);
        return v;
    }
    private void spinner_program(View v)
    {
        List<String> categories = new ArrayList<String>();
        categories.add("عيادة الباطنه");
        categories.add("عيادة الجلدية");

        doctor_clinic = (Spinner) v.findViewById(R.id.hospital_add_doctor_clinic);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, categories);
       doctor_clinic.setAdapter(dataAdapter);


    }
}