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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class hospital_maintanace_doctor extends Fragment {

    private EditText name,room_number,hour,room_location,clinic_name;
    private Button update,delete;
    private String doc_id,hos_id,email,password;
    private FirebaseFirestore database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_hospital_maintanace_doctor, container, false);
        database=FirebaseFirestore.getInstance();
        intialization_tool(v);
        get_data();
        update_method();
        delete_method();
        return v;
    }

    private void delete_method() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detete_database();
            }
        });
    }

    private void detete_database() {
        database.collection("doctors").document(doc_id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "تم حذف بيانات الدكتور", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intialization_tool(View v) {
        name=v.findViewById(R.id.hospital_maintan_doctor_name);
        room_number=v.findViewById(R.id.hospital_maintan_doctor_roomnumber);
        hour=v.findViewById(R.id.hospital_maintan_doctor_hour);
        room_location=v.findViewById(R.id.hospital_maintan_doctor_r_location);
        clinic_name=v.findViewById(R.id.hospital_maintan_doctor_clinic);
        update=v.findViewById(R.id.hospital_maintan_doctor_data);
        delete=v.findViewById(R.id.hospital_delete_doctor);
    }

    private void get_data() {
        Bundle b=getArguments();
        name.setText(b.getString("name"));
        room_number.setText(b.getString("r_n"));
        hour.setText(b.getString("hour"));
        room_location.setText(b.getString("r_l"));
        clinic_name.setText(b.getString("c_n"));
        hos_id=b.getString("h_id");
        doc_id=b.getString("d_id");
        email=b.getString("email");
        password=b.getString("password");
    }
    private void update_method()
    {
         update.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 check_data();
             }
         });
    }

    private void check_data() {
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
        else if(TextUtils.isEmpty(clinic_name.getText().toString()))
        {
            clinic_name.setError("من فضلك أضف اسم العيادة");
        }
        else {
            updata_database();
        }
    }

    private void updata_database() {
        doctor_data data=new doctor_data(name.getText().toString(),clinic_name.getText().toString(),room_location.getText().toString()
                ,doc_id,room_number.getText().toString(),hour.getText().toString(),hos_id,email,password);
        database.collection("doctors").document(doc_id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "تم تعديل بيانات الدكتور", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}