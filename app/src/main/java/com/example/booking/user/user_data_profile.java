package com.example.booking.user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.booking.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class user_data_profile extends AppCompatActivity {
   private Spinner blood_type,gender;
    private DatePickerDialog datePickerDialog;
    final Calendar myCalendar = Calendar.getInstance();
    private TextView text_age;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_data_file);
        create();
        blood_type();
        gender_type();
        text_age_method();
    }
    private void create()
    {
        Button create=(Button) findViewById(R.id.user_add_profile_data);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_data_profile.this,main_user.class));
            }
        });
    }
    private void text_age_method()
    {
        text_age=findViewById(R.id.user_set_age_profile);
        text_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog_method();
            }
        });
    }
    private void datePickerDialog_method()
    {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                //text_date.setText(year + "-" + monthOfYear+1 + "-" + dayOfMonth);
            }
        };
        datePickerDialog=  new DatePickerDialog(user_data_profile.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void blood_type()
    {
        List<String> categories = new ArrayList<String>();
        categories.add("o");
        categories.add("A");
        categories.add("B");

        blood_type = (Spinner) findViewById(R.id.user_add_blood_type);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(user_data_profile.this, R.layout.spinner_item, categories);
        blood_type.setAdapter(dataAdapter);
    }
    private void gender_type()
    {
        List<String> categories = new ArrayList<String>();
        categories.add("male");
        categories.add("female");

        gender = (Spinner) findViewById(R.id.user_add_gender);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(user_data_profile.this, R.layout.spinner_item, categories);
        gender.setAdapter(dataAdapter);
    }
}