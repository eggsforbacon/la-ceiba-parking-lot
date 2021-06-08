package model;

import java.io.Serializable;

public abstract class BinaryTree implements Serializable{
   
	private static final long serialVersionUID = 1L;
	protected BinaryTree right;
    protected BinaryTree left;
    protected Vehicle btVehicle;
    protected String licensePlate;
    protected  int number;
    private double valueToPay;

    public BinaryTree(){
        right = null;
        left = null;
        btVehicle = null;
        licensePlate = "";
        number = 0;
        setValueToPay(0);
    }

    public abstract void setBtVehicle(Vehicle newVehicle);

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public Vehicle getBtVehicle() {
        return btVehicle;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

	public double getValueToPay() {
		return valueToPay;
	}

	public void setValueToPay(double valueToPay) {
		this.valueToPay = valueToPay;
	}
	
	
}
