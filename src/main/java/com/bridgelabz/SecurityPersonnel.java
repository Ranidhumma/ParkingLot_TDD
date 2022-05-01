package com.bridgelabz;

public class SecurityPersonnel implements ParkingLotObserver {
    public static String status;

    /*updating Status (message) to SecurityPersonnel*/
    public void update(String message) {
        status = message;
    }


    public String getStatus() {

        return status;
    }
}

