package model;

public class Client extends Person implements Comparable<Client> {
	
	private String cellNumber;
	private boolean status;
	
    public Client(String name, String id, String cellNumber) {
        super(name, id);
        this.cellNumber=cellNumber;
        status =true;
    }
    

	public boolean getStatus() {
		return status;
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
	

    
    
}
