package model;

public class VehicleGenericChildren extends Vehicle{

    public VehicleGenericChildren(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, String observations, int stayIndicator, int numberOfTime) {
        super(typeIndicator, model, licensePlate, color, owner, spot, observations, stayIndicator, numberOfTime);
    }

    @Override
    public void calculateValueToPay() {


    }

 

	@Override
	public String showInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getAvailablespots() {
		
		return null;
	}
}
