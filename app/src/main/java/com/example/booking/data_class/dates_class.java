package com.example.booking.data_class;

public class dates_class {
    String name,age,clinicAddress,doctorid,hospitalid,patienid,appointmentdata,bookingdate,appointmenttime,appointmentid,file_number;


    public dates_class(String name, String age, String clinicAddress, String doctorid, String hospitalid, String patienid, String appointmentdata, String bookingdate, String appointmenttime, String appointmentid, String file_number) {
        this.name = name;
        this.age = age;
        this.clinicAddress = clinicAddress;
        this.doctorid = doctorid;
        this.hospitalid = hospitalid;
        this.patienid = patienid;
        this.appointmentdata = appointmentdata;
        this.bookingdate = bookingdate;
        this.appointmenttime = appointmenttime;
        this.appointmentid = appointmentid;
        this.file_number = file_number;
    }

    public String getFile_number() {
        return file_number;
    }

    public String getAppointmentid() {
        return appointmentid;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public String getHospitalid() {
        return hospitalid;
    }

    public String getPationid() {
        return patienid;
    }

    public String getAppointmentdata() {
        return appointmentdata;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public String getAppointmenttime() {
        return appointmenttime;
    }
}
