package com.example.booking.user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class user_data_profile extends AppCompatActivity {
    private EditText name,id,birthday,disease,phone;
   private Spinner blood_type,gender;
    private DatePickerDialog datePickerDialog;
    final Calendar myCalendar = Calendar.getInstance();
    private TextView text_age,date_picker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_data_file);
        intiate();
        create();
        blood_type();
        gender_type();
        text_age_method();
    }
    private void intiate()
    {
        name=findViewById(R.id.user_proifle_name);
        id=findViewById(R.id.user_proifle_id);
        birthday=findViewById(R.id.user_profile_birthday);
        disease=findViewById(R.id.user_profile_desicess);
        phone=findViewById(R.id.user_profile_phone);
        text_age=findViewById(R.id.user_set_age_profile);
        date_picker=findViewById(R.id.user_date_picker_profile);
    }
    private void create()
    {
        Button create=(Button) findViewById(R.id.user_add_profile_data);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               check_data();
            }
        });
    }

    private void check_data() {
       if(TextUtils.isEmpty(name.getText().toString()))
       {
           name.setError("من فضلك أدخل اسمك");
       }
       else if (TextUtils.isEmpty(id.getText().toString())&&id.getText().toString().length()>16&&id.getText().toString().length()<16)
       {
           id.setError("من فضلك أدخل رقم الهويه");
       }
       else if (TextUtils.isEmpty(birthday.getText().toString()))
       {
           birthday.setError("من فضلك اضف تاريخ ميلادك");
       }
       else if (TextUtils.isEmpty(disease.getText().toString()))
       {
           disease.setError("من فضلك اضف اذا كان لديك اي أعراض نادرة");
       }
       else if (TextUtils.isEmpty(phone.getText().toString()) && phone.getText().toString().length()>12&&phone.getText().toString().length()<12)
       {
           phone.setError("من فضلك أدخل رقم الجوال");
       }
       else if (text_age.getText().toString().equals("العمر"))
       {
           Toast.makeText(this, "من فضلك اضف العمر", Toast.LENGTH_SHORT).show();
       }
       else
       {
           add_todatabase();
       }

    }

    private void add_todatabase() {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String u_id=auth.getCurrentUser().getUid().toString();

        HashMap<String, String> map=new HashMap<>();
         map.put("name",name.getText().toString());
        map.put("birthday",text_age.getText().toString());
        map.put("pationtid",id.getText().toString());
        map.put("phone",phone.getText().toString());
        map.put("gender",gender.getSelectedItem().toString());
        map.put("blood_type",blood_type.getSelectedItem().toString());
        map.put("age",birthday.getText().toString());
        map.put("ratesymptoms",phone.getText().toString());
        map.put("id",u_id);
        map.put("user_type","user");
        FirebaseFirestore database=FirebaseFirestore.getInstance();
        database.collection("patient").document(u_id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                   startActivity(new Intent(user_data_profile.this,main_user.class));
                }
                else {
                    Toast.makeText(user_data_profile.this, "حدث خطاء", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void text_age_method()
    {

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog_method();
            }
        });
    }
    private void datePickerDialog_method()
    {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                text_age.setText(year + "-" + monthOfYear+1 + "-" + dayOfMonth);
            }
        };
        datePickerDialog=  new DatePickerDialog(user_data_profile.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void blood_type()
    {
        List<String> categories = new ArrayList<String>();
        categories.add("o");
        categories.add("A");
        categories.add("B");

        blood_type = (Spinner) findViewById(R.id.user_add_blood_type);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(user_data_profile.this, R.layout.spinner_item, categories);
        blood_type.setAdapter(dataAdapter);
    }
    private void gender_type()
    {
        List<String> categories = new ArrayList<String>();
        categories.add("رجل ");
        categories.add("انثي");

        gender = (Spinner) findViewById(R.id.user_add_gender);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(user_data_profile.this, R.layout.spinner_item, categories);
        gender.setAdapter(dataAdapter);
    }
}