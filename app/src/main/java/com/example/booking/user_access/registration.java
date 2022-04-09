package com.example.booking.user_access;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking.R;
import com.example.booking.hospital.hospital_info;
import com.example.booking.user.user_data_profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class registration extends AppCompatActivity {
    private EditText email,password,conform;
    private TextView return_login;
    private RadioButton hospital,user;
    private FirebaseFirestore database;
    private FirebaseAuth auth;
    private String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Firebase_tool();
        intialization();
        method_registiration();
        show_password_method();
    }
    private void  Firebase_tool()
    {
        auth= FirebaseAuth.getInstance();
        database= FirebaseFirestore.getInstance();
    }
    private void  method_registiration()
    {
        Button registration=(Button) findViewById(R.id.registration_button);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            check_data();
            }
        });
    }

    private void check_data() {
        if(TextUtils.isEmpty(email.getText().toString()))
        {
            email.setError("من فضلك أدخل الإيميل الخاص بك");
        }
        else if(TextUtils.isEmpty(password.getText().toString()))
        {
            password.setError("من فضلك أدخل كلمة المرور");
        }
        else if(! password.getText().toString().equals(conform.getText().toString()))
        {
            conform.setError("كلمة المرور غير متطابقة");
        }
        else
        {
            complete_registration();
        }
    }

    private void complete_registration() {
        auth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    user_id=task.getResult().getUser().getUid();
                    check_user_type();

                }
                else
                {
                    Toast.makeText(registration.this, "حدث خطأ في التسجل", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void check_user_type() {
        if (user.isChecked())
        {
            add_to_database("user");
        }
        else if(hospital.isChecked())
        {
            add_to_database("hospital");
        }
    }

    private void add_to_database(String user) {
        HashMap<String, String> user_map=new HashMap<>();
        user_map.put("email",email.getText().toString());
        user_map.put("user_type",user);
        database.collection("users").document(user_id).set(user_map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(registration.this, "تمت عملية التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                    go_to_activity(user);
                }
                else
                {
                    Toast.makeText(registration.this, "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void go_to_activity(String user) {
        if (user.equals("user"))
        {
            startActivity(new Intent(registration.this, user_data_profile.class));
            finish();
        }
        else
        {
            startActivity(new Intent(registration.this, hospital_info.class));
            finish();
        }
    }
    private void show_password_method() {
       CheckBox  show_password=findViewById(R.id.show_password_regist);
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (show_password.isChecked()) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                    conform.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    conform.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

            }
        });
    }
    private void intialization()
    {
        email=findViewById(R.id.email_registration);
        password=findViewById(R.id.password_registration);
        conform=findViewById(R.id.confirm_registration);
        return_login=findViewById(R.id.return_to_login);
        hospital=findViewById(R.id.redio_hospital);
        user=findViewById(R.id.radio_user);
        hospital.setChecked(true);
    }
}