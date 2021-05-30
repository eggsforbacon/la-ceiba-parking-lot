package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ParkingLot implements Serializable {
    private ArrayList<Client> clientsPL;
    private ArrayList<Vehicle> vehiclesPL;
  

    public ParkingLot(){
        clientsPL = new ArrayList<>();
        vehiclesPL = new ArrayList<>();
    }
    
    public String ClientVeryfier(String name, String id, String number) {
    	String temp="";
	    	if(clientsPL!=null) {
	    		Client aux=new Client(name, id, number);
		    	for(int i=0;i<clientsPL.size();i++) {		    		
		    		if (aux.getName().equalsIgnoreCase(clientsPL.get(i).getName())) {
		    			temp+="name";
		    		}
		    		if(aux.getId().equalsIgnoreCase(clientsPL.get(i).getId())) {
		    			temp+="id";
		    		}
		    	}
	    	}
	    	 return temp;
    	}
   
}
