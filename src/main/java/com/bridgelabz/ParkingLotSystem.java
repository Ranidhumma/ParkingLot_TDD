package com.bridgelabz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem {
    Attendant attendant = new Attendant();
    private static final int MAX_PARKING_CAPACITY = 10;
    private Map<Integer, Vehicle> parkingLotMap = new LinkedHashMap<>();
    private Map<Integer, Vehicle> parkingLotMap1 = new LinkedHashMap<>();
    private Map<Integer, Vehicle> parkingLotMap2 = new LinkedHashMap<>();
    public Vehicle vehicle;
    List<ParkingLotObserver> observers;
    private LocalDateTime time;

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        for (int i = 1; i <= MAX_PARKING_CAPACITY; i++) {
            parkingLotMap1.put(i, null);
        }
        for (int i = 1; i <= MAX_PARKING_CAPACITY; i++) {
            parkingLotMap2.put(i, null);
        }
    }

    /**
     * Method : checking the driver can park the vehicle
     *
     * @throws ParkingLotException if lot is full, checking empty lot for parking vehicle
     */
    public void carPark(Vehicle vehicle, DriverType driverType, CarType carType) throws ParkingLotException {
        if (carType == CarType.SMALL) parkingLotMap = parkingLotMap1;
        if (carType == CarType.LARGE) parkingLotMap = parkingLotMap2;
        if (this.parkingLotMap.containsValue(vehicle))
            throw new ParkingLotException("vehicle is already there");

        if (this.parkingLotMap.size() == MAX_PARKING_CAPACITY && !this.parkingLotMap.containsValue(null))
            throw new ParkingLotException("parking Lot is Full");

        if (this.parkingLotMap.containsValue(null)) {
            int key = attendant.parkTheVehicle(this.parkingLotMap, driverType);
            this.parkingLotMap.put(key, vehicle);
            if (this.parkingLotMap.size() > MAX_PARKING_CAPACITY) {
                this.parkingLotMap.put(key, null);
                throw new ParkingLotException("parkingLot size is outOfBound");
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            setParkedTime(localDateTime);
        }
        if (this.parkingLotMap.size() == MAX_PARKING_CAPACITY && !this.parkingLotMap.containsValue(null)) {
            for (ParkingLotObserver observer : observers) {
                if (parkingLotMap.equals(parkingLotMap1))
                    observer.update("ParkingLot 1 is Full");
                else if (parkingLotMap.equals(parkingLotMap2))
                    observer.update("ParkingLot 2 is Full");
            }
        }
    }


    private void setParkedTime(LocalDateTime time) {
        this.time = time;
    }


    /*
     * Method: unParking vehicles
     *
     * @throws ParkingLotException if parking lot is empty and  asked for unPark incorrect vehicle
     */
    public void carUnPark(Vehicle vehicle) throws ParkingLotException {
        if (parkingLotMap1.containsValue(vehicle))
            parkingLotMap = parkingLotMap1;
        if (parkingLotMap2.containsValue(vehicle))
            parkingLotMap = parkingLotMap2;
        Integer key = 0;
        int nullCount = 0;
        for (Map.Entry map : parkingLotMap.entrySet()) {
            if (map.getValue() == null)
                nullCount++;
        }
        if (nullCount == MAX_PARKING_CAPACITY) throw new ParkingLotException("parking lot is empty");
        //if (this.parkingLotMap.isEmpty()) throw new ParkingLotException("parking lot is empty");
        if (this.parkingLotMap.containsValue(vehicle)) {
            for (Map.Entry map : parkingLotMap.entrySet()) {
                if (map.getValue() == vehicle) {
                    key = (Integer) map.getKey();
                }
            }
            // this.parkingLotMap.remove(key);
            this.parkingLotMap.put(key, null);
            if (this.parkingLotMap.containsValue(vehicle)) {
                for (ParkingLotObserver observer : observers) {
                    observer.update("Parking lot has space");
                }

                if (parkingLotMap2.containsValue(null))
                    for (ParkingLotObserver observer : observers) {
                        observer.update("Parkinglot2 has space");
                    }
                if (parkingLotMap1.containsValue(null) && parkingLotMap2.containsValue(null))
                    for (ParkingLotObserver observer : observers) {
                        observer.update("Both Parkinglot has space");
                    }
                return;
            }
            throw new ParkingLotException("Ask for correct vehicle");
        }

    }


    public int getVehicleLocation(Vehicle vehicle) {
        return getVehicleLotNumber(vehicle);
    }

    public int getVehicleLotNumber(Vehicle vehicle) {
        for (Map.Entry map : parkingLotMap.entrySet()) {
            if (map.getValue() == vehicle)
                return (int) map.getKey();
        }
        return 0;

    }

    public void registerObservers(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /*
     * @return true if vehicle is parked else return false
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

    public LocalDateTime getParkedTime() {
        return this.time;
    }
}












