package com.bridgelabz;

public class ParkingLotSystem {
    Vehicle vehicle = null;

    /*Method : checking the driver can park or not the vehicle
     checking empty slot for parking vehicle ,
     @return if slot is full return false , if slot is empty return true
    */

    public boolean carPark(Vehicle vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    /*Method: checking the vehicle is present or not if Present return true,
     if not present return false
     */
    public boolean carUnPark(Vehicle vehicle) {

        if (this.vehicle == null)
            return false;
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
}
}

