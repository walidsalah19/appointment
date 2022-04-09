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

public class hospital_maintanance_clinic extends Fragment {

    private String name,clinic_id,h_id;
    private FirebaseAuth auth;
    private FirebaseFirestore database;
    private EditText clinic;
    private Button update,delete;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_hospital_maintanance_clinic, container, false);
        get_clinic_data();
        firebase_tool();
        intialization_tool(v);
        update_method();
        delete_method();
        return v;
    }

    private void delete_method() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delee_database();
            }
        });
    }

    private void delee_database() {
        database.collection("hospital").document(h_id).collection("clinic").document(clinic_id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "تم حذف بيانات العيادة", Toast.LENGTH_SHORT).show();
                    clinic.setText("");
                }
                else
                {
                    Toast.makeText(getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intialization_tool(View v) {
        clinic=v.findViewById(R.id.hospital_maintenance_clinic);
        update=v.findViewById(R.id.hospital_update_clinic);
        delete=v.findViewById(R.id.hospital_delete_clinic);
        clinic.setText(name);
    }

    private void firebase_tool() {
        auth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();
        h_id=auth.getCurrentUser().getUid().toString();

    }

    private void get_clinic_data()
    {
        Bundle b=getArguments();
        name=b.getString("name");
        clinic_id=b.getString("id");
    }
    private void update_method()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_database();
            }
        });
    }

    private void update_database() {
        if(TextUtils.isEmpty(clinic.getText().toString()))
        {
            clinic.setError("من فضلك أضف اسم العيادة");
        }
        else
        {
            clinic_data data=new clinic_data(clinic.getText().toString(),clinic_id);
            database.collection("hospital").document(h_id).collection("clinic").document(clinic_id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful())
               {
                   Toast.makeText(getActivity(), "تم تعديل بيانات العيادة", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
               }
                }
            });
        }
    }

}