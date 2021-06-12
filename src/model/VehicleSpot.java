package model;

import java.io.Serializable;

/**
 * A class defining a vehicle (not a bike) spot. <br>
 */
@SuppressWarnings("unused")
public class VehicleSpot extends Spot implements Serializable {

    private static final long serialVersionUID = 2L;
    private int number;
    private Vehicle spotVehicle;

    /**
     * This is the main constructor of the class. <br>
     */
    public VehicleSpot(int number) {
        super();
        this.number = number;
    }

    /**
     * Give the vehicle information in that spot <br>
     * <b> pre: </b><br>
     * <b> post: </b>Vehicle information is given <br>
     */
    @Override
    public String getInformation() {
        String info = "";
        if (spotVehicle != null) {
            info += "Tipo de vehiculo: " + spotVehicle.getType() + "\n";
            info += "Modelo: " + spotVehicle.getModel() + "\n";
            info += "Placa: " + spotVehicle.getLicensePlate() + "\n";
            info += "Color: " + spotVehicle.getColor() + "\n";
            info += "Propietario: " + spotVehicle.getOwner().getName() + "\n";
        } else {
            info += "Aun no hay informacion de un vehiculo en este puesto\n";
        }

        return info;
    }

    /**
     * @return The current position. <br>
     */
    @Override
    public String getActualPosition() {
        return number + "";
    }

    /**
     * @return The number of the slot. <br>
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number The number of the slot. <br>
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return The serial number. <br>
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return The spot's vehicle. <br>
     */
    public Vehicle getSpotVehicle() {
        return spotVehicle;
    }

    /**
     * @param spotVehicle The spot's vehicle. <br>
     */
    public void setSpotVehicle(Vehicle spotVehicle) {
        this.spotVehicle = spotVehicle;
    }
}
