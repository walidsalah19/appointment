package com.example.booking.hospital;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booking.R;
import com.example.booking.data_class.clinic_data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;


public class add_new_clinic extends Fragment {
    private EditText clinic;
    private Button add;
    private FirebaseAuth auth;
    private FirebaseFirestore database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_add_new_clinic, container, false);
       intialization_tool(v);
       firebaseInit();
       add_btn();
       return v;
    }

    private void add_btn() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          check_data();
            }
        });
    }

    private void check_data() {
        if (TextUtils.isEmpty(clinic.getText().toString()))
        {
            clinic.setError("من فضلك أدخل اسم العيادة");
        }
        else
        {
            add_to_database();
        }
    }

    private void add_to_database() {
        String user_id = auth.getCurrentUser().getUid().toString();
        String clinic_id = UUID.randomUUID().toString();
        clinic_data data=new clinic_data(clinic.getText().toString(),clinic_id,user_id);
        database.collection("clinic").document(clinic_id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "تم أضافة عيادة جديدة", Toast.LENGTH_SHORT).show();
                    clinic.setText("");
                }
                else
                {
                    Toast.makeText(getActivity(), "حدث خطأ في الأضافة ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void intialization_tool(View v)
    {
        clinic=v.findViewById(R.id.hospital_add_new_clinic);
        add=v.findViewById(R.id.hospital_add_clinic_btn);
    }
    private void firebaseInit()
    {
        auth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();
    }
}