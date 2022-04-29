package com.bridgelabz;

public class SecurityPersonnel implements ParkingLotObserver {
    public static String status;


    public void update(String message) {
        status = message ;
    }

    public String getStatus() {

        return status;
    }
}

