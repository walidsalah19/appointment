package com.example.booking.hospital;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.booking.R;
import com.example.booking.data_class.doctor_data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class new_doctor extends Fragment {
    private List<String> clinic;
    private Spinner doctor_clinic;
    private String h_id;
    private EditText name,room_number,hour,room_location,email,password;
    private Button add;
    private FirebaseFirestore database;
    private FirebaseAuth firebaseAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_new_doctor, container, false);
        Bundle b=getArguments();
        h_id=b.getString("h_id");
        database=FirebaseFirestore.getInstance();
        spinner_program(v);
        intialization_tool(v);
        add_method();
        return v;
    }
    private void intialization_tool(View v)
    {
        name=v.findViewById(R.id.hospital_add_doctor_name_);
        room_number=v.findViewById(R.id.hospital_add_doctor_room_number_);
        hour=v.findViewById(R.id.hospital_add_doctor_hour_);
        room_location=v.findViewById(R.id.hospital_add_doctor_room_location_);
        email=v.findViewById(R.id.hospital_add_doctor_email_);
        password=v.findViewById(R.id.hospital_add_doctor_password_);
        add=v.findViewById(R.id.hospital_add_doctor_data_);



    }
    private void add_method()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_d_data();
            }
        });
    }

    private void check_d_data() {
        if (TextUtils.isEmpty(name.getText().toString()))
        {
            name.setError("من فضلك أضف اسم الدكتور");
        }

        else if (TextUtils.isEmpty(room_number.getText().toString()))
        {
            room_number.setError("من فضلك أضف رقم الغرفة");
        }
        else if (TextUtils.isEmpty(hour.getText().toString()))
        {
            hour.setError("من فضلك أضف عدد ساعات العمل ");
        }
        else if (TextUtils.isEmpty(room_location.getText().toString()))
        {
            room_location.setError("من فضلك أضف عنوان الغرفة");
        }
        else if (TextUtils.isEmpty(email.getText().toString()))
        {
            email.setError("من فضلك أضف البريد الإلكتروني");
        }
        else if (TextUtils.isEmpty(name.getText().toString()))
        {
            password.setError("من فضلك أضف كلمة المرور");
        }
        else if (doctor_clinic.getSelectedItem().toString().equals("اختر عيادة"))
        {
            password.setError("من فضلك اختر عيادة");
        }
        else
        {
           add_d_to_databse(UUID.randomUUID().toString());
        }
    }
    private void add_d_to_databse(String doc_id) {

        doctor_data data=new doctor_data(name.getText().toString(),doctor_clinic.getSelectedItem().toString(),room_location.getText().toString()
                ,doc_id,room_number.getText().toString(),hour.getText().toString(),h_id,email.getText().toString(),password.getText().toString());
        database.collection("doctors").document(doc_id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "تم أضافة دكتور", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void spinner_program(View v)
    {

        clinic = new ArrayList<String>();
        doctor_clinic = (Spinner) v.findViewById(R.id.hospital_add_doctor_clinic_);
        get_clinic_databases();

    }
    private void get_clinic_databases(){
        clinic.add("اختر عيادة");
        database.collection("clinic").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult())
                    {
                        String id=snapshot.get("hospital_id").toString();
                        if(h_id.equals(id)) {
                            clinic.add(snapshot.get("name").toString());
                        }
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, clinic);
                    doctor_clinic.setAdapter(dataAdapter);
                }
            }
        });
    }
}