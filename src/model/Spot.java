package model;

import java.io.Serializable;

/**
 * Represents a parking spot in the parking lot generally. <br>
 */
public abstract class Spot implements Serializable {

    private static final long serialVersionUID = 1L;
    private Spot right;
    private Spot up;
    private Spot down;
    private Spot left;

    /**
     * This is the main constructor of the class. <br>
     */
    public Spot() {
        right = null;
        up = null;
        down = null;
        left = null;
    }

    /*Getters and Setters*/

    /**
     * @return The spot's vehicle's information as a string. <br>
     */
    public abstract String getInformation();

    /**
     * @return The current position. <br>
     */
    public abstract String getActualPosition();

    /**
     * @return The spot to the right of this spot. <br>
     */
    public Spot getRight() {
        return right;
    }

    /**
     * @param right The spot to the right of this spot. <br>
     */
    public void setRight(Spot right) {
        this.right = right;
    }

    /**
     * @return The spot above this spot. <br>
     */
    public Spot getUp() {
        return up;
    }

    /**
     * @param up The spot above this spot. <br>
     */
    public void setUp(Spot up) {
        this.up = up;
    }

    /**
     * @return The spot below this spot. <br>
     */
    public Spot getDown() {
        return down;
    }

    /**
     * @param down The spot below this spot. <br>
     */
    public void setDown(Spot down) {
        this.down = down;
    }

    /**
     * @return The spot to the left of this spot. <br>
     */
    public Spot getLeft() {
        return left;
    }

    /**
     * @param left The spot to the left of this spot. <br>
     */
    public void setLeft(Spot left) {
        this.left = left;
    }
}
