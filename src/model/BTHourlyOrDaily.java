package model;

import java.io.Serializable;

/**
 * A binary tree for daily or hourly vehicles. <br>
 */
@SuppressWarnings("unused")
public class BTHourlyOrDaily extends BinaryTree implements Serializable {

    private static final long serialVersionUID = 2L;
    private String entryDate;
    private String type;
    private String entryHour;
    private String exitHour;
    private String observations;

    /**
     * This is the general constructor of the class. <br>
     */
    public BTHourlyOrDaily() {
        super();
        entryDate = "";
        type = "";
        entryHour = "";
        exitHour = "";
        observations = "";
    }

    /**
     * @param newVehicle The vehicle to be set to. <br>
     */
    @Override
    public void setBtVehicle(Vehicle newVehicle) {
        btVehicle = newVehicle;
        entryDate = btVehicle.getEntryDateString();
        type = btVehicle.getType().toString();
        String[] parts = btVehicle.getEntryDateString().split(" ");
        entryHour = parts[1];

    }

    /**
     * @return The entry date of the vehicle. <br>
     */
    public String getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate The entry date of the vehicle. <br>
     */
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @return The type of vehicle. <br>
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type date of te vehicle. <br>
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The entry hour of te vehicle. <br>
     */
    public String getEntryHour() {
        return entryHour;
    }

    /**
     * @param entryHour The entry hour of te vehicle. <br>
     */
    public void setEntryHour(String entryHour) {
        this.entryHour = entryHour;
    }

    /**
     * @return The exit hour of te vehicle. <br>
     */
    public String getExitHour() {
        return exitHour;
    }

    /**
     * @param exitHour The exit hour of te vehicle. <br>
     */
    public void setExitHour(String exitHour) {
        this.exitHour = exitHour;
    }

    /**
     * @return The observations hour of te vehicle. <br>
     */
    public String getObservations() {
        return observations;
    }

    /**
     * @param observations The observations hour of te vehicle. <br>
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }
}
