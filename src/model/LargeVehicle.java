package model;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LargeVehicle extends Vehicle {
    private final static int HOURVALUE = 3000;
    private final static int TWOHOURSVALUE = 4000;
    private final static int ADITIONALVALUE = 1000;
    private final static int DAYVALUE = 12000;
    private final static int MONTHVALUE = 60000;
    private final static int[] availableSpots = {21,22,23,24,25,26,27,28,29};

    public LargeVehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, String observations, int stayIndicator, int numberOfTime) {
        super(typeIndicator, model, licensePlate, color, owner, spot, observations, stayIndicator, numberOfTime);
    }


    public StayTime changeStayTime(){
        switch (stay){
            case HORA:
                if(numberOfTime>=10){
                    return StayTime.DIA;
                }
                break;
            case DIA:
                if(numberOfTime>=5){
                    return StayTime.MES;
                }
                break;
        }
        return stay;
    }
    @Override
    public void calculateValueToPay() {
        double value = 0;
        StayTime compare=stay;
        int comp=numberOfTime;
        if(changeStayTime()!=stay){
            compare = changeStayTime();
            comp=1;
        }
        switch (compare.toString()){
            case "HORA":
                switch (comp){
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
                        valueToPay = TWOHOURSVALUE+(comp-2)*ADITIONALVALUE;
                        break;
                }
                checkAdditionalTime();
                if(additionalTime){
                   valueToPay = calculateValueAdditionalTime();
                }
                break;
            case "DIA":
                valueToPay = DAYVALUE*comp;
                if(additionalTime){
                    valueToPay = calculateValueAdditionalTime();
                }
                break;
            case "MES":
                valueToPay = MONTHVALUE*comp;
                if(additionalTime){
                    valueToPay = calculateValueAdditionalTime();
                }
                break;
            case  "INDEFINIDO":
                valueToPay= calculateValueAdditionalTime();
                break;
            default:
        }//WIP
    }

    private double calculateValueAdditionalTime(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(entryDate);
        long months = tempDateTime.until(now, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );
        long days = tempDateTime.until(now, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );
        long hours = tempDateTime.until(now, ChronoUnit.HOURS );
        return calculateMinimunValue(months, days,hours);
    }
    private double calculateMinimunValue(long months,long days,long hours){
        double value = 0;
        value += months * MONTHVALUE;
        value += days*DAYVALUE;
        if(hours >= 5){
            value += DAYVALUE;
        }
        else{
            value += hours*ADITIONALVALUE;
        }
        return value;
    }

    @Override
    public void setSpot() {
        //WIP
    }
}
