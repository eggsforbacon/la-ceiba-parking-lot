package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ParkingLot implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private ArrayList<Client> clientsPL;
    private ArrayList<Vehicle> vehiclesPL;
    private ArrayList<Employee> employeesPL;
  

    public ParkingLot(){
        clientsPL = new ArrayList<>();
        vehiclesPL = new ArrayList<>();
        employeesPL=new ArrayList<>();
    }
    
    
   //Clients methods 
    /**
    Add a client to the parking lot <br>
    <b> pre: </b>Needs verify if the clients is already in the parking lot<br>
    <b> post: </b>The client will be created and added to the parking lot<br>
    @param name A string who have the new client´s name
    @param id A string who have the new client´s ID
    @param cellNumber A string who have the new client´s cellNumber
    @return true or false
    */
    public boolean addClient(String name, String id, String cellNumber) {
    	String temp=ClientVeryfier(name,id, cellNumber);
    	//Añadir excepción para verificar si el nombre está repetido o si el id está repetido
    	if(temp.contains("name")){
    		return false;
    	}
    	else if(temp.contains("id")) {
    		return false;
    	}
    	else {
    		Client aux=new Client(name, id, cellNumber);
    		clientsPL.add(aux);
    		return true;
    	}
    }
    
    
    /**
    Disable a specific client in the parking lot <br>
    <b> pre: </b>Needs verify if the client already exists and if he does not own a car in the parking lot<br>
    <b> post: </b>The specific client will be disabled<br>
    @param id A string who have the client´s id
    @return true or false
    */
    public boolean disableClientByID(String id) {
    	Client aux=searchByID(id);
    	if(aux!=null) {
    		aux.setState(false);
    		return true;
    	}
    	return false;
    }
    
    
    /**
    Disable a specific client in the parking lot <br>
    <b> pre: </b>Needs verify if the client already exists and if he does not own a car in the parking lot<br>
    <b> post: </b>The specific client will be disabled<br>
    @param name A string who have the client´s name
    @return true or false
    */
    public boolean disableClientByName(String name) {
    	Client aux=searchByName(name);
    	if(aux!=null) {
    		aux.setState(false);
    		return true;
    	}
    	return false;
    }
    
    
    /**
    Update a specific client's name <br>
    <b> pre: </b>Needs verify if the client already exists and if his new name is not used by other client<br>
    <b> post: </b>The specific client will be updated his name<br>
    @param newName A string who have the client´s newName
    @param pastName A string who have the client´s pastName
    @return true or false
    */
    public boolean updateClientName(String newName, String pastName) {
    	if((searchByName(newName)==null)&&(searchByName(pastName)!=null)) {
    		searchByName(pastName).setName(newName);
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    
    /**
    Update a specific client's ID<br>
    <b> pre: </b>Needs verify if the client already exists and if his new ID is not used by other client<br>
    <b> post: </b>The specific client will be updated his ID<br>
    @param newID A string who have the client´s new ID
    @param pastID A string who have the client´s past ID
    @return true or false
    */
    public boolean updateClientID(String pastID, String newID) {
    	if((searchByID(newID)==null)&&(searchByID(pastID)!=null)) {
    		searchByID(pastID).setId(newID);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    /**
    Update a specific client's cell number<br>
    <b> pre: </b>Needs verify if the client already exists<br>
    <b> post: </b>The specific client will be updated his cell number<br>
    @param id A string who have the client´s ID
    @param newCellNumber A string who have the client´s new cell number
    @return true or false
    */
    public boolean updateClientCellNumber(String id, String newCellNumber) {
    	Client aux=searchByID(id);
    	if(aux!=null) {
    		aux.setCellNumber(newCellNumber);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
    Verify if the new client has already exist<br>
    <b> pre: </b>Needs ask to the user the necessary parameters to create a client<br>
    <b> post: </b>The user verify if the client´s name or ID is duplicated<br>
    @param name A string who have the new client´s name
    @param id A string who have the new client´s ID
    @param cellNumber A string who have the new client´s cellNumber
    @return temp A string
    */
    public String ClientVeryfier(String name, String id, String cellNumber) {
    	String temp="";
	    	if(clientsPL!=null) {
	    		Client aux=new Client(name, id, cellNumber);
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
    
    
    /**
    Search a specific client by It´s ID <br>
    <b> pre: </b>Needs the specific client were created<br>
    <b> post: </b>The specific client will be founded<br>
    @param id A string who have the specific client´s id
    @return Object client or null
    */
    public Client searchByID(String id) {
    	if(clientsPL!=null) {
    		for(int i=0;i<clientsPL.size();i++) {
    			if(clientsPL.get(i).getId().equalsIgnoreCase(id)) {
    				return clientsPL.get(i);
    			}
    		}
    		return null;
    	}
		return null;
    	
    }
    
    
    /**
    Search a specific client by It´s name <br>
    <b> pre: </b>Needs the specific client were created<br>
    <b> post: </b>The specific client will be founded<br>
    @param name A string who have the specific client´s name
    @return Object client or null
    */
    public Client searchByName(String name) {
    	if(clientsPL!=null) {
    		for(int i=0;i<clientsPL.size();i++) {
    			if(clientsPL.get(i).getName().equalsIgnoreCase(name)) {
    				return clientsPL.get(i);
    			}
    		}
    		return null;
    	}
    	return null;
    }
   
    //Employee method
    /**
    Add a employee to the parking lot <br>
    <b> pre: </b>Needs verify if the employee is already in the parking lot<br>
    <b> post: </b>The employee will be created and added to the parking lot<br>
    @param name A string who have the new employee´s name
    @param id A string who have the new employee´s ID
    @param cellNumber A string who have the new employee´s cellNumber
    @return true or false
    */
    public boolean addEmployee(String name, String id, String username, String password) {
    	String temp=EmployeeVeryfier(name,id,username,password);
    	//Añadir excepción para verificar si el nombre está repetido o si el id está repetido
    	if(temp.contains("name")){
    		return false;
    	}
    	else if(temp.contains("id")) {
    		return false;
    	}
    	else if(temp.contains("username")) {
    		return false;
    	}
    	else if(temp.contains("password")) {
    		return false;
    	}
    	else {
    		Employee aux=new Employee(name, id, username, password);
    		employeesPL.add(aux);
    		return true;
    	}
    }
    
    
    /**
    Disable a specific employee in the parking lot <br>
    <b> pre: </b>Needs verify if the employee already exists in the parking lot<br>
    <b> post: </b>The specific employee will be disabled<br>
    @param id A string who have the employee´s id
    @return true or false
    */
    public boolean disableemployeeByID(String id) {
    	Employee aux=searchEmployeeByID(id);
    	if(aux!=null) {
    		aux.setState(false);
    		return true;
    	}
    	return false;
    }
    
    
    /**
    Disable a specific employee in the parking lot <br>
    <b> pre: </b>Needs verify if the employee already exists in the parking lot<br>
    <b> post: </b>The specific employee will be disabled<br>
    @param id A string who have the employee´s name
    @return true or false
    */
    public boolean disableemployeeByName(String name) {
    	Employee aux=searchEmployeeByName(name);
    	if(aux!=null) {
    		aux.setState(false);
    		return true;
    	}
    	return false;
    }
    
    
    /**
    Update a specific employee's name <br>
    <b> pre: </b>Needs verify if the employee already exists and if his new name is not used by other employee<br>
    <b> post: </b>The specific employee will be updated his name<br>
    @param newName A string who have the employee´s new Name
    @param pastName A string who have the employee´s past Name
    @return true or false
    */
    public boolean updateEmployeeName(String newName, String pastName) {
    	if((searchEmployeeByName(newName)==null)&&(searchEmployeeByName(pastName)!=null)) {
    		searchEmployeeByName(pastName).setName(newName);
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    
    /**
    Update a specific employee's ID<br>
    <b> pre: </b>Needs verify if the employee already exists and if his new ID is not used by other employee<br>
    <b> post: </b>The specific employee will be updated his ID<br>
    @param newID A string who have the employee´s new ID
    @param pastID A string who have the employee´s past ID
    @return true or false
    */
    public boolean updateEmployeeID(String pastID, String newID) {
    	if((searchEmployeeByID(newID)==null)&&(searchEmployeeByID(pastID)!=null)) {
    		searchEmployeeByID(pastID).setId(newID);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    /**
    Update a specific employee's password<br>
    <b> pre: </b>Needs verify if the employee already exists and if his new password is not used by other employee<br>
    <b> post: </b>The specific employee will be updated his password<br>
    @param newpassword A string who have the employee´s new password
    @param username A string who have the employee´s username
    @param id A string who have the employee´s ID
    @return true or false
    */
    public boolean updateEmployeeUsername(String id, String newUsername,String pastUsername, String password) {
    	if(EmployeeVeryfierLogin(pastUsername, password)) {
	    	if((searchEmployeeByID(id)!=null)) {
	    		searchEmployeeByID(id).setUsername(newUsername);
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
    	}
    	return false;
    }
    
    
    /**
    Update a specific employee's password<br>
    <b> pre: </b>Needs verify if the employee already exists and if his new password is not used by other employee<br>
    <b> post: </b>The specific employee will be updated his password<br>
    @param newpassword A string who have the employee´s new password
    @param username A string who have the employee´s username
    @param id A string who have the employee´s ID
    @return true or false
    */
    public boolean updateEmployeePassword(String id, String username, String newPassword) {
    	if(EmployeeVeryfierLogin(username, newPassword)) {
	    	if((searchEmployeeByID(id)!=null)) {
	    		searchEmployeeByID(id).setPassword(newPassword);
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
    	}
    	return false;
    }
    
    /**
    Verify if the new employee name or id is already in use<br>
    <b> pre: </b>Needs ask to the user the necessary parameters to create a employee<br>
    <b> post: </b>The user verify if the employee´s name or ID is duplicated<br>
    @param name A string who have the new employee´s name
    @param id A string who have the new employee´s ID
    @param cellNumber A string who have the new employee´s cellNumber
    @return temp A string
    */
    public String EmployeeVeryfier(String name, String id, String username, String password) {
    	String temp="";
	    	if(employeesPL!=null) {
	    		Employee aux=new Employee(name, id, username, password);
		    	for(int i=0;i<employeesPL.size();i++) {		    		
		    		if (aux.getName().equalsIgnoreCase(employeesPL.get(i).getName())) {
		    			temp+="name";
		    		}
		    		if(aux.getId().equalsIgnoreCase(employeesPL.get(i).getId())) {
		    			temp+="id";
		    		}
		    		if (aux.getUsername().equalsIgnoreCase(employeesPL.get(i).getUsername())) {
		    			temp+="username";
		    		}
		    		if(aux.getPassword().equalsIgnoreCase(employeesPL.get(i).getPassword())) {
		    			temp+="password";
		    		}
		    	}
	    	}
	    	 return temp;
    	}
    
    
    /**
    Verify if the  employee username and password is already in use<br>
    <b> pre: </b>Needs there to be employee created<br>
    <b> post: </b>The user verify if the employee´s user name or password is duplicated<br>
    @param username A string who have the employee´s username
    @param password A string who have the new employee´s password
    @return true or false
    */
    public boolean EmployeeVeryfierLogin(String username, String password) {
	    	if(employeesPL!=null) {
		    	for(int i=0;i<employeesPL.size();i++) {		    		
		    		if (username.equalsIgnoreCase(employeesPL.get(i).getUsername())) {
		    			return false;
		    		}
		    		if(password.equalsIgnoreCase(employeesPL.get(i).getPassword())) {
		    			return false;
		    		}
		    	}
	    	}
	    	 return true;
    	}
    
    
    /**
    Search a specific employee by It´s name <br>
    <b> pre: </b>Needs the specific employee were created<br>
    <b> post: </b>The specific employee will be founded<br>
    @param name A string who have the specific employee´s name
    @return Object employee or null
    */
    public Employee searchEmployeeByName(String name) {
    	if(employeesPL!=null) {
    		for(int i=0;i<employeesPL.size();i++) {
    			if(employeesPL.get(i).getName().equalsIgnoreCase(name)) {
    				return employeesPL.get(i);
    			}
    		}
    		return null;
    	}
		return null;
    }
    
    
    /**
    Search a specific employee by It´s ID <br>
    <b> pre: </b>Needs the specific employee were created<br>
    <b> post: </b>The specific employee will be founded<br>
    @param id A string who have the specific employee´s id
    @return Object employee or null
    */
    public Employee searchEmployeeByID(String id) {
    	if(employeesPL!=null) {
    		for(int i=0;i<employeesPL.size();i++) {
    			if(employeesPL.get(i).getId().equalsIgnoreCase(id)) {
    				return employeesPL.get(i);
    			}
    		}
    		return null;
    	}
		return null;
    	
    }
}
