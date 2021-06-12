package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents the attributes and functionalities of a motorcycle.
 */
public class Motorcycle extends Vehicle implements Serializable {

    private final static int HOURVALUE = 1000;
    private final static int ADITIONALVALUE = 500;
    private final static int DAYVALUE = 4000;
    private final static int MONTHVALUE = 30000;
    private final int[] availableSpots = {-9, -8, -7, -6, -5, -4, -3, -2, -1};

    /**
     * This is the main constructor of the class. <br>
     */
    public Motorcycle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, int stayIndicator, int numberOfTime) {
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
                if (numberOfTime >= 7) {
                    return StayTime.DIA;
                }
                break;
            case DIA:
                if (numberOfTime >= 7) {
                    return StayTime.MES;
                }
                break;
            default:
                //Añadir excepción
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
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        valueToPay = HOURVALUE + (comp - 1) * ADITIONALVALUE;
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
