package model;

public class BTPerHourOrDaily extends BinaryTree{

    private String entryDate;
    private String type;
    private String entryHour;
    private String exitHour;
    private String observations;

    public BTPerHourOrDaily(){
        super();
        entryDate = "";
        type = "";
        entryHour = "";
        exitHour = "";
        observations = "";
    }

    @Override
    public void setBtVehicle(Vehicle newVehicle) {
        btVehicle = newVehicle;
        entryDate = btVehicle.getEntryDateString();
        type = btVehicle.getType().toString();
        String[] parts = btVehicle.getEntryDateString().split(" ");
        entryHour = parts[1];
        observations = btVehicle.getObservations();
    }


    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntryHour() {
        return entryHour;
    }

    public void setEntryHour(String entryHour) {
        this.entryHour = entryHour;
    }

    public String getExitHour() {
        return exitHour;
    }

    public void setExitHour(String exitHour) {
        this.exitHour = exitHour;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
