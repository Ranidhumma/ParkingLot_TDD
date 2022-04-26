package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
      /*Test case for checking the vehicle is parked return true*/
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("Car1");
        boolean isParked = parkingLotSystem.carPark(vehicle);
        Assert.assertTrue(isParked);
    }
    /*Test case for unPark vehicle , Here , before unPark vehicle, we are parking vehicle*/
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("Car2");
        parkingLotSystem.carPark(vehicle);
        boolean isUnParked = parkingLotSystem.carUnPark(vehicle);
        Assert.assertTrue(isUnParked);

    }
}

