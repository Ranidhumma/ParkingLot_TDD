package com.bridgelabz;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    private ParkingLotSystem parkingLotSystem;
    private String status;
    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
    }

    /*Uc1: Test case for checking the vehicle is parked  it returns true*/
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        try {
            Vehicle vehicle = new Vehicle(1 , "Car1");
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
            Vehicle vehicle = new Vehicle(1, "Car1");
            Vehicle vehicle1 = new Vehicle( 2 ,"Car2");
            parkingLotSystem.carPark(vehicle);
            parkingLotSystem.carPark(vehicle1);
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
            Vehicle vehicle = new Vehicle(1, "Car1");
            Vehicle vehicle1 = new Vehicle(1, "Car2");
            parkingLotSystem.carPark(vehicle);
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
            Vehicle vehicle = new Vehicle(1,"Car1");
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
            Vehicle vehicle = new Vehicle(1,"Car1");
            parkingLotSystem.carPark(vehicle);
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
            SecurityPersonnel securityPersonnel = new SecurityPersonnel();
            parkingLotSystem.registerObservers(securityPersonnel);
            parkingLotSystem.carPark(new Vehicle(1,"car1"));
            parkingLotSystem.carPark(new Vehicle(2,"car1"));
            parkingLotSystem.carPark(new Vehicle(3,"car1"));
            parkingLotSystem.carPark(new Vehicle(4,"car1"));
            String status = securityPersonnel.getStatus();
            Assert.assertEquals("Parking Lot is Full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC5 : Test to check updating parkingLot empty message to Security Personnel
     */
    @Test
    public void givenAVehicle_WhenParkingLotHasSpaceAgain_ShouldGiveMessageToOwner() {
        try {
            Vehicle vehicle = new Vehicle(1,"Car1");
            Owner owner = new Owner();
            parkingLotSystem.registerObservers(owner);
            parkingLotSystem.carPark(vehicle);
            parkingLotSystem.carUnPark(vehicle);
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot has space", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
}

    /**
     * UC6 Test case : owner gives the parking lot to Attedant to park the vehicle
     *
     */
    @Test
    public void givenAttendant_WhenOwnerGivesTheSlotToParkTheVehicle_ShouldPark() {
        Attendant attendant = new Attendant();
        try {
            Owner owner = new Owner();
            parkingLotSystem.registerObservers(owner);
            Vehicle vehicle1 = new Vehicle(1,"car1");
            Vehicle vehicle2 = new Vehicle(2,"car2");
            parkingLotSystem.carPark(vehicle1);
            parkingLotSystem.carPark(vehicle2);
            int vehicleLotNumber = parkingLotSystem.getVehicleLotNumber(vehicle2);
            Assert.assertEquals(2,vehicleLotNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
    /**
     * UC7 Test case for driver is finding the location of given vehicle (car)
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicle_WhenFindVehicle_ShouldReturnKey() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle(1, "car1");
        Vehicle vehicle2 = new Vehicle(2, "car2");
        parkingLotSystem.carPark(vehicle1);
        parkingLotSystem.carPark(vehicle2);

        int key = parkingLotSystem.getVehicleLocation(vehicle2);
        Assert.assertEquals(2, key);
    }
}




