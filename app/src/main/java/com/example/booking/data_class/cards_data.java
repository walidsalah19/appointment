package com.example.booking.data_class;

public class cards_data {
    String cardid,pationid,doctorid,hospitalcontactinfo,arname,enname,birthday,cardexpiredate,filenumber;

    public cards_data(String cardid, String pationid, String doctorid, String hospitalcontactinfo, String arname,
                      String enname, String birthday, String cardexpiredate,String filenumber) {
        this.cardid = cardid;
        this.pationid = pationid;
        this.doctorid = doctorid;
        this.hospitalcontactinfo = hospitalcontactinfo;
        this.arname = arname;
        this.enname = enname;
        this.birthday = birthday;
        this.cardexpiredate = cardexpiredate;
        this.filenumber=filenumber;
    }

    public String getCardid() {
        return cardid;
    }

    public String getPationid() {
        return pationid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public String getHospitalcontactinfo() {
        return hospitalcontactinfo;
    }

    public String getArname() {
        return arname;
    }

    public String getEnname() {
        return enname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCardexpiredate() {
        return cardexpiredate;
    }
}
