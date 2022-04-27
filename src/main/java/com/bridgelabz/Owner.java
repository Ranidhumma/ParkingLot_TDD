package com.bridgelabz;

public class Owner {
       private static String status;
    public void update(String message) {

        status = message;
    }
    public String getStatus() {
        return status;
    }
}
