package model;

import java.io.Serializable;

/**
 * Represents the clients of the parking lot <br>
 */
@SuppressWarnings("unused")
public class Client extends Person implements Comparable<Client>, Serializable {

    private String cellNumber;
    private boolean status;

    /**
     * This is the main constructor of the class. <br>
     */
    public Client(String name, String id, String cellNumber) {
        super(name, id);
        this.cellNumber = cellNumber;
        status = true;
    }

    /**
     * Gets the current status of the vehicle as a string value. <br>
     */
    public String getStatus() {
        if (status) {
            return "SI";
        } else {
            return "NO";
        }
    }

    /**
     * @return Whether or not the client is enabled. <br>
     */
    public boolean isEnabled() {
        return status;
    }

    /**
     * @return The number of the cell. <br>
     */
    public String getCellNumber() {
        return cellNumber;
    }

    /**
     * @param cellNumber The number of the cell. <br>
     */
    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    /**
     * @param status Whether or not the client is enabled. <br>
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Client o) {
        return getName().compareTo(o.getName());
    }

    /**
     * @return The general information of the person as a String. <br>
     */
    @Override
    public String showInformation() {
        String info = getName() + ";";
        info += getId() + ";";
        info += getCellNumber() + ";";
        return info;
    }
}
