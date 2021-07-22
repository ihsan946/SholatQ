package com.ihsan946.sholatq.model;

public class Sholatqmodel {

    private String Ip_device;
    private String hasil;
    private String [] name_jadwalsholat;
    private String [] jadwal_sholat;
    private int background_jadwal;

    public String getIp_device() {
        return Ip_device;
    }

    public void setIp_device(String ip_device) {
        this.Ip_device = ip_device;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }


    public String[] getName_jadwalsholat() {
        return name_jadwalsholat;
    }

    public void setName_jadwalsholat(String[] name_jadwalsholat) {
        this.name_jadwalsholat = name_jadwalsholat;
    }

    public String[] getJadwal_sholat() {
        return jadwal_sholat;
    }

    public void setJadwal_sholat(String[] jadwal_sholat) {
        this.jadwal_sholat = jadwal_sholat;
    }

    //

    public int getBackground_jadwal() {
        return background_jadwal;
    }

    public void setBackground_jadwal(int background_jadwal) {
        this.background_jadwal = background_jadwal;
    }


//
}
