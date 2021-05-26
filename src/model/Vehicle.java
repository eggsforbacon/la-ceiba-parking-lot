package model;

public class Vehicle {

    private VehicleType type;
    private int typeIndicator;
    private String model;
    private String licensePlate;
    private String color;
    private Client owner;
    private int spot;
    private String observations;

    public Vehicle(int typeIndicator,String model,String licensePlate,String color,Client owner,int spot,String observations){
        this.typeIndicator = typeIndicator;
        type = VehicleType.values()[typeIndicator];
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.owner = owner;
        this.spot = spot;
        this.observations = observations;
    }








    //Getters and setters
    public String getType() {
        return type.toString();
    }

    public void setType(int typeIndicator) {
        this.typeIndicator=typeIndicator;
        type = VehicleType.values()[typeIndicator];
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
