package com.ncu.healthy.bean;

public class UserData {
    private String Time;
    private String EcgData;
    private String DataTime;
    private String UserTemp;
    private String UserName;
    private String UserBedId;
    private String UserSpo2;
    private String userId;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getEcgData() {
        return EcgData;
    }

    public void setEcgData(String ecgData) {
        EcgData = ecgData;
    }

    public String getDataTime() {
        return DataTime;
    }

    public void setDataTime(String dataTime) {
        DataTime = dataTime;
    }

    public String getUserTemp() {
        return UserTemp;
    }

    public void setUserTemp(String userTemp) {
        UserTemp = userTemp;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserBedId() {
        return UserBedId;
    }

    public void setUserBedId(String userBedId) {
        UserBedId = userBedId;
    }

    public String getUserSpo2() {
        return UserSpo2;
    }

    public void setUserSpo2(String userSpo2) {
        UserSpo2 = userSpo2;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "Time='" + Time + '\'' +
                ", EcgData='" + EcgData + '\'' +
                ", DataTime='" + DataTime + '\'' +
                ", UserTemp='" + UserTemp + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserBedId='" + UserBedId + '\'' +
                ", UserSpo2='" + UserSpo2 + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}