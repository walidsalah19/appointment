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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class hospital_activity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private Toolbar toolbar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_activity);
        auth=FirebaseAuth.getInstance();
        tool_bar();
        navigation();
        move_fragment(new manage_doctors());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        chack_login();
    }

    private void tool_bar() {
        toolbar=findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
    }
    private void navigation()
    {
        navigationView=findViewById(R.id.hospital_bottomNavigationView);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
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
                    startActivity(new Intent(hospital_activity.this, login.class));
                    finish();
                }

                return false;
            }
        });
    }
    private void move_fragment(Fragment Fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.hospital_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
    private void chack_login()
    {
        FirebaseUser user=auth.getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(hospital_activity.this, login.class));
            finish();
        }
    }

}