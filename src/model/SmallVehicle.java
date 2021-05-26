package model;

public class SmallVehicle extends Vehicle{
    private final static int HOURVALUE = 2000;
    private final static int TWOHOURSVALUE = 3000;
    private final static int ADITIONAL = 500;
    private final static int DAY = 6000;
    private final static int NIGHT = 6000;
    private final static int DAYANDNIGHT = 8000;
    private final static int MONTH = 55000;
    private final static int[] availableSpots = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

    public SmallVehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, String observations, int stayIndicator, int numberOfTime) {
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
