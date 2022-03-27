package com.example.booking.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.booking.R;
import com.example.booking.user.user_data_profile;
import com.example.booking.user_access.registration;

public class hospital_info extends AppCompatActivity {
    private Button create_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);
        create_method();
    }
    private void create_method()
    {
        create_profile=findViewById(R.id.hospital_add_profile_data);
        create_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(hospital_info.this, hospital_main.class));

            }
        });
    }
}