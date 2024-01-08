package com.cosmic.coroulette.dao;

public class User {
    private String sessionId;
    private String name;
    private boolean isReady;
    
    public User(String sessionId, String name, boolean isReady){
        this.sessionId = sessionId;
        this.name = name;
        this.isReady = isReady;
    }
    
    public User(String sessionId){
        this.sessionId = sessionId;
        this.name = "random";
        this.isReady = false;
    }

    public String getsessionId() {
        return sessionId;
    }
    public String getName() {
        return name;
    }
    public boolean isReady() {
        return isReady;
    }
    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }
    
}
