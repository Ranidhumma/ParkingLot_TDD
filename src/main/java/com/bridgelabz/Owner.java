package com.bridgelabz;

import java.util.Map;

public class Owner implements ParkingLotObserver {
    private static String status;
    private int lotNumber;
    private int key;

    /**
     * @param message -updating Status (message) to owner
     */
    public void update(String message) {

        status = message;
    }

    /*updated message*/
    public String getStatus() {
        return status;
    }

    public int getKeyToPark(Map<Integer, Vehicle> parkingLotMap) {
        lotNumber = 1;
        if (parkingLotMap.isEmpty()) this.key = lotNumber;
        for (Map.Entry map : parkingLotMap.entrySet()) {
            lotNumber++;
        }
        this.key = lotNumber;
        return this.key;
    }

}
