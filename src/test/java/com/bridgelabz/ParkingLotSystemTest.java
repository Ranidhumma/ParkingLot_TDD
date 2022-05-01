package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class ParkingLotSystemTest {
    private ParkingLotSystem parkingLotSystem;
    private String status;
    private ParkingLotSystem parkingLotMap;


    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
    }

    /*Uc1: Test case for checking the vehicle is parked , it returns true*/
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        try {
            Vehicle vehicle = new Vehicle(1, "Car1");
            parkingLotSystem.carPark(vehicle,DriverType.NORMAL);
            boolean isParked = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /*UC1: Test case for checking the vehicle is Already parked  when try to park another vehicle it returns ParkingLotexception*/
    @Test
    public void givenVehicle_WhenAlreadyParked_WhenTryToParkAnotherVehicle_ShouldReturn_throwException() {
        try {
            Vehicle vehicle = new Vehicle(1, "Car1");
            Vehicle vehicle1 = new Vehicle(2, "Car2");
            parkingLotSystem.carPark(vehicle,DriverType.NORMAL);
            parkingLotSystem.carPark(vehicle1,DriverType.NORMAL);
        } catch (ParkingLotException e) {
            Assert.assertEquals("!parking lot is full", e.getMessage());
            e.printStackTrace();
        }
    }

    /*Uc2:Test case for unPark vehicle by calling method unPark, before unPark vehicle, we are parking vehicle return true*/
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            Vehicle vehicle = new Vehicle(1, "Car1");
            parkingLotSystem.carPark(vehicle,DriverType.NORMAL);
            parkingLotSystem.carUnPark(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnPark(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /*Uc2:Test case for one vehicle parked and calling method unPark of different vehicle return ParkingLotException*/
    @Test
    public void givenOneVehicleParked_AndWhenDifferentVehicleUnPark_ShouldthrowException() {
        try {
            Vehicle vehicle = new Vehicle(1, "Car1");
            Vehicle vehicle1 = new Vehicle(1, "Car2");
            parkingLotSystem.carPark(vehicle,DriverType.NORMAL);
            parkingLotSystem.carUnPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Ask for correct vehicle", e.getMessage());
            e.printStackTrace();
        }
    }

    /*Uc2:Test case for no vehicle parked and calling method unPark return ParkingLotException*/
    @Test
    public void givenVehicle_UnParked_WhenNotParkedBefore_Should_throwException() {
        try {
            Vehicle vehicle = new Vehicle(1, "Car1");
            parkingLotSystem.carUnPark(vehicle);
            Assert.assertEquals("Parking lot is empty", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC3:Test case to check updating parkingLot Full message to owner
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToOwner() {
        try {
            Vehicle vehicle = new Vehicle(1, "Car1");
            parkingLotSystem.carPark(vehicle,DriverType.NORMAL);
            Owner owner = new Owner();
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot has space", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC4 : Test to check if lot is full update parking Lot Full message to Security Personnel
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToSecurityPersonnel() {
        try {
            Vehicle vehicle1 = new Vehicle(1, "car1");
            Vehicle vehicle2 = new Vehicle(2, "car2");
            Vehicle vehicle3 = new Vehicle(1, "car1");
            Vehicle vehicle4 = new Vehicle(2, "car2");
            SecurityPersonnel securityPersonnel = new SecurityPersonnel();
            parkingLotSystem.registerObservers(securityPersonnel);
            parkingLotSystem.carPark(vehicle1,DriverType.NORMAL);
            parkingLotSystem.carPark(vehicle2,DriverType.NORMAL);
            parkingLotSystem.carPark(vehicle3,DriverType.NORMAL);
            parkingLotSystem.carPark(vehicle4,DriverType.NORMAL);
            String status = securityPersonnel.getStatus();
            Assert.assertEquals("Parking Lot is Full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC5 : Test to check updating parkingLot empty message to owner
     */
    @Test
    public void givenAVehicle_WhenParkingLotHasSpaceAgain_ShouldGiveMessageToOwner() {
        try {
            Vehicle vehicle = new Vehicle(1, "Car1");
            Owner owner = new Owner();
            parkingLotSystem.registerObservers(owner);
            parkingLotSystem.carPark(vehicle,DriverType.NORMAL);
            parkingLotSystem.carUnPark(vehicle);
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot has space", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC6 Test case : owner gives the parking lot to Attedant to park the vehicle
     */
    @Test
    public void givenAttendant_WhenOwnerGivesTheSlotToParkTheVehicle_ShouldPark() {
        Attendant attendant = new Attendant();
        try {
            Owner owner = new Owner();
            parkingLotSystem.registerObservers(owner);
            Vehicle vehicle1 = new Vehicle(1, "car1");
            Vehicle vehicle2 = new Vehicle(2, "car2");
            parkingLotSystem.carPark(vehicle1,DriverType.NORMAL);
            parkingLotSystem.carPark(vehicle2,DriverType.NORMAL);
            int vehicleLotNumber = parkingLotSystem.getVehicleLotNumber(vehicle2);
            Assert.assertEquals(2, vehicleLotNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC7 Test case : driver is finding the location of Parked car (vehicle)
     */
    @Test
    public void givenVehicle_WhenFindVehicle_ShouldReturnKey() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle(1, "car1");
        Vehicle vehicle2 = new Vehicle(2, "car2");
        parkingLotSystem.carPark(vehicle1,DriverType.NORMAL);
        parkingLotSystem.carPark(vehicle2,DriverType.NORMAL);
        int key = parkingLotSystem.getVehicleLocation(vehicle2);
        Assert.assertEquals(2, key);
    }

    /**
     * UC8 Test case for : parking lot owner wants to know the time when the car was parked
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTimeOfParking() {
        try {
            Vehicle vehicle1 = new Vehicle(1, "car1");
            parkingLotSystem.carPark(vehicle1,DriverType.NORMAL);
            LocalDateTime localDateTime = LocalDateTime.now();
            Assert.assertEquals(localDateTime, parkingLotSystem.getParkedTime());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC9 : Test case for attendant is taking key from owner to park the vehicles in Evenly distrubuted manner (equally distrubuted)
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldParkEvenly() {
        Vehicle vehicle1 = new Vehicle(1, "car1");
        Vehicle vehicle2 = new Vehicle(2, "car2");
        Vehicle vehicle3 = new Vehicle(3, "car3");
        Vehicle vehicle4 = new Vehicle(4, "car4");
        try {
            parkingLotSystem.carPark(vehicle1, DriverType.NORMAL);
            parkingLotSystem.carPark(vehicle2,DriverType.NORMAL);
            parkingLotSystem.carPark(vehicle3,DriverType.NORMAL);
            parkingLotSystem.carUnPark(vehicle2);//2 empty
            parkingLotSystem.carPark(vehicle4,DriverType.NORMAL);
            Assert.assertEquals(2, parkingLotSystem.getVehicleLocation(vehicle4));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC10 test case to for parking vehicles according to their Driver type
     * Normal and Handicap
     */
    @Test
    public void givenAVehicle_WhenDriverTypeGiven_ShouldParkAccordingly() {
        Vehicle v1 = new Vehicle(1, "audi");
        Vehicle v2 = new Vehicle(2, "brezza");
        Vehicle v3 = new Vehicle(3, "etios");

        try {
            parkingLotSystem.carPark(v1, DriverType.NORMAL);
            parkingLotSystem.carPark(v2, DriverType.HANDICAP);
            parkingLotSystem.carPark(v3, DriverType.HANDICAP);
            int key1 = parkingLotSystem.getVehicleLocation(v1);//6
            int key2 = parkingLotSystem.getVehicleLocation(v2);//1
            int key3 = parkingLotSystem.getVehicleLocation(v3);//2
            Assert.assertEquals(6, key1);
            Assert.assertEquals(1, key2);
            Assert.assertEquals(2, key3);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }


}









