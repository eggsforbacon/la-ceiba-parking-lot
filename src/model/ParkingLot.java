package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ParkingLot implements Serializable {
    private ArrayList<Client> clientsPL;
    private ArrayList<Vehicle> vehiclesPL;

    public ParkingLot(){
        clientsPL = new ArrayList<>();
        vehiclesPL = new ArrayList<>();
    }
}
