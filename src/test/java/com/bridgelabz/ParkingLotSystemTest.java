package com.bridgelabz;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    private ParkingLotSystem parkingLotSystem;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
    }

    /*Uc1: Test case for checking the vehicle is parked  it returns true*/
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        try {
            Vehicle vehicle = new Vehicle("Car1");
            parkingLotSystem.carPark(vehicle);
            boolean isParked = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /*Uc1: Test case for checking the vehicle is Already parked  when try to park another vehicle it returns ParkingLotexception*/
    @Test
    public void givenVehicle_WhenAlreadyParked_WhenTryToParkAnotherVehicle_ShouldReturn_throwException() {
        try {
            Vehicle vehicle = new Vehicle("Car1");
            Vehicle vehicle1 = new Vehicle("Car2");
            parkingLotSystem.carPark(vehicle);
            parkingLotSystem.carPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking lot is full", e.getMessage());
            e.printStackTrace();
        }

    }

    /*Uc2:Test case for unPark vehicle by calling method unPark, before unPark vehicle, we are parking vehicle return true*/
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            Vehicle vehicle = new Vehicle("Car1");
            parkingLotSystem.carPark(vehicle);
            parkingLotSystem.carUnPark(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnPark(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /*Uc2:Test case for one vehicle parked and calling method unPark for different vehicle return ParkingLotException*/
    @Test
    public void givenOneVehicleParked_AndWhenDifferentVehicleUnPark_ShouldthrowException() {
        try {
            Vehicle vehicle = new Vehicle("Car1");
            Vehicle vehicle1 = new Vehicle("Car2");
            parkingLotSystem.carPark(vehicle);
            parkingLotSystem.carUnPark(vehicle1);
        } catch (ParkingLotException e) {
            e.printStackTrace();
            Assert.assertEquals("Ask for correct vehicle", e.getMessage());
        }
    }

    /*Uc2:Test case for no vehicle parked and calling method unPark return ParkingLotException*/
    @Test
    public void givenVehicle_UnParked_WhenNotParkedBefore_Should_throwException() {
        try {
            Vehicle vehicle = new Vehicle("Car1");
            parkingLotSystem.carUnPark(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking lot is empty", e.getMessage());
            e.printStackTrace();
        }
    }
}