package com.example.booking.user;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class user_cardes extends Fragment {
    private EditText name_ar,name_en,file_num,birthday,expire,hospital;
    private Button btn;
    FirebaseAuth auth;
    FirebaseFirestore database;
    String id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user_cardes, container, false);
        auth=FirebaseAuth.getInstance();
        id=auth.getCurrentUser().getUid().toString();
        database=FirebaseFirestore.getInstance();
       intialize(v);
       get_card();
       add_card();
        return v;
    }
    private void intialize(View v)
    {
        name_ar=v.findViewById(R.id.user_card_name_ar);
        name_en=v.findViewById(R.id.user_card_name_en);
        file_num=v.findViewById(R.id.user_card_number);
        birthday=v.findViewById(R.id.user_card_birthday);
        expire=v.findViewById(R.id.user_card_expire);
        hospital=v.findViewById(R.id.user_card_hospital);
        btn=v.findViewById(R.id.user_card_button);
    }
    private void get_card()
    {
        database.collection("cards").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()) {
                    name_ar.setText(task.getResult().get("arname").toString());
                    name_en.setText(task.getResult().get("enname").toString());
                    birthday.setText(task.getResult().get("birthday").toString());
                    file_num.setText(task.getResult().get("filenumber").toString());
                    expire.setText(task.getResult().get("cardexpiredate").toString());
                    hospital.setText(task.getResult().get("hospitalcontactinfo").toString());
                }
                else
                {
                    btn.setText("أضافة");
                }
            }
        });
    }
        private void add_card()
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check_data();
                }
            });
        }

    private void check_data() {
        if (TextUtils.isEmpty(name_ar.getText().toString()))
        {
            name_ar.setError("من فضلك أضف اسمك ");
        }
        else if (TextUtils.isEmpty(name_en.getText().toString()))
        {
            name_en.setError("please add you'r name ");
        }
        else if (TextUtils.isEmpty(file_num.getText().toString()))
        {
            file_num.setError("من فضلك أضف رقم الملف ");
        }
        else if (TextUtils.isEmpty(birthday.getText().toString()))
        {
            birthday.setError("من فضلك أضف تاريخ الميلاد ");
        }
        else if (TextUtils.isEmpty(expire.getText().toString()))
        {
            expire.setError("من فضلك أضف تاريخ إنتهاء البطاقه ");
        }
        else if (TextUtils.isEmpty(hospital.getText().toString()))
        {
            hospital.setError("من فضلك أضف  معلومات تواصل المستشفي");
        }
        else
        {
            add_todatabase();
        }
    }

    private void add_todatabase() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("arname",name_ar.getText().toString());
        map.put("enname",name_en.getText().toString());
        map.put("birthday",birthday.getText().toString());
        map.put("filenumber",file_num.getText().toString());
        map.put("cardexpiredate",expire.getText().toString());
        map.put("hospitalcontactinfo",hospital.getText().toString());
        database.collection("cards").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              if (task.isSuccessful())
              {
                  Toast.makeText(getActivity(), "تم التعديل", Toast.LENGTH_SHORT).show();
              }
            }
        });

    }

}