package model;

import java.io.Serializable;

public class Employee extends Person implements Comparable<Employee>, Serializable {

	private String username;
	private String password;
	private boolean state;
	

	public Employee(String name, String id,String username, String password) {
        super(name, id);
        this.username=username;
        this.password=password;
        state=true;
    }
   
	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus(){
		if(state){
			return "SI";
		}
		else{
			return "NO";
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(Employee o) {
		return getUsername().compareToIgnoreCase(o.getUsername());
	}

	@Override
	public String showInformation() {
		String info=getName()+";"+getId()+";"+getUsername();
		return info;
	}

	

}
