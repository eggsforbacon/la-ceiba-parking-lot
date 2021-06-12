package model;

import java.io.Serializable;

/**
 * A general depiction of a binary tree of vehicles. <br>
 */
@SuppressWarnings("unused")
public abstract class BinaryTree implements Serializable {

    private static final long serialVersionUID = 2L;
    protected BinaryTree right;
    protected BinaryTree left;
    protected Vehicle btVehicle;
    protected String licensePlate;
    protected int number;
    private double valueToPay;

    /**
     * This is the main constructor of the class. <br>
     */
    public BinaryTree() {
        right = null;
        left = null;
        btVehicle = null;
        licensePlate = "";
        number = 0;
        setValueToPay(0);
    }

    /**
     * @param newVehicle The vehicle to be set to. <br>
     */
    public abstract void setBtVehicle(Vehicle newVehicle);

    /**
     * @return The right leaf. <br>
     */
    public BinaryTree getRight() {
        return right;
    }

    /**
     * @param right The right leaf. <br>
     */
    public void setRight(BinaryTree right) {
        this.right = right;
    }

    /**
     * @return The left leaf. <br>
     */
    public BinaryTree getLeft() {
        return left;
    }

    /**
     * @param left The left leaf. <br>
     */
    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    /**
     * @return The vehicle. <br>
     */
    public Vehicle getBtVehicle() {
        return btVehicle;
    }

    /**
     * @return The license plate. <br>
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @param licensePlate The license plate. <br>
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * @return The value to pay. <br>
     */
    public double getValueToPay() {
        return valueToPay;
    }

    /**
     * @param valueToPay The value to pay. <br>
     */
    public void setValueToPay(double valueToPay) {
        this.valueToPay = valueToPay;
    }
}
