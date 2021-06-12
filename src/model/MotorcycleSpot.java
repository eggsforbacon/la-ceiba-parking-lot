package model;

import java.io.Serializable;

/**
 * A class defining a bike spot. <br>
 */
@SuppressWarnings("unused")
public class MotorcycleSpot extends Spot implements Serializable {

    private static final long serialVersionUID = 2L;
    private int number1;
    private int number2;
    private Vehicle spotVehicle1;
    private Vehicle spotVehicle2;

    /**
     * This is the main constructor of the class. <br>
     */
    public MotorcycleSpot(int number1, int number2) {
        super();
        this.number1 = number1;
        this.number2 = number2;
    }

    /**
     * @return The spot's vehicle's information as a string. <br>
     */
    @Override
    public String getInformation() {
        String info = "";
        info += "Motocicleta 1:" + "\n";
        if (spotVehicle1 == null) {
            info += "Aun no hay informacion de algun vehiculo en este puesto" + "\n";
        } else {
            info += "Tipo de vehiculo: " + spotVehicle1.getType() + "\n";
            info += "Modelo: " + spotVehicle1.getModel() + "\n";
            info += "Placa: " + spotVehicle1.getLicensePlate() + "\n";
            info += "Color: " + spotVehicle1.getColor() + "\n";
            info += "Propietario: " + spotVehicle1.getOwner().getName() + "\n" + "\n";
        }
        info += "Motocicleta 2:" + "\n";
        if (spotVehicle2 == null) {
            info += "Aun no hay informacion de algun vehiculo en este puesto" + "\n";
        } else {
            info += "Tipo de vehiculo: " + spotVehicle2.getType() + "\n";
            info += "Modelo: " + spotVehicle2.getModel() + "\n";
            info += "Placa: " + spotVehicle2.getLicensePlate() + "\n";
            info += "Color: " + spotVehicle2.getColor() + "\n";
            info += "Propietario: " + spotVehicle2.getOwner().getName() + "\n";
        }
        return info;
    }

    /**
     * @return The current position. <br>
     */
    @Override
    public String getActualPosition() {
        return number1 + " " + number2;
    }

    /**
     * @return The first number of the slot. <br>
     */
    public int getNumber1() {
        return number1;
    }

    /**
     * @param number1 The first number of the slot. <br>
     */
    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    /**
     * @return The second number of the slot. <br>
     */
    public int getNumber2() {
        return number2;
    }

    /**
     * @param number2 The second number of the slot. <br>
     */
    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    /**
     * @return The serial number. <br>
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return The spot of the first vehicle. <br>
     */
    public Vehicle getSpotVehicle1() {
        return spotVehicle1;
    }

    /**
     * @param spotVehicle1 The spot of the first vehicle. <br>
     */
    public void setSpotVehicle1(Vehicle spotVehicle1) {
        this.spotVehicle1 = spotVehicle1;
    }

    /**
     * @return The spot of the second vehicle. <br>
     */
    public Vehicle getSpotVehicle2() {
        return spotVehicle2;
    }

    /**
     * @param spotVehicle2 The spot of the second vehicle. <br>
     */
    public void setSpotVehicle2(Vehicle spotVehicle2) {
        this.spotVehicle2 = spotVehicle2;
    }
}
