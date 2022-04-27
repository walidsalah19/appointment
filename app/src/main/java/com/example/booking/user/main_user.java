package com.example.booking.user;

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

public class main_user extends AppCompatActivity {
    private BottomNavigationView bottomnavigation;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        move_fragment(new show_date());
        //toolpar_intialize();
        bottom_navigation_method();

    }
    private void toolpar_intialize() {
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
    }
    private void bottom_navigation_method()
    {
        bottomnavigation=findViewById(R.id.user_bottomNavigationView);
        bottomnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(R.id.user_calender_card==item.getItemId())
                {
                    move_fragment(new show_date());
                }
                if(R.id.user_manage_card==item.getItemId())
                {
                    move_fragment(new user_cardes());
                }
                if(R.id.user_logout==item.getItemId())
                {
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.signOut();
                    startActivity(new Intent(main_user.this, login.class));
                }

                return false;
            }
        });
    }
    private void move_fragment(Fragment Fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.user_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
}