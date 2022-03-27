package com.example.booking.user_access;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_password extends AppCompatActivity {
   private EditText email;
   private Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        change_method();
    }

    private void change_method() {
        change=findViewById(R.id.forgit_change_password);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_password();
            }
        });
    }

    private void change_password() {
        email=findViewById(R.id.forgit_email_text);
        if (email.getText().toString().equals(""))
        {
            email.setError("من فضلك أدخل البريد الإلكتروني");
        }
        else
        {
            FirebaseAuth auth=FirebaseAuth.getInstance();
            auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful())
               {
                   Toast.makeText(forget_password.this, "تم إرسال الي البريد الإلكتروني", Toast.LENGTH_SHORT).show();
               }
                }
            });
        }
    }
}