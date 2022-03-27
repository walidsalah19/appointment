package com.example.booking.hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.booking.R;
import com.example.booking.user_access.login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class hospital_main extends AppCompatActivity {
   private BottomNavigationView bottomnavigation;
   private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_main);
        toolpar_intialize();
        bottom_navigation_method();
        move_fragment(new manage_doctors());
    }
    private void toolpar_intialize() {
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
    }
    private void bottom_navigation_method()
    {
        bottomnavigation=findViewById(R.id.hospital_bottomNavigationView);
        bottomnavigation.inflateMenu(R.menu.hospital);
        bottomnavigation.setSelectedItemId(0);
        bottomnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(R.id.hospital_manage_doctors==item.getItemId())
                {
                    move_fragment(new manage_doctors());
                }
                if(R.id.hospital_manage_clinics==item.getItemId())
                {
                    move_fragment(new manage_clinic());
                }
                if(R.id.hospital_logout==item.getItemId())
                {
                    startActivity(new Intent(hospital_main.this, login.class));
                }

                return false;
            }
        });
    }
    private void move_fragment(Fragment Fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.hospital_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
}