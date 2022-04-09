package com.example.booking.hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class hospital_info extends AppCompatActivity {
    private Button create_profile;
    private EditText name,phone,address;
    private FirebaseFirestore database;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);
        database=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        create_method();
        intialization_tool();
    }

    private void intialization_tool() {
        name=findViewById(R.id.hospital_info_name);
        phone=findViewById(R.id.hospital_info_phone);
        address=findViewById(R.id.hospital_info_address);
    }

    private void create_method()
    {
        create_profile=findViewById(R.id.hospital_add_profile_data);
        create_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              check_data();
            }
        });
    }

    private void check_data() {
        if (TextUtils.isEmpty(name.getText().toString()))
        {
            name.setError("من فضلك أضف اسم المستشفي ");
        }
        else if (TextUtils.isEmpty(phone.getText().toString()))
        {
            phone.setError("من فضلك أضف قم المحمول");

        }
        else if (TextUtils.isEmpty(address.getText().toString()))
        {
            address.setError("من فضلك أضف عنوان المستشفي");
        }
        else {
            add_to_database();
        }
    }

    private void add_to_database() {
        String h_id=auth.getCurrentUser().getUid().toString();
        HashMap<String, String> map=new HashMap<>();
        map.put("HospitalID",h_id);
        map.put("user_type","hospital");
        map.put("name",name.getText().toString());
        map.put("address",address.getText().toString());
        map.put("phone",phone.getText().toString());
        database.collection("users").document(h_id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(hospital_info.this, "تمت أضافة بيانات المستشفي ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(hospital_info.this, hospital_activity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(hospital_info.this, "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}