package com.bridgelabz;

import java.util.Map;

public class Owner implements ParkingLotObserver {
    private static String status;
    private int lotNumber;
    private int key;
    private Object vehicle;

    /**
     * @param message -updating Status (message) to owner
     */
    public void update(String message) {

        status = message;
    }

    /*Method: updated message*/
    public String getStatus() {
        return status;
    }

    public int getKeyToPark(Map<Integer, Vehicle> parkingLotMap) {
        for (Map.Entry map : parkingLotMap.entrySet()) {
            if (map.getValue() == null) {
                this.key = (int) map.getKey();
                break;
            }
        }
        return this.key;
    }
}
