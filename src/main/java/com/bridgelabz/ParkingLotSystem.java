package com.bridgelabz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem {
    Attendant attendant = new Attendant();
    private static final int MAX_PARKING_CAPACITY = 2;
    private final Map<Integer, Vehicle> parkingLotMap;
    public Vehicle vehicle;
    List<ParkingLotObserver> observers;
//    private Object attendant;

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        parkingLotMap = new LinkedHashMap<>();
    }

    /**
     * Method : checking the driver can park the vehicle or not
     *
     * @throws ParkingLotException if lot is full, checking empty lot for parking vehicle
     */
    public void carPark(Vehicle vehicle) throws ParkingLotException {
        if (this.parkingLotMap.containsValue(vehicle)) throw new ParkingLotException("vehicle is already there");
        if (this.parkingLotMap.size() == MAX_PARKING_CAPACITY)
            throw new ParkingLotException("parking Lot is Full");

        if (parkingLotMap.size() < MAX_PARKING_CAPACITY) {
            int key = attendant.parkTheVehicle(parkingLotMap);
            this.parkingLotMap.put(key, vehicle);
        }

        if (this.parkingLotMap.size() < MAX_PARKING_CAPACITY) {
            String message = "Parking Lot is Full";
            for (ParkingLotObserver observer : observers) {
                observer.update(message);
            }
        }
    }

    /*
     * Method: checking the vehicle is Present or not if Present return true,
     *
     * @throws ParkingLotException if parking lot is empty
     */
    public void carUnPark(Vehicle vehicle) throws ParkingLotException {
        Integer key = 0;
        if (this.parkingLotMap.isEmpty()) throw new ParkingLotException("parking lot is empty");
        if (this.parkingLotMap.containsValue(vehicle)) {
            for (Map.Entry map : parkingLotMap.entrySet()) {
                if (map.getValue() == vehicle) {
                    key = (Integer) map.getKey();
                }
            }
            this.parkingLotMap.remove(key);
            if (this.parkingLotMap.size() < MAX_PARKING_CAPACITY) {
                for (ParkingLotObserver observer : observers) {
                    observer.update("Parking lot has space");
                }
            }
            return;
        }
        throw new ParkingLotException("Ask for correct vehicle");
    }

    /*
     * @return if vehicle is parked return true else return false
     */

    public boolean isVehiclePark(Vehicle vehicle) {
        if (this.parkingLotMap.containsValue(vehicle)) return true;
        return false;
    }
    /*
     * @return if vehicle is UnPark return true else return false
     */

    public boolean isVehicleUnPark(Vehicle vehicle) {
        if (!this.parkingLotMap.containsValue(vehicle)) return true;//checking for vehicle is unParked
        return false;
    }

    public void registerObservers(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public int getVehicleLotNumber(Vehicle vehicle) {
        for (Map.Entry map : parkingLotMap.entrySet()) {
            if (map.getValue() == vehicle)
                return (int) map.getKey();
        }
        return 0;
    }

    public int getVehicleLocation(Vehicle vehicle) {
        return getVehicleLotNumber(vehicle);
    }
}











