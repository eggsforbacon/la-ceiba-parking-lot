package model;

public class Client extends Person {
	
	private String cellNumber;
	private boolean state;
	
    public Client(String name, String id, String cellNumber) {
        super(name, id);
        this.cellNumber=cellNumber;
        state=true;
    }
    

	public boolean getState() {
		return state;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	} 
	public void setState(boolean state) {
		this.state = state;
	}
    
    
}
