package com.ncu.healthy.bean;

public class ResultInfo {
    private String errorInfo;
    private  boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "errorInfo='" + errorInfo + '\'' +
                ", flag=" + flag +
                '}';
    }
}
