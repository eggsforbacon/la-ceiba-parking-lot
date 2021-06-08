package model;

import java.io.Serializable;

public class VehicleSpot extends Spot implements Serializable{

    
	private static final long serialVersionUID = 1L;
	private int number;
    private Vehicle spotVehicle;

    public VehicleSpot(int number){
        super();
        this.number = number;
    }

    /**
     Give the vehicle information in that spot <br>
     <b> pre: </b><br>
     <b> post: </b>Vehicle information is given <br>
     */
    @Override
    public String getInformation() {
        String info = "";
        info += "Tipo de vehiculo: "+spotVehicle.getType()+"\n";
        info += "Modelo: "+spotVehicle.getModel()+"\n";
        info += "Placa: "+spotVehicle.getLicensePlate()+"\n";
        info += "Color: "+spotVehicle.getColor()+"\n";
        info += "Propietario: "+spotVehicle.getOwner().getName()+"\n";
        return info;
    }

    @Override
    public String getActualPosition() {
        return number+"";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
