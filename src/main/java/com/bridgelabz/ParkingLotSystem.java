package com.bridgelabz;
public class ParkingLotSystem {
    Vehicle vehicle = null;

    public Owner owner = new Owner();
    public SecurityPersonnel securityPersonnel = new SecurityPersonnel();

    /**
     * Method : checking the driver can park the vehicle or not
     *
     * @throws ParkingLotException if lot is full throw exception , checking empty lot for parking vehicle
     */

    public void carPark(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicle != null) throw new ParkingLotException("!parking lot is full");
        this.vehicle = vehicle;
        if (this.vehicle != null) {
            String message = "Parking lot is full!!";
            owner.update(message);
            securityPersonnel.update(message);
        }
    }

    /**
     * Method: checking the vehicle is Present or not if Present return true,
     *
     * @throws ParkingLotException if parking lot is empty
     */

    public void carUnPark(Vehicle vehicle) throws ParkingLotException {

        if (this.vehicle == null) throw new ParkingLotException("parking lot is empty");
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return;
        }
        throw new ParkingLotException("Ask for correct vehicle");

    }


    /**
     * @return if vehicle is parked return true else return false
     */

    public boolean isVehiclePark(Vehicle vehicle) {
        if (this.vehicle.equals(vehicle)) return true;
        return false;
    }

    /**
     * @return if vehicle is UnPark return true else return false
     */

    public boolean isVehicleUnPark(Vehicle vehicle) {
        if (this.vehicle == null) {
            return true;
        }
        return false;
    }
}




