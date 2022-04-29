package com.bridgelabz;

import java.util.Map;

public class Attendant {
    Owner owner = new Owner();

    public int parkThevehicle (Map<Integer, Vehicle> parkingLotMap) {
        return owner.getKeyToPark(parkingLotMap);

    }
}


