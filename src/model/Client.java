package model;

import java.io.Serializable;

public class Client extends Person implements Comparable<Client>, Serializable {
	

	private String cellNumber;
	private boolean status;
	
    public Client(String name, String id, String cellNumber) {
        super(name, id);
        this.cellNumber=cellNumber;
        status =true;
    }
    
//ha
	public String getStatus() {
    	if(status){
    		return "SI";
		}
    	else{
    		return "NO";
		}
	}

	public boolean isEnabled(){
    	return  status;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	} 
	public void setStatus(boolean status) {
		this.status = status;
	}


	@Override
	public int compareTo(Client o) {
		return getName().compareTo(o.getName());
	}


	@Override
	public String showInformation() {
		String info = getName()+";";
		info+=getId()+";";
		info+=getCellNumber()+";";
		return info;
		
	}
	

    
    
}
