package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Vehicle {

    protected VehicleType type; //Esto se define en base a un Enum, es el tipo del vehiculo (carro,moto,etc)
    protected String model;  //En plan Mazda, chevrolet, etc
    protected String licensePlate; //la placa :v
    protected String color;
    protected Client owner; //aqui toca conectarlo con esa clase
    protected boolean enabled;
    protected int spot; //el numero del puesto en el parqueadero
    protected String observations;
    protected double valueToPay; //esto se calcula llamando a calculateValue
    protected StayTime stay; //Es un enum, puede ser hora, dia, mes o indefinido
    protected LocalDateTime entryDate; //la fecha en que entro el vehiculo
    protected String entryDateString; //eso pero en string
    protected int numberOfTime; // el numero de tiempo, en plan si escoge horas es 2 horas, si escoge meses es 2 meses blablabla
    protected LocalDateTime supposedExitDate; // La fecha en que DEBERIA irse
    protected String supposedExitDateString;
    protected LocalDateTime actualExitDate; //La fecha en que se fue
    protected String actualExitDateString;
    protected boolean additionalTime; // para saber si se paso de la fecha acordada o no
    private DateTimeFormatter formatter; // ignoralo


    public Vehicle(int typeIndicator,String model,String licensePlate,String color,Client owner,int spot,String observations,int stayIndicator,int numberOfTime){
        type = VehicleType.values()[typeIndicator];
        enabled = true;
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.owner = owner;
        this.spot = spot;
        this.observations = observations;
        valueToPay = 0;
        stay = stay.values()[stayIndicator];
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        entryDate = LocalDateTime.now();
        entryDateString = entryDate.format(formatter);
        this.numberOfTime = numberOfTime;
        calculateExitDate();
    }

    /**
     Calculate the supposed date of departure of the vehicle based on what is specified previously <br>
     <b> pre: </b> The date of entry and the type of stay must be defined<br>
     <b> post: </b>Obtains by means of a String the date on which the vehicle should leave <br>
     */
    public void calculateExitDate(){
        switch (stay){
            case HORA:
                supposedExitDate = entryDate.plusHours(numberOfTime);
                supposedExitDateString = supposedExitDate.toString();
                break;
            case DIA:
                supposedExitDate = entryDate.plusDays(numberOfTime);
                supposedExitDateString = supposedExitDate.toString();
                break;
            case MES:
                supposedExitDate = entryDate.plusMonths(numberOfTime);
                supposedExitDateString = supposedExitDate.toString();
                break;

            case INDEFINIDO:
                supposedExitDateString = "No se puede calcular. Indefinido";
                break;

        }
    }
    /**
     Take out a vehicle from the parking lot <br>
     <b> pre: </b> <br>
     <b> post: </b>Take out the vehicle, disabling it and giving the departure date <br>
     */
    public void takeOut(){
        enabled = false;
        actualExitDate = LocalDateTime.now();
        actualExitDateString = actualExitDate.format(formatter);
    }

    /**
     Check if the vehicle exceeded the supposed exit date <br>
     <b> pre: </b> <br>
     <b> post: </b>Sets the boolean that indicates if the time was exceeded to true <br>
     */
    public void checkAdditionalTime(){
        calculateExitDate();
        if(!stay.toString().equalsIgnoreCase("INDEFINIDO") ){
            if(LocalDateTime.now().isAfter(supposedExitDate)){
                additionalTime = true;
            }
        }
    }

    public abstract void calculateValueToPay();
    public abstract void setSpot();



    //Getters and setters

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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public double getValueToPay() {
        return valueToPay;
    }

    public void setValueToPay(double valueToPay) {
        this.valueToPay = valueToPay;
    }

    public String getEntryDateString() {
        return entryDateString;
    }

    public void setEntryDateString(String entryDateString) {
        this.entryDateString = entryDateString;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }

    public String getSupposedExitDateString() {
        return supposedExitDateString;
    }

    public void setSupposedExitDateString(String supposedExitDateString) {
        this.supposedExitDateString = supposedExitDateString;
    }

    public String getActualExitDateString() {
        return actualExitDateString;
    }

    public void setActualExitDateString(String actualExitDateString) {
        this.actualExitDateString = actualExitDateString;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getSupposedExitDate() {
        return supposedExitDate;
    }

    public void setSupposedExitDate(LocalDateTime supposedExitDate) {
        this.supposedExitDate = supposedExitDate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public StayTime getStay() {
        return stay;
    }

    public void setStay(StayTime stay) {
        this.stay = stay;
    }

    public LocalDateTime getActualExitDate() {
        return actualExitDate;
    }

    public void setActualExitDate(LocalDateTime actualExitDate) {
        this.actualExitDate = actualExitDate;
    }

    public boolean isAdditionalTime() {
        return additionalTime;
    }

    public void setAdditionalTime(boolean additionalTime) {
        this.additionalTime = additionalTime;
    }
}
