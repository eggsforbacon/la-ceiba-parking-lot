package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents the attributes and functionalities of a large vehicle.
 */
public class LargeVehicle extends Vehicle implements Serializable {

    private static final long serialVersionUID = 2L;
    private final static int HOURVALUE = 3000;
    private final static int TWOHOURSVALUE = 4000;
    private final static int ADITIONALVALUE = 1000;
    private final static int DAYVALUE = 12000;
    private final static int MONTHVALUE = 60000;
    private final int[] availableSpots = {21, 22, 23, 24, 25, 26, 27, 28, 29};

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
    public LargeVehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, int stayIndicator, int numberOfTime) {
        super(typeIndicator, model, licensePlate, color, owner, spot, stayIndicator, numberOfTime);
        changeStayTime();
        calculateValueToPay();
    }

    /**
     * Change the user's stay time if necessary<br>
     * <b> pre: </b> The stay attribute must be initialized <br>
     * <b> post: </b>Change the length of stay or not<br>
     *
     * @return stayTime
     */
    public StayTime changeStayTime() {
        switch (stay) {
            case HORA:
                if (numberOfTime >= 10) {
                    return StayTime.DIA;
                }
                break;
            case DIA:
                if (numberOfTime >= 5) {
                    return StayTime.MES;
                }
                break;
            default:
                //A�adir excepci�n
                break;
        }
        return stay;
    }

    /**
     * Calculates the value that the client has to pay for their stay in the parking lot. <br>
     */
    @Override
    public void calculateValueToPay() {
        StayTime compare = stay;
        int comp = numberOfTime;
        if (changeStayTime() != stay) {
            compare = changeStayTime();
            comp = 1;
        }
        switch (compare.toString()) {
            case "HORA":
                switch (comp) {
                    case 1:
                        valueToPay = HOURVALUE;
                        break;
                    case 2:
                        valueToPay = TWOHOURSVALUE;
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        valueToPay = TWOHOURSVALUE + (comp - 2) * ADITIONALVALUE;
                        break;
                }
                checkAdditionalTime();
                if (additionalTime) {
                    valueToPay = calculateValueAdditionalTime();
                }
                break;
            case "DIA":
                valueToPay = DAYVALUE * comp;
                if (additionalTime) {
                    valueToPay = calculateValueAdditionalTime();
                }
                break;
            case "MES":
                valueToPay = MONTHVALUE * comp;
                if (additionalTime) {
                    valueToPay = calculateValueAdditionalTime();
                }
                break;
            case "INDEFINIDO":
                valueToPay = calculateValueAdditionalTime();
                break;
            default:
        }
    }

    /**
     * Calculate the amount to pay when the time of stay is undefined or exceeds what was agreed<br>
     * <b> pre: </b> The stay attribute must be initialized <br>
     * <b> post: </b>The specific value is given<br>
     *
     * @return minimumValue
     */
    public double calculateValueAdditionalTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(entryDate);
        long months = tempDateTime.until(now, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);
        long days = tempDateTime.until(now, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);
        long hours = tempDateTime.until(now, ChronoUnit.HOURS);
        return calculateMinimumValue(months, days, hours);
    }

    /**
     * calculates the minimum value that can be paid for a defined number of time<br>
     * <b> pre: </b> <br>
     * <b> post: </b>The specific value is given<<br>
     *
     * @return value
     */
    public double calculateMinimumValue(long months, long days, long hours) {
        double value = 0;
        value += months * MONTHVALUE;
        value += days * DAYVALUE;
        if (hours >= 5) {
            value += DAYVALUE;
        } else {
            value += hours * ADITIONALVALUE;
        }
        return value;
    }

    /**
     * Shows the information of the vehicle. <br>
     */
    @Override
    public String showInformation() {
        return getType() + ";" + getModel() + ";" + getLicensePlate() + ";" + getColor()
                + ";" + getOwner().getName() + ";" + getOwner().getId() + ";" + getEntryDateString()
                + ";" + getActualExitDateString() + ";" + getValueToPay();
    }

    /**
     * @return The available spots as an integer array with their indexes. <br>
     */
    @Override
    public int[] getAvailableSpots() {
        return availableSpots;
    }

}
