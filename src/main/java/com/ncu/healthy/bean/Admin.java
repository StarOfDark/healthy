package com.ncu.healthy.bean;

public class Admin {
    private String adminID;
    private String adminName;
    private String adminPwd;
    private int adminAge;
    private String adminTElNum;
    private String adminGender;
    private String adminSubDataTime;
    private String adminNum;

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public int getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(int adminAge) {
        this.adminAge = adminAge;
    }

    public String getAdminTElNum() {
        return adminTElNum;
    }

    public void setAdminTElNum(String adminTElNum) {
        this.adminTElNum = adminTElNum;
    }

    public String getAdminGender() {
        return adminGender;
    }

    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }

    public String getAdminSubDataTime() {
        return adminSubDataTime;
    }

    public void setAdminSubDataTime(String adminSubDataTime) {
        this.adminSubDataTime = adminSubDataTime;
    }

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminID='" + adminID + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminAge=" + adminAge +
                ", adminTElNum='" + adminTElNum + '\'' +
                ", adminGender='" + adminGender + '\'' +
                ", adminSubDataTime='" + adminSubDataTime + '\'' +
                ", adminNum='" + adminNum + '\'' +
                '}';
    }
}
