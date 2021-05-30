package model;

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


    public void changeStayTime(){
        switch (stay){
            case HORA:
                if(numberOfTime>=5){
                    stay = StayTime.DIA;
                }
                break;
            case DIA:
                //if(numberOfTime)
                break;
        }
    }
    @Override
    public double calculateValueToPay() {
        double value = 0;
        switch (stay){
            case HORA:
                value = HOURVALUE*numberOfTime;
                if(value > DAYVALUE){
                    stay = StayTime.values()[1];
                    value = DAYVALUE;
                    calculateExitDate();
                    checkAdditionalTime();
                }
                if(additionalTime){

                    //supposedExitDate; ///NO
                }
                break;
            case DIA:

                break;
            case MES:

                break;
            case  INDEFINIDO:

                break;
        }
        return 0; //WIP
    }

    @Override
    public void setSpot() {
        //WIP
    }
}
