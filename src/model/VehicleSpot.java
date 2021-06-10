package model;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

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
        if(spotVehicle != null){
            info += "Tipo de vehiculo: "+spotVehicle.getType()+"\n";
            info += "Modelo: "+spotVehicle.getModel()+"\n";
            info += "Placa: "+spotVehicle.getLicensePlate()+"\n";
            info += "Color: "+spotVehicle.getColor()+"\n";
            info += "Propietario: "+spotVehicle.getOwner().getName()+"\n";
        }
        else{
            info += "Aun no hay información de un vehiculo en este puesto\n";
        }

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
    
    public void iniTableViewPerHOD() {
    	laCeiba.fillPerHODVehiclesPL();
    	 
    	ObservableList<Vehicle> perHODVehicles = FXCollections.observableArrayList(laCeiba.getPerHODVehiclesPL());
    	perHODVehiclesTBV.setItems(perHODVehicles);
    	perHODVehicleTypeCOL.setCellValueFactory(new PropertyValueFactory<>("valueToPay"));
    	perHODVehiclePlateCOL.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
    	perHODVehicleEntryCOL.setCellValueFactory(new PropertyValueFactory<>("entryDateString"));
    	perHODVehicleExitCOL.setCellValueFactory(new PropertyValueFactory<>("supposedExitDateString"));
    	perHODVehiclePayCOL.setCellValueFactory(new PropertyValueFactory<>("valueToPay"));
       
        //SideBar
        perHODVehicleDeleteBTN.setDisable(true);
        perHODVehicleEditBTN.setDisable(true);
        perHODVehiclesTBV.setOnMouseClicked(event -> {
            if (! perHODVehiclesTBV.getSelectionModel().getSelectedItems().isEmpty()) {
            	perHODVehicleDeleteBTN.setDisable(false);
            	perHODVehicleEditBTN.setDisable(false);
            }
        });

    }
}
