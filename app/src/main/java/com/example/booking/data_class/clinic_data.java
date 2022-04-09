package com.example.booking.data_class;

public class clinic_data {
    String name,clinic_id;

    public clinic_data(String name, String clinic_id) {
        this.name = name;
        this.clinic_id = clinic_id;
    }

    public String getName() {
        return name;
    }

    public String getClinic_id() {
        return clinic_id;
    }
}
