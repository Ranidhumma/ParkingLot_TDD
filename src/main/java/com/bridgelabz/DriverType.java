package com.bridgelabz;

public enum DriverType {

    NORMAL(5),HANDICAP(0);

    public final int startingLot;

    DriverType(int value) {
        this.startingLot=value;
    }



}
