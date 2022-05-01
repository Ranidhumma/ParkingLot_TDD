package com.bridgelabz;

import java.util.Map;

public class Attendant {
    Owner owner = new Owner();

    /**
     * Method : Parking the vehicle
     * @return key to park vehicle
     */
    public int parkTheVehicle(Map<Integer, Vehicle> parkingLotMap) {
        return owner.getKeyToPark(parkingLotMap);

    }
}


