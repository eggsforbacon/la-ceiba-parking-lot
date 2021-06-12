package model;

import java.io.Serializable;

/**
 * A binary tree for monthly vehicles. <br>
 */
@SuppressWarnings("unused")
public class BTMonthly extends BinaryTree implements Serializable {

    private static final long serialVersionUID = 2L;
    private String ownerName;
    private int spotNumber;
    private String lastTimePaid;
    private String dayToPay;

    /**
     * This is the main constructor of the class. <br>
     */
    public BTMonthly() {
        ownerName = "";
        spotNumber = 0;
        lastTimePaid = "";
        dayToPay = "";
    }

    /**
     * @param newVehicle The vehicle to be set to. <br>
     */
    @Override
    public void setBtVehicle(Vehicle newVehicle) {
        btVehicle = newVehicle;
        ownerName = btVehicle.getOwner().getName();
        spotNumber = btVehicle.getSpot();
        dayToPay = btVehicle.getSupposedExitDateString();
    }

    /**
     * @return The owner's name. <br>
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName The owner's name. <br>
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return The spot number. <br>
     */
    public int getSpotNumber() {
        return spotNumber;
    }

    /**
     * @param spotNumber The spot number. <br>
     */
    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    /**
     * @return The last time the client payed as a String. <br>
     */
    public String getLastTimePaid() {
        return lastTimePaid;
    }

    /**
     * @param lastTimePaid The last time the client payed as a String. <br>
     */
    public void setLastTimePaid(String lastTimePaid) {
        this.lastTimePaid = lastTimePaid;
    }

    /**
     * @return The payment due date as a String. <br>
     */
    public String getDayToPay() {
        return dayToPay;
    }

    /**
     * @param dayToPay The payment due date as a String. <br>
     */
    public void setDayToPay(String dayToPay) {
        this.dayToPay = dayToPay;
    }
}
