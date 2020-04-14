package com.ncu.healthy.bean;

public class User {
    private String id;
    private int count;
    private String userName;
    private String email;
    private String password;
    private String userClass;
    private int age;
    private String sex;
    private String userDoctorDiagnosis;
    private String bedInfoBedId;
    private String userSubDateTime;
    private String userDoctorAdvice;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserDoctorDiagnosis() {
        return userDoctorDiagnosis;
    }

    public void setUserDoctorDiagnosis(String userDoctorDiagnosis) {
        this.userDoctorDiagnosis = userDoctorDiagnosis;
    }

    public String getBedInfoBedId() {
        return bedInfoBedId;
    }

    public void setBedInfoBedId(String bedInfoBedId) {
        this.bedInfoBedId = bedInfoBedId;
    }

    public String getUserSubDateTime() {
        return userSubDateTime;
    }

    public void setUserSubDateTime(String userSubDateTime) {
        this.userSubDateTime = userSubDateTime;
    }

    public String getUserDoctorAdvice() {
        return userDoctorAdvice;
    }

    public void setUserDoctorAdvice(String userDoctorAdvice) {
        this.userDoctorAdvice = userDoctorAdvice;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", count='" + count + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userClass='" + userClass + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", userDoctorDiagnosis='" + userDoctorDiagnosis + '\'' +
                ", bedInfoBedId='" + bedInfoBedId + '\'' +
                ", userSubDateTime='" + userSubDateTime + '\'' +
                ", userDoctorAdvice='" + userDoctorAdvice + '\'' +
                '}';
    }
}
