package com.example.filmai.model;

public class UserSingleton {
    private static UserSingleton instance;

    private String userName;

    private boolean isReserved;

    private UserSingleton() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getIsReserved() {return  isReserved;}

    public void setIsReserved(boolean isReserved) {this.isReserved = isReserved;}


    public static UserSingleton getInstance(){
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }
}
