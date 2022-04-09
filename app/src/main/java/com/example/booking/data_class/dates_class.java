package com.example.booking.data_class;

public class dates_class {
    String name,age,clinicAddress,doctoridid,hospitalid,pastionid,appointmentdata,bookingdate,appointmenttime,appointmentid,file_number;

    public dates_class(String file_number,String name, String age, String clinicAddress, String doctoridid, String hospitalid, String pastionid, String appointmentdata, String bookingdate, String appointmenttime,String appointmentid) {
        this.name = name;
        this.file_number=file_number;
        this.age = age;
        this.clinicAddress = clinicAddress;
        this.doctoridid = doctoridid;
        this.hospitalid = hospitalid;
        this.pastionid = pastionid;
        this.appointmentdata = appointmentdata;
        this.bookingdate = bookingdate;
        this.appointmenttime = appointmenttime;
        this.appointmentid = appointmentid;
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

    public String getDoctoridid() {
        return doctoridid;
    }

    public String getHospitalid() {
        return hospitalid;
    }

    public String getPastionid() {
        return pastionid;
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
