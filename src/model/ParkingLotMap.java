package model;

public class ParkingLotMap {
    private Spot leftColumn;
    private Spot bottomRow;
    private Spot rightColumn;
    private int leftColumnNumber;
    private int bottomRowNumber;
    private int rightColumnNumber;

    public ParkingLotMap(){
        leftColumnNumber = 1;
        bottomRowNumber = 1;
        rightColumnNumber = 1;
    }

    public void setLeftColumn(){
        leftColumn = new MotorcycleSpot(-10,-9);
        setLeftColumn(leftColumn);
    }

    private void setLeftColumn(Spot a){
        if(leftColumnNumber < 13){
            Spot b=null;
            int t = translatorLC(leftColumnNumber);
            if(leftColumnNumber < 5){
                b = new MotorcycleSpot(t,t+1);
            }
            else{
                b = new VehicleSpot(t);
            }
            a.setDown(b);
            leftColumnNumber++;
            setLeftColumn(a.getDown());
        }
        else if(leftColumnNumber == 13){
            setBottomRow();
            a.setRigth(bottomRow);
        }
    }

    public void setBottomRow(){
        bottomRow = new VehicleSpot(10);
        setBottomRow(bottomRow);
    }

    private void setBottomRow(Spot a){
        if(bottomRowNumber < 7){
            int t = translatorBR(bottomRowNumber);
            Spot b = new VehicleSpot(t);
            a.setRigth(b);
            bottomRowNumber++;
            setBottomRow(a.getRigth());
        }
        else if(bottomRowNumber == 7){
            setRightColumn();
            a.setUp(rightColumn);
        }
    }

    public void setRightColumn(){
        rightColumn = new VehicleSpot(17);
        setRightColumn(rightColumn);
    }
    private void setRightColumn(Spot a){
        if(rightColumnNumber < 13){
            int t = translatorRC(rightColumnNumber);
            Spot b = new VehicleSpot(t);
            a.setUp(b);
            rightColumnNumber++;
            setRightColumn(a.getUp());
        }
    }

    private int translatorBR(int n){
        switch (n){
            case 1:
                return 11;
            case 2:
                return 12;
            case 3:
                return 13;
            case 4:
                return 14;
            case 5:
                return 15;
            case 6:
                return 16;
        }
        return 0;
    }

    private int translatorRC(int n){
        switch (n){
            case 1:
                return 18;
            case 2:
                return 19;
            case 3:
                return 20;
            case 4:
                return 21;
            case 5:
                return 22;
            case 6:
                return 23;
            case 7:
                return 24;
            case 8:
                return 25;
            case 9:
                return 26;
            case 10:
                return 27;
            case 11:
                return 28;
            case 12:
                return 29;
        }
        return 0;
    }

    private int translatorLC(int n){
        switch (n){
            case 1:
                return -8;
            case 2:
                return -6;
            case 3:
                return -4;
            case 4:
                return -2;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 4;
            case 8:
                return 5;
            case 9:
                return 6;
            case 10:
                return 7;
            case 11:
                return 8;
            case 12:
                return 9;
        }
        return 0;
    }

    public void print(){
        print(leftColumn);
    }

    private void print(Spot a){
        if(a.getDown()==null){
            System.out.println(a.getActualPosition());
            printBR();
        }
        else{
            System.out.println(a.getActualPosition());
            print(a.getDown());
        }
    }
    public void printBR(){
        printBR(bottomRow);
    }
    private void printBR(Spot a){
        if(a.getRigth()==null){
            System.out.println(a.getActualPosition());
            printRC();
        }
        else{
            System.out.println(a.getActualPosition());
            printBR(a.getRigth());
        }
    }

    public void printRC(){
        printRC(rightColumn);
    }
    private void printRC(Spot a){
        if(a.getUp()==null){
            System.out.println(a.getActualPosition());
        }
        else{
            System.out.println(a.getActualPosition());
            printRC(a.getUp());
        }
    }


}
