package com.example.ess;

public class User {


         String id;
         String password;
         String name;
         String car;
        double allbattery;
        double nowbattery;
        double efficiency;
        String homeattitude;
        String homelongitude;
        String officeattitude;
        String officelongitude;

        public User(String id, String password, String name, String car, double allbattery, double nowbattery, double efficiency, String homeattitude, String homelongitude, String officeattitude, String officelongitude){
                this.id = id;
                this.password = password;
                this.name = name;
                this.car = car;
                this.allbattery = allbattery;
                this.nowbattery = nowbattery;
                this.efficiency = efficiency;
                this.homeattitude = homeattitude;
                this.homelongitude = homelongitude;
                this.officeattitude = officeattitude;
                this.officelongitude = officelongitude;

        }



        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCar() {
                return car;
        }

        public void setCar(String car) {
                this.car = car;
        }

        public double getAllbattery() {
                return allbattery;
        }

        public void setAllbattery(double allbattery) {
                this.allbattery = allbattery;
        }

        public double getNowbattery() {
                return nowbattery;
        }

        public void setNowbattery(double nowbattery) {
                this.nowbattery = nowbattery;
        }

        public double getEfficiency() {
                return efficiency;
        }

        public void setEfficiency(double efficiency) {
                this.efficiency = efficiency;
        }

        public String getHomeattitude() {
                return homeattitude;
        }

        public void setHomeattitude(String homeattitude) {
                this.homeattitude = homeattitude;
        }

        public String getHomelongitude() {
                return homelongitude;
        }

        public void setHomelongitude(String homelongitude) {
                this.homelongitude = homelongitude;
        }

        public String getOfficeattitude() {
                return officeattitude;
        }

        public void setOfficeattitude(String officeattitude) {
                this.officeattitude = officeattitude;
        }

        public String getOfficelongitude() {
                return officelongitude;
        }

        public void setOfficelongitude(String officelongitude) {
                this.officelongitude = officelongitude;
        }
}
