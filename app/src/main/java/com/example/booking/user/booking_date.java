package com.example.booking.user;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.booking.R;
import com.example.booking.data_class.dates_class;
import com.example.booking.data_class.move_data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.LoggingMXBean;


public class booking_date extends Fragment {
  private Spinner hospital,clinic,doctor;
  private FirebaseFirestore database;
  private String hospital_id,doctor_id="",name="",age="",booking_date,user_id;
  private EditText date,time;
  private int file_num=0;
  private TextView time_picker,date_picker;
  private   ArrayList<String> dates;
  private ArrayList<String> appointment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_booking_date, container, false);
        database=FirebaseFirestore.getInstance();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        user_id=auth.getCurrentUser().getUid().toString();
        booking_assest_class assest=new booking_assest_class(v,booking_date.this);
        assest.hospital();
        assest.get_patient_data();
        booking_date=assest.get_date();
       // get_patient_data();
        get_file_number();
        intialize(v);
        // hospital();
         booking_method(v);
         date_picker_method();
         time_picker_method();
        return v;
    }
    private void intialize(View v)
    {
        /*hospital = (Spinner) v.findViewById(R.id.user_booking_data_hospital);
        clinic =v.findViewById(R.id.user_booking_data_clinic);
        doctor =v.findViewById(R.id.user_booking_data_doctor);*/
        date=v.findViewById(R.id.user_booking_data_date);
        time=v.findViewById(R.id.user_booking_data_time);
        time_picker=v.findViewById(R.id.time_picker);
        date_picker=v.findViewById(R.id.date_picker);
    }
    private void date_picker_method()
    {
        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
      DatePickerDialog picker = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
            }
        });
    }

    private void time_picker_method()
    {
        time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       Calendar mCalendar = Calendar.getInstance();

        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        time.setText(hourOfDay + "." + minute);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
            }
        });
    }
    private void booking_method(View v) {

        Button booking=(Button)v.findViewById(R.id.user_booking_date);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    check_date();

            }
        });
    }

    private void check_date()  {
        if(TextUtils.isEmpty( date.getText().toString()))
        {
            date.setError("من فضلك ادخل تاريخ الحجز");
        }
        else if(TextUtils.isEmpty( time.getText().toString()))
        {
            time.setError("من فضلك ادخل وقت الحجز");
        }
        else
        {
                get_timeperiod(time.getText().toString());
                get_time_database(date.getText().toString());
        }
    }
    private void get_time_database(String date) {
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        appointment=new ArrayList<String>();
        database.collection("dates").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult())
                    {
                        String d=snapshot.get("appointmentdata").toString();
                        String t=snapshot.get("appointmenttime").toString();
                        String doc_id=snapshot.get("doctorid").toString();
                        if (d.equals(date)&&doc_id.equals(move_data.getDoctor_id()))
                        {
                            appointment.add(t);

                        }
                    }
                    check_containt();
                }
            }
        });
    }
    private void get_timeperiod(String time)  {

        dates=new ArrayList<String>();
        ArrayList<String> arr=new ArrayList<String>();
        arr.add(time);
        dates.add(time);
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH.mm" );
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 10; i++) {
                Date d = dateFormat2.parse(dates.get(i));
                cal.setTime(d);
                cal.add(Calendar.MINUTE, 1);
                dates.add(dateFormat2.format(cal.getTime()));
                Log.d("this",dateFormat2.format(cal.getTime())+"\n");

            }
            for (int i = 0; i < 10; i++) {
                Date d = dateFormat2.parse(arr.get(i));
                cal.setTime(d);
                cal.add(Calendar.MINUTE, -1);
                arr.add(dateFormat2.format(cal.getTime()));
            }
            for (String a:arr)
            {
                dates.add(a);
                Log.d("this",a+"\n");

            }
        }
        catch (ParseException e) {
             Log.d("this",e.getMessage()+"\n");
        }
    }
    private void check_containt()  {

        boolean check = false;
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH.mm");
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < dates.size(); i++) {
                Log.d("st1", dates.get(i) + "\n");
                for (int h = 0; h < appointment.size(); h++) {
                    Date d = dateFormat2.parse(appointment.get(h));
                    cal.setTime(d);
                  String tim=  dateFormat2.format(cal.getTime());
                    Log.d("st2", tim + "\n");
                    if (dates.get(i).equals(tim)) {
                        check = true;
                        Toast.makeText(getActivity(), "من فضلك اختر وقت اخر", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        }catch (Exception e)
        {
            Log.d("error",e.getMessage()+"\n");
        }
        if (!check) {
            add_to_database();
        }
    }
    private void add_to_database() {
      // get_date();

       String date_id= UUID.randomUUID().toString();
        dates_class data=new dates_class(move_data.getUser_name(),move_data.getUser_age(),move_data.getClinic(),move_data.getDoctor_id(),move_data.getHospital_id(),user_id,date.getText().toString(),booking_date
        ,time.getText().toString(),date_id,file_num+"");
        database.collection("dates").document(date_id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "تم حجز موعد ", Toast.LENGTH_SHORT).show();
                   /* hospital.setSelection(0);
                    clinic.setSelection(0);
                    doctor.setSelection(0);
                    date.setText("");
                    time.setText("");*/
                }
                else
                {
                    Toast.makeText(getActivity(), "حدث خطأ ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
   /* private void get_date()
    {
      booking_date=  new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
    }*/
    private void get_file_number()
    {
        database.collection("dates").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult()) {
                       file_num+=1;
                    }
                }
            }
        });
    }
   /* private void get_patient_data()
    {
     database.collection("patient").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
         @Override
         public void onComplete(@NonNull Task<DocumentSnapshot> task) {
             if (task.getResult().exists())
             {
                 name=task.getResult().get("name").toString();
                 age=task.getResult().get("age").toString();
             }
         }
     });
    }

    private void hospital()
    {
        List<String> hospital_name = new ArrayList<String>();
        List<String> hospital_id_l = new ArrayList<String>();
        database.collection("hospital").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult()) {
                            hospital_id_l.add(snapshot.get("HospitalID").toString());
                            hospital_name.add(snapshot.get("name").toString());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, hospital_name);
                    hospital.setAdapter(dataAdapter);
                }
            }
        });
      hospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              if (! hospital_id_l.isEmpty()) {
                  hospital_id = hospital_id_l.get(i);
                  get_selected_clinic(hospital_id);
              }
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {
          }
      });
    }

    private void get_selected_clinic(String hospital_id) {
        List<String> c_id = new ArrayList<String>();
        ArrayList<String> clinic_name=new ArrayList<String>();
        database.collection("clinic").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult()) {
                        String hos_id=snapshot.get("hospital_id").toString();
                        if (hos_id.equals(hospital_id)) {
                            c_id.add(snapshot.get("clinic_id").toString());
                            clinic_name.add(snapshot.get("name").toString());
                        }
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, clinic_name);
                    clinic.setAdapter(dataAdapter);
                }
            }
        });
        clinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                get_selected_doctor(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void get_selected_doctor(String c_name) {
        ArrayList<String> docotr_name=new ArrayList<String>();
        ArrayList<String> d_id=new ArrayList<String>();
        database.collection("doctors").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult()) {
                        String hos_id=snapshot.get("hospitalId").toString();
                        String c_n=snapshot.get("clinicname").toString();
                        if (hos_id.equals(hospital_id) && c_n.equals(c_name)) {
                            docotr_name.add(snapshot.get("name").toString());
                            d_id.add(snapshot.get("doctorid").toString());
                        }
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item,  docotr_name);
                    doctor.setAdapter(dataAdapter);
                }
            }
        });
        doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              if (d_id.size()>0) {
                  doctor_id = d_id.get(i);
              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }*/


}