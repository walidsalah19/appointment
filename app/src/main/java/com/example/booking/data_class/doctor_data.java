package com.example.booking.data_class;

public class doctor_data {
    String name,clinicname,clinicaddress,doctorid,roomnumber,workinghours,hospitalId,email,password;

    public doctor_data(String name, String clinicname, String clinicaddress, String doctorid, String roomnumber, String workinghours, String hospitalId, String email, String password) {
        this.name = name;
        this.clinicname = clinicname;
        this.clinicaddress = clinicaddress;
        this.doctorid = doctorid;
        this.roomnumber = roomnumber;
        this.workinghours = workinghours;
        this.hospitalId = hospitalId;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getClinicname() {
        return clinicname;
    }

    public String getClinicaddress() {
        return clinicaddress;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public String getWorkinghours() {
        return workinghours;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
