package com.bridgelabz;

public class SecurityPersonnel{
    private static String status;

    public String getStatus() {
        return status;
    }
    public void update(String message) {
        status = message;
    }

}


