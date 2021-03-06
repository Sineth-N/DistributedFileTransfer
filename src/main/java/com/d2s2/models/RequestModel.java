package com.d2s2.models;

public class RequestModel {
    String ip;
    String port;
    String userName;

    public RequestModel(String ip, String port, String userName) {
        this.ip = ip;
        this.port = port;
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
