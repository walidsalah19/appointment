package com.example.booking.user_access;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking.R;
import com.example.booking.data_class.doctor_data;
import com.example.booking.doctor.doctor_main_activity;
import com.example.booking.doctor.get_doctor_id;
import com.example.booking.hospital.adapters.doctor_recycler_adapter;
import com.example.booking.hospital.hospital_activity;
import com.example.booking.hospital.manage_doctors;
import com.example.booking.user.main_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class login extends AppCompatActivity {
    private Button login;
    private EditText email,password;
    private TextView registration ,forget_password_tex;
    private FirebaseFirestore database;
    private FirebaseAuth auth;
    private  SweetAlertDialog  pDialogLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sweetalert();
        Firebase_tool();
    login_method();
    registration_method();
    forget_passeord();
    show_password_method();
    }

    @Override
    protected void onStart() {
        super.onStart();
        sweetalert();
        FirebaseUser user=auth.getCurrentUser();
        if (user !=null)
        {
           check_user_type (user.getUid().toString());
        }
    }

    private void forget_passeord() {
        forget_password_tex=findViewById(R.id.forgit_password);
        forget_password_tex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,forget_password.class));
            }
        });
    }

    private void login_method() {
        login=findViewById(R.id.login);
        password=findViewById(R.id.email_password_text);
        email=findViewById(R.id.email_edittext);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_data();
            }
        });
    }
    private void check_data()
    {
        if(TextUtils.isEmpty(email.getText().toString()))
        {
           email.setError("من فضلك أدخل البريد الإلكتروني");
        }
        else if(TextUtils.isEmpty(password.getText().toString()))
        {
            password.setError("من فضلك أدخل كلمة المرور");
        }
       else
        {
           check_if_doctor();
        }
    }
    Boolean found=false;
    private void check_if_doctor() {
        database.collection("doctors").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult())
                    {
                       String email_s=snapshot.get("email").toString();
                       String password_s=snapshot.get("password").toString();
                        if (email_s.equals(email.getText().toString()) &&password_s.equals(password.getText().toString()) ) {
                            found=true;
                            String id=snapshot.get("doctorid").toString();
                            get_doctor_id.setDoc_id(id);
                            startActivity(new Intent(login.this, doctor_main_activity.class));
                            finish();
                        }

                    }
                    if (!found) {
                        login_to_account();
                    }

                }
            }
        });
    }

    private void login_to_account() {
          auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful())
             {
                 check_user_type(task.getResult().getUser().getUid());
             }
             else
             {
                 Toast.makeText(login.this, "الرجاء المحاولة مرة أخري ", Toast.LENGTH_SHORT).show();
             }
              }
          });
    }
    private void sweetalert()
    {
        pDialogLoading = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialogLoading.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialogLoading.setTitleText("sign in ");
        pDialogLoading.setCancelable(false);
    }
    private void check_user_type(String user_id) {

        pDialogLoading.show();
            database.collection("hospital").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                          pDialogLoading.dismiss();
                                startActivity(new Intent(login.this, hospital_activity.class));
                        }
                        else{
                            pDialogLoading.dismiss();
                            startActivity(new Intent(login.this, main_user.class));
                        }
                    }

                }
            });
    }

    private void  Firebase_tool()
    {
        auth=FirebaseAuth.getInstance();
        database= FirebaseFirestore.getInstance();
    }

    private void registration_method()
    {
        registration=findViewById(R.id.new_account);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,registration.class));
            }
        });
    }
    private void show_password_method() {
        CheckBox show_password=findViewById(R.id.show_password_login);
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (show_password.isChecked()) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

            }
        });
    }
}