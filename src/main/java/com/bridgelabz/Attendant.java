package com.bridgelabz;

import java.util.Map;

public class Attendant {
    Owner owner = new Owner();
    private int key;

    /**
     * Method : Parking the vehicle
     * @return key to park vehicle
     */
    public int parkTheVehicle(Map<Integer, Vehicle> parkingLotMap, DriverType driverType) {
  //      for (Map.Entry map : parkingLotMap.entrySet()) {
//            if(map.getValue()==null) {
//                this.key = (int) map.getKey();//finds the key where the lot is empty
//                break;//breaks after finding evenly parked key
//            }
//        }
            if (driverType == DriverType.NORMAL) {
                int normalKey = 6;
                for (normalKey = 6; normalKey <= parkingLotMap.size(); normalKey++) {
                    if (parkingLotMap.get(normalKey) == null)
                        return normalKey;
                }
            }
            if (driverType == DriverType.HANDICAP)
                for (int key = 1; key <= parkingLotMap.size(); key++) {
                    if (parkingLotMap.get(key) == null)
                        return key;
                }

            if (driverType == DriverType.NORMAL) {
                int normalKey = 1;
                for (normalKey = 1; normalKey <= parkingLotMap.size(); normalKey++) {
                    if (parkingLotMap.get(normalKey) == null)
                        return normalKey;
                }
            }
            return this.key;

        }

    }


