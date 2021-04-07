package com.example.ess;

public class Gwangjuev {
    private int id;
    private String name;
    private String address;
    private String gu;
    private String dong1;
    private String dong2;
    private String latitude;
    private String longitude;
    private String facility;
    private String now;


    public Gwangjuev(int id, String name, String address, String gu, String dong1, String dong2, String latitude, String longitude, String facility, String now){
        this.id = id;
        this.name = name;
        this.address = address;
        this.gu = gu;
        this.dong1 = dong1;
        this.dong2 = dong2;
        this.latitude = latitude;
        this.longitude = longitude;
        this.facility = facility;
        this.now = now;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getDong1() {
        return dong1;
    }

    public void setDong1(String dong1) {
        this.dong1 = dong1;
    }

    public String getDong2() {
        return dong2;
    }

    public void setDong2(String dong2) {
        this.dong2 = dong2;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }
}
