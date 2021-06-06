package model;

public class BTMonthly extends BinaryTree{

    private String ownerName;
    private int spotNumber;
    private String lastTimePaid;
    private String dayToPay;

    public BTMonthly(){
        ownerName = "";
        spotNumber = 0;
        lastTimePaid = "";
        dayToPay = "";
    }

    @Override
    public void setBtVehicle(Vehicle newVehicle) {
        btVehicle = newVehicle;
        ownerName = btVehicle.getOwner().getName();
        spotNumber = btVehicle.getSpot();
        dayToPay = btVehicle.getSupposedExitDateString();
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getLastTimePaid() {
        return lastTimePaid;
    }

    public void setLastTimePaid(String lastTimePaid) {
        this.lastTimePaid = lastTimePaid;
    }

    public String getDayToPay() {
        return dayToPay;
    }

    public void setDayToPay(String dayToPay) {
        this.dayToPay = dayToPay;
    }
}
