package com.example.booking.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.booking.R;
import com.example.booking.hospital.hospital_main;
import com.example.booking.hospital.manage_clinic;
import com.example.booking.hospital.manage_doctors;
import com.example.booking.user_access.login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class doctor_main_activity extends AppCompatActivity {
    private BottomNavigationView bottomnavigation;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main_activity);
        toolpar_intialize();
        bottom_navigation_method();
        move_fragment(new doctore_show_dates());
    }
    private void toolpar_intialize() {
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
    }
    private void bottom_navigation_method()
    {
        bottomnavigation=findViewById(R.id.doctor_bottomNavigationView);
        bottomnavigation.setSelectedItemId(0);
        bottomnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(R.id.doctor_date==item.getItemId())
                {
                    move_fragment(new doctore_show_dates());
                }
                if(R.id.doctor_logout==item.getItemId())
                {
                    startActivity(new Intent(doctor_main_activity.this, login.class));
                }

                return false;
            }
        });
    }
    private void move_fragment(Fragment Fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.doctor_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
}