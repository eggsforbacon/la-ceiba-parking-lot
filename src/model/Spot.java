package model;

public abstract class Spot {
    private Spot rigth;
    private Spot up;
    private Spot down;
    private Spot left;


    public Spot(){
        rigth = null;
        up = null;
        down = null;
        left = null;
    }

    public abstract String getInformation();

    public abstract String getActualPosition();

    public Spot getRigth() {
        return rigth;
    }

    public void setRigth(Spot rigth) {
        this.rigth = rigth;
    }

    public Spot getUp() {
        return up;
    }

    public void setUp(Spot up) {
        this.up = up;
    }

    public Spot getDown() {
        return down;
    }

    public void setDown(Spot down) {
        this.down = down;
    }

    public Spot getLeft() {
        return left;
    }

    public void setLeft(Spot left) {
        this.left = left;
    }
}
