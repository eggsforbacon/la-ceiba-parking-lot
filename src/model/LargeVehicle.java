package model;

public class LargeVehicle extends Vehicle {
    private final static int HOURVALUE = 3000;
    private final static int TWOHOURSVALUE = 4000;
    private final static int ADITIONAL = 1000;
    private final static int DAY = 7000;
    private final static int NIGHT = 10000;
    private final static int DAYANDNIGHT = 12000;
    private final static int MONTH = 60000;
    private final static int[] availableSpots = {21,22,23,24,25,26,27,28,29};

    public LargeVehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, String observations, int stayIndicator, int numberOfTime) {
        super(typeIndicator, model, licensePlate, color, owner, spot, observations, stayIndicator, numberOfTime);
    }

    @Override
    public double calculateValueToPay() {
        return 0; //WIP
    }

    @Override
    public void setSpot() {
        //WIP
    }
}
