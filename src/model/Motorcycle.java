package model;

public class Motorcycle extends Vehicle{
    private final static int HOURVALUE = 1000;
    private final static int ADITIONALVALUE = 500;
    private final static int DAYVALUE = 3000;
    private final static int NIGHTVALUE = 3000;
    private final static int DAYANDNIGHTVALUE = 4000;
    private final static int MONTHVALUE = 30000;
    private final static int[] availableSpots = {-9,-8,-7,-6,-5,-4,-3,-2,-1};

    public Motorcycle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, String observations, int stayIndicator, int numberOfTime) {
        super(typeIndicator, model, licensePlate, color, owner, spot, observations, stayIndicator, numberOfTime);
    }

    @Override
    public double calculateValueToPay() {
        return 0;
    }

    @Override
    public void setSpot() {

    }
}
