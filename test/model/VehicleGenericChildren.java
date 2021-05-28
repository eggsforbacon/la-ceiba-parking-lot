package model;

public class VehicleGenericChildren extends Vehicle{

    public VehicleGenericChildren(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, String observations, int stayIndicator, int numberOfTime) {
        super(typeIndicator, model, licensePlate, color, owner, spot, observations, stayIndicator, numberOfTime);
    }

    @Override
    public double calculateValueToPay() {
        return 0;
    }

    @Override
    public void setSpot() {
     //Something
    }
}
