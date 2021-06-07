package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ParkingLot implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private  ArrayList<Client> clientsPL;
    private ArrayList<Vehicle> vehiclesPL;
    private ArrayList<Employee> employeesPL;
    private ParkingLotMap plMap;
    private BTPerHourOrDaily perHourOrDailyVehicles;
    private BTMonthly monthlyVehicles;
    private Employee root;
    private Employee actualEmployee;
    private boolean firstTime;
    private boolean columnsVeryfier=false;
  

    public ParkingLot(){
        clientsPL = new ArrayList<>();
        vehiclesPL = new ArrayList<>();
        employeesPL=new ArrayList<>();
        plMap = new ParkingLotMap();
        root = new Employee("Admin","12345","admin","1234");
        actualEmployee = root;
        firstTime = true;
    }

    public boolean checkFirstTime(){
    	return firstTime && actualEmployee.equals(root);
	}
    
    //Client methods
    //
    //
    //
    /**
    Add a client to the parking lot <br>
    <b> pre: </b>Needs verify if the clients is already in the parking lot<br>
    <b> post: </b>The client will be created and added to the parking lot<br>
    @param name A string who have the new clientï¿½s name
    @param id A string who have the new clientï¿½s ID
    @param cellNumber A string who have the new clientï¿½s cellNumber
    @return true or false
    */
    public boolean addClient(String name, String id, String cellNumber) {
    	if(searchByName(name)!=null&&searchByName(name).getStatus()==false) {
    		clientsPL.add(new Client(name,id,cellNumber));
    		return true;
    	}
    	else {
    		String temp=ClientVeryfier(name,id, cellNumber);
        	//Aï¿½adir excepciï¿½n para verificar si el nombre estï¿½ repetido o si el id estï¿½ repetido
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
    	
    }
    
    
    /**
    Disable a specific client in the parking lot <br>
    <b> pre: </b>Needs verify if the client already exists and if he does not own a car in the parking lot<br>
    <b> post: </b>The specific client will be disabled<br>
    @param id A string who have the clientï¿½s id
    @return true or false
    */
    public boolean disableClientByID(String id) {
    	Client aux=searchByID(id);
    	if(aux!=null) {
    		aux.setStatus(false);
    		return true;
    	}
    	return false;
    }
    
    //A juan david le gusta el monitor
    /**
    Disable a specific client in the parking lot <br>
    <b> pre: </b>Needs verify if the client already exists and if he does not own a car in the parking lot<br>
    <b> post: </b>The specific client will be disabled<br>
    @param name A string who have the clientï¿½s name
    @return true or false
    */
    public boolean disableClientByName(String name) {
    	Client aux=searchByName(name);
    	if(aux!=null) {
    		aux.setStatus(false);
    		return true;
    	}
    	return false;
    }
    
    
    /**
    Update a specific client's name <br>
    <b> pre: </b>Needs verify if the client already exists and if his new name is not used by other client<br>
    <b> post: </b>The specific client will be updated his name<br>
    @param newName A string who have the clientï¿½s newName
    @param pastName A string who have the clientï¿½s pastName
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
    @param newID A string who have the clientï¿½s new ID
    @param pastID A string who have the clientï¿½s past ID
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
    @param id A string who have the clientï¿½s ID
    @param newCellNumber A string who have the clientï¿½s new cell number
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
    <b> post: </b>The user verify if the clientï¿½s name or ID is duplicated<br>
    @param name A string who have the new clientï¿½s name
    @param id A string who have the new clientï¿½s ID
    @param cellNumber A string who have the new clientï¿½s cellNumber
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
    Search a specific client by Itï¿½s ID <br>
    <b> pre: </b>Needs the specific client were created<br>
    <b> post: </b>The specific client will be founded<br>
    @param id A string who have the specific clientï¿½s id
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
    Search a specific client by Itï¿½s name <br>
    <b> pre: </b>Needs the specific client were created<br>
    <b> post: </b>The specific client will be founded<br>
    @param name A string who have the specific clientï¿½s name
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
   
    //Employee methods
    //
    //
    //
    
    /**
    Add a employee to the parking lot <br>
    <b> pre: </b>Needs verify if the employee is already in the parking lot<br>
    <b> post: </b>The employee will be created and added to the parking lot<br>
    @param name A string who have the new employeeï¿½s name
    @param id A string who have the new employeeï¿½s ID
    @param username A string who have the new employeeï¿½s username
	@param password  A string who have the new employeeï¿½s password
    @return true or false
    */
    public boolean addEmployee(String name, String id, String username, String password) {
    	if(searchEmployeeByName(name)!=null&&searchEmployeeByName(name).getState()==false) {
    		for(int i=0;i<employeesPL.size();i++) {
    			if(employeesPL.get(i).getId().equalsIgnoreCase(searchEmployeeByName(name).getId())) {
    				employeesPL.remove(i);
    				employeesPL.add(new Employee(name, id, username, password));
    			}
    		}
    	}
    	String temp=employeeVeryfier(name,id,username,password);
    	//Añadir excepciï¿½n para verificar si el nombre estï¿½ repetido o si el id estï¿½ repetido
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
    Disable a specific employee in the parking lot searching by It's ID <br>
    <b> pre: </b>Needs verify if the employee already exists in the parking lot<br>
    <b> post: </b>The specific employee will be disabled<br>
    @param id A string who have the employeeï¿½s id
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
    Disable a specific employee in the parking lot searching by It's name <br>
    <b> pre: </b>Needs verify if the employee already exists in the parking lot<br>
    <b> post: </b>The specific employee will be disabled<br>
    @param name A string who have the employeeï¿½s name
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
    @param newName A string who have the employeeï¿½s new Name
    @param pastName A string who have the employeeï¿½s past Name
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
    @param newID A string who have the employeeï¿½s new ID
    @param pastID A string who have the employeeï¿½s past ID
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
	 @param id A string who have the employeeï¿½s ID
	 @param newUsername A string who have the employeeï¿½s new username
	 @param pastUsername A string who have the employee's past username
    @param password A string who have the employeeï¿½s password
    @return true or false
    */
    public boolean updateEmployeeUsername(String id, String newUsername,String pastUsername, String password) {
    	if(employeeVeryfierLogin(pastUsername, password)) {
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
	 @param id A string who have the employeeï¿½s ID
	 @param username A string who have the employeeï¿½s username
	 @param password A string who have the employeeï¿½s past password
    @param newPassword A string who have the employeeï¿½s new password
    @return true or false
    */
    public boolean updateEmployeePassword(String id, String username,String password, String newPassword) {
    	if(employeeVeryfierLogin(username, password)) {
    		if(!(employeeVeryfierLogin("x", newPassword))) {
		    	if((searchEmployeeByID(id)!=null)) {
		    		searchEmployeeByID(id).setPassword(newPassword);
		    		return true;
		    	}
    		}
    	}
    	return false;
    }
    
    /**
    Verify if the new employee's name, id, username or password is already in use<br>
    <b> pre: </b>Needs ask to the user the necessary parameters to create a employee<br>
    <b> post: </b>The user verify if the employeeï¿½s name or ID is duplicated<br>
    @param name A string who have the new employeeï¿½s name
    @param id A string who have the new employeeï¿½s ID
    @param password A string who have the new employeeï¿½s password
    @param username A string who have the new employee's username
    @return temp A string
    */
    public String employeeVeryfier(String name, String id, String username, String password) {
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
    Verify if the employee username and password is already in use<br>
    <b> pre: </b>Needs there to be employee created<br>
    <b> post: </b>The user verify if the employeeï¿½s user name or password is duplicated<br>
    @param username A string who have the employeeï¿½s username
    @param password A string who have the new employeeï¿½s password
    @return true or false
    */
    public boolean employeeVeryfierLogin(String username, String password) {
    	boolean temp=false;
	    	if(employeesPL!=null) {
		    	for(int i=0;i<employeesPL.size();i++) {		    		
		    		if (username.equalsIgnoreCase(employeesPL.get(i).getUsername())) {
		    			temp= true;
		    		}
		    		if(password.equalsIgnoreCase(employeesPL.get(i).getPassword())) {
		    			temp= true;
		    		}
		    	}
	    	}
	    	 return temp;
    	}
    
    
    /**
    Search a specific employee by Itï¿½s name <br>
    <b> pre: </b>Needs the specific employee were created<br>
    <b> post: </b>The specific employee will be founded<br>
    @param name A string who have the specific employeeï¿½s name
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
    Search a specific employee by Itï¿½s ID <br>
    <b> pre: </b>Needs the specific employee were created<br>
    <b> post: </b>The specific employee will be founded<br>
    @param id A string who have the specific employeeï¿½s id
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

    public ParkingLotMap getPlMap(){
    	return plMap;
	}

    
    //Manage vehicles
    
    /**
    Add a vehicle to the vehicle's ArrayList creating it by It's specific type <br>
    <b> pre: </b>Needs there is no other vehicle with the same specifications<br>
    <b> post: </b>The specific vehicle will be created and added<br>
    @param typeIndicator int whose information stipulate the type of the vehicle
    @param model String with the vehicleï¿½s model
    @param licensePlate String with the vehicle's plate information
    @param color String with the vehicle's color
    @param owner The object client with the owner's information
    @param spot int with the vehicle's position at the parking lot
    @param observations String with extra information about the vehicle
    @param stayIndicator int with the indicator for the StayTime enum
    @param numberOfTime int with the amount of time that vehicle will spend in the PL
    @return true or false
    */
    public boolean addVehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, int stayIndicator, int numberOfTime) {
    	boolean check = false;
    	Vehicle toAdd;
    	if(verifyVehicleByPlate(licensePlate)) {

		    	switch(typeIndicator) {
		    	case 0:
		    		toAdd=new SmallVehicle(typeIndicator, model, licensePlate, color, owner, spot, stayIndicator, numberOfTime);
		    		for(int i=0;i<toAdd.getAvailablespots().length;i++) {
		    			if(toAdd.getSpot()==toAdd.getAvailablespots()[i]) {
		    				vehiclesPL.add(toAdd);
				    		check = true;
		    			}
		    		}
		    		if(check==false) {
		    			//EXCEPTION
		    		}
		    		
		    		break;
		    	case 1:
		    		toAdd=new Motorcycle(typeIndicator, model, licensePlate, color, owner, spot, stayIndicator, numberOfTime);
		    		for(int i=0;i<toAdd.getAvailablespots().length;i++) {
		    			if(toAdd.getSpot()==toAdd.getAvailablespots()[i]) {
		    				vehiclesPL.add(toAdd);
				    		check = true;
		    			}
		    		}
		    		if(check==false) {
		    			//EXCEPTION
		    		}
		  			break;
		    	case 2:
					case 3:
					case 4:
					case 5:
						toAdd=new LargeVehicle(typeIndicator, model, licensePlate, color, owner, spot, stayIndicator, numberOfTime);
						for(int i=0;i<toAdd.getAvailablespots().length;i++) {
			    			if(toAdd.getSpot()==toAdd.getAvailablespots()[i]) {
			    				vehiclesPL.add(toAdd);
					    		check = true;
			    			}
			    		}
						if(check==false) {
			    			//EXCEPTION
			    		}
		    			break;
					default: return false;
    		}
    		switch(stayIndicator){
				case 0:
				case 1:
					addAVehicleToPerHourOrDailyVehicles(toAdd);
					break;
				case 2:
					addAVehicleToMonthlyVehicles(toAdd);
					break;
				default:
			}
    	}
    	return check;
    }
    
    //Binary trees begin
    //
    //
    
    
    /**
	   Add a vehicle to the per hour or daily binary tree  <br>
	   <b> pre: </b>The vehicle was already created<br>
	   <b> post: </b>Add a vehicle to the binary tree<br>
	   @param newVehicle Vehicle who is go to be added to the binary tree
	   */
	public void addAVehicleToPerHourOrDailyVehicles(Vehicle newVehicle){
    	BTPerHourOrDaily newBt = new BTPerHourOrDaily();
		newBt.setBtVehicle(newVehicle);
		perHourOrDailyVehicles = addAVehicleToPerHourOrDailyVehicles(perHourOrDailyVehicles,newBt);
	}
	
	
	/**
	   Find a vehicle to the per hour or daily binary tree  <br>
	   <b> pre: </b><br>
	   <b> post: </b>Add a vehicle to the binary tree<br>
	   @param newBT A node of the binary tree per hour or daily
	   @param r A node of the binary tree per hour or daily
	   @return r node of the binary tree per hour or daily
	   */
	private BTPerHourOrDaily addAVehicleToPerHourOrDailyVehicles(BTPerHourOrDaily r,BTPerHourOrDaily newBT){
		if (r == null) {
			r = newBT;
			return r;
		}
		if ((newBT.getBtVehicle().compareTo(r.getBtVehicle())) <= 0) //no estoy seguro
			r.setLeft(addAVehicleToPerHourOrDailyVehicles((BTPerHourOrDaily) r.getLeft(),newBT));
		else if ((newBT.getBtVehicle().compareTo(r.getBtVehicle())) > 0)
			r.setRight(addAVehicleToPerHourOrDailyVehicles((BTPerHourOrDaily) r.getRight(),newBT));
		return r;
	}
	
	
	/**
	   Add a vehicle to the month binary tree  <br>
	   <b> pre: </b>The vehicle was already created<br>
	   <b> post: </b>Add a vehicle to the binary tree<br>
	   @param newVehicle Vehicle who is go to be added to the binary tree
	   */
	public void addAVehicleToMonthlyVehicles(Vehicle newVehicle){
		BTMonthly newBt = new BTMonthly();
		newBt.setBtVehicle(newVehicle);
		monthlyVehicles = addAVehicleToMonthlyVehicles(monthlyVehicles,newBt);
	}
	
	
	/**
	   Fin a vehicle to the month binary tree  <br>
	   <b> pre: </b><br>
	   <b> post: </b>Add a vehicle to the binary tree<br>
	   @param newBT A node of the binary tree monthly
	   @param r A node of the binary tree monthly
	   @return r node of the binary tree monthly
	   */
	private BTMonthly addAVehicleToMonthlyVehicles(BTMonthly r,BTMonthly newBT){
		if (r == null) {
			r = newBT;
			return r;
		}
		if ((newBT.getBtVehicle().compareTo(r.getBtVehicle())) <= 0) //no estoy seguro
			r.setLeft(addAVehicleToMonthlyVehicles((BTMonthly) r.getLeft(),newBT));
		else if ((newBT.getBtVehicle().compareTo(r.getBtVehicle())) > 0)
			r.setRight(addAVehicleToMonthlyVehicles((BTMonthly) r.getRight(),newBT));
		return r;
	}
    //Binary trees finish
    
    /**
    Disable a vehicle searching it by It's license plate <br>
    <b> pre: </b>Needs that the vehicle is already created<br>
    <b> post: </b>Disable the vehicle<br>
    @param plate A string who have the specific vehicleï¿½s license plate
    @return true or false
    */
    public boolean disableVehicleByPlate(String plate) {
    	for(int i=0;i<vehiclesPL.size();i++) {
    		if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
    			vehiclesPL.get(i).setEnabled(false);
    			return true;
    		}
    	}
    	return false;
    }
    
    
    /**
    Update a vehicle's model searching it by It's license plate <br>
    <b> pre: </b>Needs that the vehicle is already created<br>
    <b> post: </b>Update the model<br>
    @param plate A string who have the specific vehicleï¿½s license plate
    @param newModel A string who have the new model
    @return true or false
    */
    public boolean updateVehicleModel(String plate, String newModel) {
    	for(int i=0;i<vehiclesPL.size();i++) {
    		if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
    			vehiclesPL.get(i).setModel(newModel);
    			return true;
    		}
    	}
    	return false;
    }
    
    
    /**
    Update a vehicle's color searching it by It's license plate <br>
    <b> pre: </b>Needs that the vehicle is already created<br>
    <b> post: </b>Update the color<br>
    @param plate A string who have the specific vehicleï¿½s license plate
    @param newColor A string who have the new color
    @return true or false
    */
    public boolean updateVehicleColor(String plate, String newColor) {
    	for(int i=0;i<vehiclesPL.size();i++) {
    		if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
    			vehiclesPL.get(i).setColor(newColor);
    			return true;
    		}
    	}
    	return false;
    }
    
    
    /**
    Update the spot in which is placed the vehicle <br>
    <b> pre: </b>Needs the vehicle is already created<br>
    <b> post: </b>Change the spot<br>
    @param newSpot int with the new place number
    @param plate String with the license plate information
    @return true or false
    */
    public boolean updateSpot(String plate,int newSpot) {
    	if(verifySpot(newSpot)) {
    		for(int i=0;i<vehiclesPL.size();i++) {
    			if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
        			vehiclesPL.get(i).setSpot(newSpot);
        			return true;
        		}
    		}
    	}
    	return false;
    }
    
    
   
    
    
    /**
    Verify if the new spot is already in use <br>
    <b> pre: </b><br>
    <b> post: </b>Check if the place is vacate<br>
    @param spot int with the place's number
    @return true or false
    */
    public boolean verifySpot(int spot) {
    	for(int i=0;i<vehiclesPL.size();i++) {
	    	if(vehiclesPL.get(i).isEnabled()) {
				    	if(vehiclesPL.get(i).getSpot()==spot) {
							return false;
							}
		    		}
    	}
    	return true;
    }
    
    
    /**
    Verify if the new vehicle is already created <br>
    <b> pre: </b><br>
    <b> post: </b>Verify is the vehicle can be created<br>
    @param plate A string who have the specific vehicleï¿½s license plate
    @return true or false
    */
    public boolean verifyVehicleByPlate(String plate) {
    	for(int i=0;i<vehiclesPL.size();i++) {
    		if(vehiclesPL.get(i).isEnabled()) {
	    		if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
	    			return false;
	    		}
    		}
    	}
    	return true;
    }
    
    
    //Sorting algorithms
    //
    //
    
    
    /**
    Use the bubble sort to organize the clients by name<br>
    <b> pre: </b>There had to be an array list to sort<br>
    <b> post: </b>Sort the list<br> 
    */
    public void clientBubbleSortName() {
    	
		for(int i=1; i<clientsPL.size();i++) {
			
			for(int j=0;j<clientsPL.size()-i;j++) {
				
				if((clientsPL.get(j).compareTo(clientsPL.get(j+1))>0)) {
					
						Client temp = clientsPL.get(j);
						//array[j] = array[j+1];
						clientsPL.set(j,clientsPL.get(j+1));
						clientsPL.set(j+1, temp);
					
					}
				}
			}
    	}
    
    
    /**
    Use the bubble sort to organize the clients by Id <br>
    <b> pre: </b>There had to be an array list to sort<br>
    <b> post: </b>Sort the list<br>
    */
	public void clientBubbleSortByID() {
	    	
			for(int i=1; i<clientsPL.size();i++) {
				
				for(int j=0;j<clientsPL.size()-i;j++) {
					
					if((clientsPL.get(j).getId().compareTo(clientsPL.get(j+1).getId())>0)) {
						
							Client temp = clientsPL.get(j);
							//array[j] = array[j+1];
							clientsPL.set(j,clientsPL.get(j+1));
							clientsPL.set(j+1, temp);
						
						}
					}
				}
	    	}
	    
    
	
	/**
    Use the insertion sort to organize the vehicles by license plate <br>
    <b> pre: </b>There had to be an array list to sort<br>
    <b> post: </b>Sort the list<br>
    */
   public void vehicleInsertionSortByPlate(){ //Client pero llama vehicle xd//Muerete es que estaba probandolos
	   int i;
		int j;
		Vehicle aux;
		for (i = 1;i <vehiclesPL.size(); i++){
			aux = vehiclesPL.get(i);
			j = i - 1;
			while ((j >= 0) && (aux.compareTo(vehiclesPL.get(j))<0)){
				vehiclesPL.set(j+1,vehiclesPL.get(j));
				j--;
			}
			vehiclesPL.set(j+1, aux);
		}
	}
   
   
   /**
   Use the insertion sort to organize the vehicles by owner's name <br>
   <b> pre: </b>There had to be an array list to sort<br>
   <b> post: </b>Sort the list<br>
   */
   public void vehicleInsertionSortByOwner(){ //Client pero llama vehicle xd//Muerete es que estaba probandolos
	   int i;
		int j;
		Vehicle aux;
		for (i = 1;i <vehiclesPL.size(); i++){
			aux = vehiclesPL.get(i);
			j = i - 1;
			while ((j >= 0) && (aux.getOwner().getName().compareTo((vehiclesPL.get(j).getOwner().getName()))<0)){
				vehiclesPL.set(j+1,vehiclesPL.get(j));
				j--;
			}
			vehiclesPL.set(j+1, aux);
		}
	}
   
   
   /**
   Use the selection sort to organize the employees by username <br>
   <b> pre: </b>There had to be an array list to sort<br>
   <b> post: </b>Sort the list<br>
   */
   public void employeeselectionSortByUsername() {
	   if(employeesPL.size()>2) {
		for(int i=0;i<employeesPL.size();i++) {
			
		Employee min=employeesPL.get(0);
		for(int j=i+1;i<employeesPL.size();j++) {
			if(employeesPL.get(j).compareTo(min)<0) {
				Employee temp=employeesPL.get(j);
				employeesPL.set(j, min);
				min=temp;
				}
			}
		employeesPL.set(i, min);
		}
	   }
	}
   
   
   /**
   Use the selection sort to organize the employees by id <br>
   <b> pre: </b>There had to be an array list to sort<br>
   <b> post: </b>Sort the list<br>
   */
   public void employeeselectionSortById() {
	   if(employeesPL.size()>2) {
		for(int i=0;i<employeesPL.size();i++) {
			
		Employee min=employeesPL.get(0);
		for(int j=i+1;i<employeesPL.size();j++) {
			if(employeesPL.get(j).getId().compareTo(min.getId())<0) {
				Employee temp=employeesPL.get(j);
				employeesPL.set(j, min);
				min=temp;
				}
			}
		employeesPL.set(i, min);
		}
	   }
	}
   
   /**
   Use the class comparator to organize the employees by name <br>
   <b> pre: </b>There had to be an array list to sort<br>
   <b> post: </b>Sort the list<br> 
   */
   public void sortEmployeeByName() {   //Note: This method must be used because when the first or last name is modified, the order of these in the arrayList is changed.
		Comparator<Employee> nComparator = new employeeComparator();
		Collections.sort(employeesPL,nComparator);
	}
   
   
   /**
   Use the class comparator to organize the vehicles by model <br>
   <b> pre: </b>There had to be an array list to sort<br>
   <b> post: </b>Sort the list<br>
   */
   public void sortVehicleByModel() {   //Note: This method must be used because when the first or last name is modified, the order of these in the arrayList is changed.
		Comparator<Vehicle> nComparator = new vehicleComparator();
		Collections.sort(vehiclesPL,nComparator);
	}
   
   
   //Binary search
   //
   //
   
   
   /**
   Use the binary search to find a person <br>
   <b> pre: </b>There had to be an array list to search in<br>
   <b> post: </b>Give the position of the person<br>
   @param aL Array list generic with the list to search in
   @param searchName String with the name to search
   @return Pos int with the position
   */
   public int binarySearchPerson(ArrayList<?> aL,String searchName) {
		int pos = -1;
		int i = 0;
		int j = aL.size() - 1;

		while(i<=j && pos<0) {
			int m = (i + j)/2;
			String objectName= ((Person) aL.get(m)).getName();
			String name = searchName;
			if(objectName.equals(name)) {
				pos = m;
			}
			else if((objectName.compareTo(name)) > 0){
				j = m - 1;
			}
			else {
				i = m + 1;
			}
		}
		return pos;
	}
   
   
   /**
   Use the binary search to find a vehicle by It's license plate <br>
   <b> pre: </b>There had to be an array list to search in<br>
   <b> post: </b>Give the position of the vehicle<br>
   @param aL Array list generic with the list to search in
   @param searchPlate String with the license plate to search
   @return Pos int with the position
   */
   public int binarySearchVehicle(ArrayList<?> aL,String searchPlate) {
		int pos = -1;
		int i = 0;
		int j = aL.size() - 1;

		while(i<=j && pos<0) {
			int m = (i + j)/2;
			String objectName= ((Vehicle) aL.get(m)).getLicensePlate();
			String plate = searchPlate;
			if(objectName.equals(plate)) {
				pos = m;
			}
			else if((objectName.compareTo(plate)) > 0){
				j = m - 1;
			}
			else {
				i = m + 1;
			}
		}
		return pos;
	}
   
   //Login
   //
   //
   
   
   /**
   Verify the login <br>
   <b> pre: </b><br>
   <b> post: </b>Give the case of the login<br>
   @param password String with the employee password
   @param username String with the employee user name 
   @return int with the login case
   */
   public int login(String username,String password) {
		int index = -1; //Not found
		boolean band = false;
		for(int i = 0; i < employeesPL.size() && !band; i++) {
			if((employeesPL.get(i).getUsername().equals(username)) && (employeesPL.get(i).getPassword().equals(password))) {
				index = i;
				band = true;
			}
		}
		if (root.getUsername().equals(username) && root.getPassword().equals(password)) {
			index = -2; // The user is the rootUser
		}
		return index;
	}
   
   //Reports
   //
   //
   
   
   
   /**
   Generate a csv about the client's information <br>
   <b> pre: </b>Needs a file in which write the information<br>
   <b> post: </b>Generate the csv<br>
   @param startDate LocalDateTime with the begin date 
   @param endDate LocalDateTime with the end date 
   */
   public void generateClientsReport(LocalDateTime startDate,LocalDateTime endDate) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter("data/reports/clientReport.csv");
		String separator=";";
		clientBubbleSortName();
		String columns = "Nombre y apellidos" + separator + "Identificación" + separator + "Telefono del cliente";
		pw.println(columns);
		 for(int i = 0; i < clientsPL.size(); i++) {
			 LocalDateTime dateOfClient=clientsPL.get(i).getEntryDate();
			 if(dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
		    		pw.println(clientsPL.get(i).showInformation());
		    	}
			 
		 }
		pw.close();
	  }
   
   
   
   /**
   Generate a csv about the vehicle's general information <br>
   <b> pre: </b>Needs a file in which write the information<br>
   <b> post: </b>Generate the csv<br>
   @param startDate LocalDateTime with the begin date 
   @param endDate LocalDateTime with the end date 
   */
   public void generateVehiclesReport(LocalDateTime startDate,LocalDateTime endDate) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter("data/reports/vehicleReport.csv");
		String separator=";";
		vehicleInsertionSortByPlate();
		String columns = "Tipo de vehiculo" + separator + "Modelo" + separator + "Matricula"+separator+"Color"+ separator 
		+"Nombre de propietario"+ separator +"Identificación propietario"+ separator +"Fecha de entrada"+ separator +"Fecha de salida"+"Valor a pagar";
		pw.println(columns);
		 for(int i = 0; i < vehiclesPL.size(); i++) {
			 LocalDateTime dateOfClient=vehiclesPL.get(i).getEntryDate();
			 if(dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
		    		pw.println(vehiclesPL.get(i).showInformation());
		    	}
			 
		 }
		pw.close();
	  }
   
   
   /**
   Generate a csv about the vehicle's monthly information <br>
   <b> pre: </b>Needs a file in which write the information<br>
   <b> post: </b>Generate the csv<br>
   @param monthlyVehicles the node of the binary tree
   @param startDate LocalDateTime with the begin date 
   @param endDate LocalDateTime with the end date 
   */
   public void reportInfoMonthly(BTMonthly monthlyVehicles,LocalDateTime startDate,LocalDateTime endDate) throws FileNotFoundException {
	   columnsVeryfier=false;
	   reportInfoMonthly(monthlyVehicles, startDate, endDate, columnsVeryfier);
   }
   
   
   /**
   Generate a csv about the vehicle's monthly information <br>
   <b> pre: </b>Needs a file in which write the information<br>
   <b> post: </b>Generate the csv<br>
   @param monthlyVehicles the node of the binary tree
   @param startDate LocalDateTime with the begin date 
   @param endDate LocalDateTime with the end date 
   */
   private void reportInfoMonthly(BTMonthly monthlyVehicles,LocalDateTime startDate,LocalDateTime endDate, boolean columnsVeyfier) throws FileNotFoundException{
	   PrintWriter pw = new PrintWriter("data/reports/vehicleMonthlyReport.csv");
			   if(columnsVeryfier==false) {
					   String separator=";";
						String columns = "Tipo de vehiculo" + separator + "Modelo" + separator + "Matricula"+separator+"Color"+ separator 
						+"Nombre de propietario"+ separator +"Identificación propietario"+ separator +"Fecha de entrada"+ separator +"Fecha de salida";
						pw.println(columns);
						columnsVeryfier=true;
			   }
	   String temp="";
				if (monthlyVehicles != null) {
					reportInfoMonthly((BTMonthly) monthlyVehicles.getLeft(), startDate, endDate, columnsVeryfier);
					LocalDateTime dateOfClient=monthlyVehicles.getBtVehicle().getEntryDate();
							if(dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
									temp=monthlyVehicles.getBtVehicle().getLicensePlate()+";"+monthlyVehicles.getBtVehicle().getModel()+";"
										+monthlyVehicles.getBtVehicle().getOwner().getName()
										+";"+monthlyVehicles.getBtVehicle().getOwner().getCellNumber()+";"+monthlyVehicles.getBtVehicle().getValueToPay()
										+"\n";
									pw.println(temp);
									reportInfoMonthly((BTMonthly)monthlyVehicles.getRight(), startDate, endDate, columnsVeryfier);
							}
					}
		pw.close();
		
	}
   
   
   /**
   Generate a csv about the vehicle's per hour or daily information <br>
   <b> pre: </b>Needs a file in which write the information<br>
   <b> post: </b>Generate the csv<br>
   @param perHourOrDailyVehicles the node of the binary tree
   @param startDate LocalDateTime with the begin date 
   @param endDate LocalDateTime with the end date 
   */
   public void reportInfoDaily(BTPerHourOrDaily perHourOrDailyVehicles,LocalDateTime startDate,LocalDateTime endDate) throws FileNotFoundException {
	   columnsVeryfier=false;
	   reportInfoDaily(perHourOrDailyVehicles, startDate, endDate, columnsVeryfier);
   }
   
   
   /**
   Generate a csv about the vehicle's per hour or daily information <br>
   <b> pre: </b>Needs a file in which write the information<br>
   <b> post: </b>Generate the csv<br>
   @param perHourOrDailyVehicles the node of the binary tree
   @param startDate LocalDateTime with the begin date 
   @param endDate LocalDateTime with the end date 
   */
   private void reportInfoDaily(BTPerHourOrDaily perHourOrDailyVehicles,LocalDateTime startDate,LocalDateTime endDate, boolean columnsVeryfier) throws FileNotFoundException{
	   PrintWriter pw = new PrintWriter("data/reports/vehiclePerHourOrDailyReport.csv");
	   if(columnsVeryfier==false) {
		   String separator=";";
			String columns = "Tipo de vehiculo" + separator + "Modelo" + separator + "Matricula"+separator+"Color"+ separator 
			+"Nombre de propietario"+ separator +"Identificación propietario"+ separator +"Fecha de entrada"+ separator +"Fecha de salida";
			pw.println(columns);
			columnsVeryfier=true;
		   }
	   String temp="";
		if (perHourOrDailyVehicles != null) {
				reportInfoDaily((BTPerHourOrDaily) perHourOrDailyVehicles.getLeft(), startDate, endDate, columnsVeryfier);
				LocalDateTime dateOfClient=perHourOrDailyVehicles.getBtVehicle().getEntryDate();
				if(dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
							temp=perHourOrDailyVehicles.getBtVehicle().getLicensePlate()+";"
							+perHourOrDailyVehicles.getBtVehicle().getModel()+";"+perHourOrDailyVehicles.getBtVehicle().getOwner().getName()
							+";"+perHourOrDailyVehicles.getBtVehicle().getOwner().getCellNumber()+";"+perHourOrDailyVehicles.getBtVehicle().getValueToPay()
							+"\n";
						pw.println(temp);
						reportInfoDaily((BTPerHourOrDaily)perHourOrDailyVehicles.getRight(), startDate, endDate,columnsVeryfier);
				}
		}
		pw.close();
	}
   
   //jd gei
   //Getters y setters
   //
   //

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public ArrayList<Client> getClientsPL() {
		return clientsPL;
	}

	public void setClientsPL(ArrayList<Client> clientsPL) {
		this.clientsPL = clientsPL;
	}

	public ArrayList<Vehicle> getVehiclesPL() {
		return vehiclesPL;
	}

	public void setVehiclesPL(ArrayList<Vehicle> vehiclesPL) {
		this.vehiclesPL = vehiclesPL;
	}

	public ArrayList<Employee> getEmployeesPL() {
		return employeesPL;
	}

	public void setEmployeesPL(ArrayList<Employee> employeesPL) {
		this.employeesPL = employeesPL;
	}

	public void setPlMap(ParkingLotMap plMap) {
		this.plMap = plMap;
	}

	public BTPerHourOrDaily getPerHourOrDailyVehicles() {
		return perHourOrDailyVehicles;
	}
	//aias

	public void setPerHourOrDailyVehicles(BTPerHourOrDaily perHourOrDailyVehicles) {
		this.perHourOrDailyVehicles = perHourOrDailyVehicles;
	}

	public BTMonthly getMonthlyVehicles() {
		return monthlyVehicles;
	}

	public void setMonthlyVehicles(BTMonthly monthlyVehicles) {
		this.monthlyVehicles = monthlyVehicles;
	}

	public Employee getRoot() {
		return root;
	}

	public void setRoot(Employee root) {
		this.root = root;
	}

	public Employee getActualEmployee() {
		return actualEmployee;
	}

	public void setActualEmployee(Employee actualEmployee) {
		this.actualEmployee = actualEmployee;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	public boolean getColumnsVeryfier() {
		return columnsVeryfier;
	}

	public void setColumnsVeryfier(boolean columnsVeryfier) {
		this.columnsVeryfier = columnsVeryfier;
	}
}
