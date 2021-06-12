package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a vehicle withing the parking lot. <br>
 */
@SuppressWarnings("unused")
public abstract class Vehicle implements Comparable<Vehicle>, Serializable {

    private static final long serialVersionUID = 2L;
    protected VehicleType type; //Esto se define en base a un Enum, es el tipo del vehiculo (carro,moto,etc)
    protected String model;  //En plan Mazda, chevrolet, etc
    protected String licensePlate; //la placa :v
    protected String color;
    protected Client owner; //aqui toca conectarlo con esa clase
    protected boolean enabled;
    protected int spot; //el numero del puesto en el parqueadero
    protected double valueToPay; //esto se calcula llamando a calculateValue
    protected StayTime stay; //Es un enum, puede ser hora, dia, mes o indefinido
    protected transient LocalDateTime entryDate; //la fecha en que entro el vehiculo
    protected String entryDateString; //eso pero en string
    protected int numberOfTime; // el numero de tiempo, en plan si escoge horas es 2 horas, si escoge meses es 2 meses blablabla
    protected transient LocalDateTime supposedExitDate; // La fecha en que DEBERIA irse
    protected String supposedExitDateString;
    protected transient LocalDateTime actualExitDate; //La fecha en que se fue
    protected String actualExitDateString;
    protected boolean additionalTime; // para saber si se paso de la fecha acordada o no
    private transient DateTimeFormatter formatter; // ignoralo
    private boolean yetPay;
    private String pay;

