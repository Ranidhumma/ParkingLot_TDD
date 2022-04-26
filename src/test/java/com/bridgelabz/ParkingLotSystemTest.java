package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    /*Uc1: Test case for checking the vehicle is parked  it returns true*/
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("Car1");
        boolean isParked = parkingLotSystem.carPark(vehicle);
        Assert.assertTrue(isParked);

    }

    /*Uc2:Test case for unPark vehicle by calling method unpark, before unPark vehicle, we are parking vehicle*/
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("Car1");
        parkingLotSystem.carPark(vehicle);
        boolean isUnParked = parkingLotSystem.carUnPark(vehicle);
        Assert.assertTrue(isUnParked);
    }

    /*Uc2:Test case for one vehicle parked and calling method unPark for different vehicle return false*/
    @Test
    public void givenOneVehicleParked_AndWhenDifferentVehicleUnPark_ShouldReturnFalse() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("Car1");
        Vehicle vehicle1 = new Vehicle("Car2");
        parkingLotSystem.carPark(vehicle);
        boolean isUnParked = parkingLotSystem.carUnPark(vehicle1);
        Assert.assertFalse(isUnParked);
    }

    /*Uc2:Test case for no vehicle parked and calling method unPark return false*/
    @Test
    public void givenVehicle_UnParked_WhenNotParkedBefore_ShouldReturnFalse() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("Car1");
        boolean isUnParked = parkingLotSystem.carUnPark(vehicle);
        Assert.assertFalse(isUnParked);
    }
}

