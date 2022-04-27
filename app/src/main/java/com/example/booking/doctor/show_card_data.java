package com.example.booking.doctor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.booking.R;
import com.example.booking.data_class.move_data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class show_card_data extends Fragment {

    private EditText ar_name,en_name,barth,file_number,expire_date,hospital_data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_show_card_data, container, false);

        intialization(v);
        return v;
    }

    private void intialization(View v) {
        ar_name=v.findViewById(R.id.doctor_user_ar_name);
        en_name=v.findViewById(R.id.doctor_user_en_name);
        barth=v.findViewById(R.id.doctor_user_barth);
        file_number=v.findViewById(R.id.doctor_user_file_num);
        expire_date=v.findViewById(R.id.doctor_user_expire);
        hospital_data=v.findViewById(R.id.doctor_user_hospital_con);
        get_data_firebase();
    }
    private void get_data_firebase()
    { FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("cards").document(move_data.getP_id()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                   if(task.getResult().exists()) {
                       ar_name.setText(task.getResult().get("arname").toString());
                       en_name.setText(task.getResult().get("enname").toString());
                       barth.setText(task.getResult().get("birthday").toString());
                       file_number.setText(task.getResult().get("filenumber").toString());
                       expire_date.setText(task.getResult().get("cardexpiredate").toString());
                       hospital_data.setText(task.getResult().get("hospitalcontactinfo").toString());
                   }
            }
        });
    }
}