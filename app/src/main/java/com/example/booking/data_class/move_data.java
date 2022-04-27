package com.example.booking.data_class;

public class move_data {
    static  String Appointmentid,p_id,doctor_id,hospital_id,user_name,user_age,clinic;

    public static String getClinic() {
        return clinic;
    }

    public static void setClinic(String clinic) {
        move_data.clinic = clinic;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        move_data.user_name = user_name;
    }

    public static String getUser_age() {
        return user_age;
    }

    public static void setUser_age(String user_age) {
        move_data.user_age = user_age;
    }

    public static String getDoctor_id() {
        return doctor_id;
    }

    public static void setDoctor_id(String doctor_id) {
        move_data.doctor_id = doctor_id;
    }

    public static String getHospital_id() {
        return hospital_id;
    }

    public static void setHospital_id(String hospital_id) {
        move_data.hospital_id = hospital_id;
    }

    public static String getAppointmentid() {
        return Appointmentid;
    }

    public static void setAppointmentid(String appointmentid) {
        Appointmentid = appointmentid;
    }

    public static String getP_id() {
        return p_id;
    }

    public static void setP_id(String p_id) {
        move_data.p_id = p_id;
    }
}
