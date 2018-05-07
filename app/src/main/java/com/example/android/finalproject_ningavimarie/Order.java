package com.example.android.finalproject_ningavimarie;

import java.util.HashMap;

public class Order {

    public String userNameR;
    public HashMap<String, String> dataMap = new HashMap<String, String>();

    public HashMap<String, String> firebaseMap() {
        return dataMap;
    }

    public void setUserNameR(String userNameR) {
        this.userNameR = userNameR;
    }

    public String getUserNameR() {
        return userNameR;
    }
}
