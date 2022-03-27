package com.example.booking.data_class;

public class dates_class {
    String name,age,clinic,doctor,hospital,file_number,time,date,room_number;

    public dates_class(String name, String age, String clinic, String doctor, String hospital, String file_number, String time, String date,String room_number) {
        this.name = name;
        this.age = age;
        this.clinic = clinic;
        this.doctor = doctor;
        this.hospital = hospital;
        this.file_number = file_number;
        this.time = time;
        this.date = date;
        this.room_number=room_number;
    }

    public String getRoom_number() {
        return room_number;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getClinic() {
        return clinic;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public String getFile_number() {
        return file_number;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
