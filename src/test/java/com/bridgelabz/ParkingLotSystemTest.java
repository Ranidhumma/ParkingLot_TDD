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
}

