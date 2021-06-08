package model;

import java.io.Serializable;

public class MotorcycleSpot extends Spot implements Serializable {

    
	private static final long serialVersionUID = 1L;
	private int number1;
    private int number2;
    private Vehicle spotVehicle1;
    private Vehicle spotVehicle2;

    public MotorcycleSpot(int number1,int number2){
        super();
        this.number1 = number1;
        this.number2 = number2;
    }

    /**
     Give the vehicle information in that spot <br>
     <b> pre: </b><br>
     <b> post: </b>Vehicle information is given <br>
     */
    @Override
    public String getInformation() {
        String info = "";
        info += "Motocicleta 1:"+"\n";
        info += "Tipo de vehiculo: "+spotVehicle1.getType()+"\n";
        info += "Modelo: "+spotVehicle1.getModel()+"\n";
        info += "Placa: "+spotVehicle1.getLicensePlate()+"\n";
        info += "Color: "+spotVehicle1.getColor()+"\n";
        info += "Propietario: "+spotVehicle1.getOwner().getName()+"\n"+"\n";
        info += "Motocicleta 2:"+"\n";
        info += "Tipo de vehiculo: "+spotVehicle2.getType()+"\n";
        info += "Modelo: "+spotVehicle2.getModel()+"\n";
        info += "Placa: "+spotVehicle2.getLicensePlate()+"\n";
        info += "Color: "+spotVehicle2.getColor()+"\n";
        info += "Propietario: "+spotVehicle2.getOwner().getName()+"\n";
        return info;
    }

    @Override
    public String getActualPosition() {
        return number1+" "+number2;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }
}