    /**
     * This is the main constructor of the class. <br>
     *
     * @param typeIndicator The index that represents the type of the vehicle being instantiated. Must be between <b>0 and 4</b>.<br>
     * @param model         The model of the vehicle being instantiated. @NotEmpty. <br>
     * @param licensePlate  The license plate of the vehicle. @NotEmpty. <br>
     * @param color         The color of the vehicle. @NotEmpty. <br>
     * @param owner         The client that owns the vehicle. @NotNull. <br>
     * @param spot          The index of the spot where the vehicle will be slotted. Must be between <b>-10 and 29</b>.
     * @param stayIndicator The index that represents the stay type of the vehicle. Must be between <b>0 and 3</b>. <br>
     * @param numberOfTime  The amount of time the vehicle will stay inside of the parking lot. The units are given by the stayIndicator. @NotNeg. <br>
     */
    public Vehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, int stayIndicator, int numberOfTime) {
        type = VehicleType.values()[typeIndicator];
        enabled = true;
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.owner = owner;
        this.spot = spot;
        valueToPay = 0;
        stay = StayTime.values()[stayIndicator];
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        entryDate = LocalDateTime.now();
        entryDateString = entryDate.format(formatter);
        this.numberOfTime = numberOfTime;
        setYetPay(false);
        calculateExitDate();
        pay = valueToPay + "";

    }

    /**
     * Calculate the supposed date of departure of the vehicle based on what is specified previously <br>
     * <b> pre: </b> The date of entry and the type of stay must be defined<br>
     * <b> post: </b>Obtains by means of a String the date on which the vehicle should leave <br>
     */
    public void calculateExitDate() {
        switch (stay) {
            case HORA:
                supposedExitDate = entryDate.plusHours(numberOfTime);
                supposedExitDateString = supposedExitDate.format(formatter);
                break;
            case DIA:
                supposedExitDate = entryDate.plusDays(numberOfTime);
                supposedExitDateString = supposedExitDate.format(formatter);
                break;
            case MES:
                supposedExitDate = entryDate.plusMonths(numberOfTime);
                supposedExitDateString = supposedExitDate.format(formatter);
                break;

            case INDEFINIDO:
                supposedExitDateString = "No se puede calcular. Indefinido";
                break;

        }
    }

    /**
     * Take out a vehicle from the parking lot <br>
     * <b> pre: </b> <br>
     * <b> post: </b>Take out the vehicle, disabling it and giving the departure date <br>
     */
    public void takeOut() {
        enabled = false;
        actualExitDate = LocalDateTime.now();
        actualExitDateString = actualExitDate.format(formatter);
    }

    /**
     * Check if the vehicle exceeded the supposed exit date <br>
     * <b> pre: </b> <br>
     * <b> post: </b>Sets the boolean that indicates if the time was exceeded to true <br>
     */
    public void checkAdditionalTime() {
        calculateExitDate();
        if (!stay.toString().equalsIgnoreCase("INDEFINIDO")) {
            if (LocalDateTime.now().isAfter(supposedExitDate)) {
                additionalTime = true;
            }
        }
    }

    /**
     * Calculates the value that the client has to pay for their stay in the parking lot. <br>
     */
    public abstract void calculateValueToPay();

    //Getters and setters

    /**
     * Gets the current status of the vehicle as a string value. <br>
     */
    public String getStatus() {
        if (enabled) {
            return "SI";
        } else {
            return "NO";
        }
    }

    /*Getters and Setters*/

    /**
     * @return The model of the vehicle. <br>
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model The model of the vehicle. <br>
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return The license plate of the vehicle. <br>
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @param licensePlate The license plate of the vehicle. <br>
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * @return The color of the vehicle. <br>
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color The color of the vehicle. <br>
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return The owner of the vehicle. <br>
     */
    public Client getOwner() {
        return owner;
    }

    /**
     * @param owner The owner of the vehicle. <br>
     */
    public void setOwner(Client owner) {
        this.owner = owner;
    }

    /**
     * @param spot The spot of the vehicle. <br>
     */
    public void setSpot(int spot) {
        this.spot = spot;
    }

    /**
     * @return The spot of the vehicle. <br>
     */
    public int getSpot() {
        return spot;
    }

    /**
     * @return The boolean state of the vehicle. <br>
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled The boolean state of the vehicle. <br>
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return The value the client has to pay for the vehicle. <br>
     */
    public String getValueToPay() {
        return valueToPay + "";
    }

    /**
     * @param valueToPay The value the client has to pay for the vehicle. <br>
     */
    public void setValueToPay(double valueToPay) {
        this.valueToPay = valueToPay;
    }

    /**
     * @return The date the entry date of the vehicle as string. <br>
     */
    public String getEntryDateString() {
        return entryDateString;
    }

    /**
     * @param entryDateString The date the entry date of the vehicle as string. <br>
     */
    public void setEntryDateString(String entryDateString) {
        this.entryDateString = entryDateString;
    }

    /**
     * @return The amount of time the vehicle is staying. <br>
     */
    public int getNumberOfTime() {
        return numberOfTime;
    }

    /**
     * @param numberOfTime The amount of time the vehicle is staying. <br>
     */
    public void setNumberOfTime(int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }

    /**
     * @return The presumed exit date of the vehicle as a string. <br>
     */
    public String getSupposedExitDateString() {
        return supposedExitDateString;
    }

    /**
     * @param supposedExitDateString The presumed exit date of the vehicle as a string. <br>
     */
    public void setSupposedExitDateString(String supposedExitDateString) {
        this.supposedExitDateString = supposedExitDateString;
    }

    /**
     * @return The actual exit date of the vehicle as a string. <br>
     */
    public String getActualExitDateString() {
        return actualExitDateString;
    }

    /**
     * @param actualExitDateString The param exit date of the vehicle as a string. <br>
     */
    public void setActualExitDateString(String actualExitDateString) {
        this.actualExitDateString = actualExitDateString;
    }

    /**
     * @return The entry date of the vehicle. <br>
     */
    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate The entry date of the vehicle. <br>
     */
    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @return The presumed exit date of the vehicle. <br>
     */
    public LocalDateTime getSupposedExitDate() {
        return supposedExitDate;
    }

    /**
     * @param supposedExitDate The presumed exit date of the vehicle. <br>
     */
    public void setSupposedExitDate(LocalDateTime supposedExitDate) {
        this.supposedExitDate = supposedExitDate;
    }

    /**
     * @return The type of the vehicle. <br>
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * @param type The type of the vehicle. <br>
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * @return The type of stay of the vehicle. <br>
     */
    public StayTime getStay() {
        return stay;
    }

    /**
     * @param stay The type of stay of the vehicle. <br>
     */
    public void setStay(StayTime stay) {
        this.stay = stay;
    }

    /**
     * @return The actual exit date of the vehicle. <br>
     */
    public LocalDateTime getActualExitDate() {
        return actualExitDate;
    }

    /**
     * @param actualExitDate The actual exit date of the vehicle. <br>
     */
    public void setActualExitDate(LocalDateTime actualExitDate) {
        this.actualExitDate = actualExitDate;
    }

    /**
     * @return Whether or not the vehicle has additional time. <br>
     */
    public boolean isAdditionalTime() {
        return additionalTime;
    }

    /**
     * @param additionalTime Whether or not the vehicle has additional time. <br>
     */
    public void setAdditionalTime(boolean additionalTime) {
        this.additionalTime = additionalTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Vehicle o) {
        return getLicensePlate().compareToIgnoreCase(o.getLicensePlate());
    }

    /**
     * @return The available spots as an integer array with their indexes. <br>
     */
    public abstract int[] getAvailableSpots();

    /**
     * Shows the information of the vehicle. <br>
     */
    public abstract String showInformation();

    /**
     * @return Whether or not the client has payed yet. <br>
     */
    public boolean getYetPay() {
        return yetPay;
    }

    /**
     * @param yetPay Whether or not the client has payed yet. <br>
     */
    public void setYetPay(boolean yetPay) {
        this.yetPay = yetPay;
    }

    /**
     * @return The amount that has to be payed by the client. <br>
     */
    public String getPay() {
        return pay;
    }

    /**
     * @param pay The amount that has to be payed by the client. <br>
     */
    public void setPay(String pay) {
        this.pay = pay;
    }
}
