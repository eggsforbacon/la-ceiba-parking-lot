package model;

import exceptions.*;

import java.io.*;
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
    private boolean columnsVeryfier;
	private ArrayList<Client> searchClientResults;
	private ArrayList<Employee> searchEmployeeResults;
	private ArrayList<Vehicle> searchVehicleResults;
	private ArrayList<Vehicle> perHODVehiclesPL;
	private ArrayList<Vehicle> monthlyVehiclesPL;

  

    public ParkingLot(){
        clientsPL = new ArrayList<>();
        vehiclesPL = new ArrayList<>();
        employeesPL=new ArrayList<>();
        perHODVehiclesPL=new ArrayList<>();
        monthlyVehiclesPL=new ArrayList<>();
        plMap = new ParkingLotMap();
        root = new Employee("Admin","12345","admin","1234");
        actualEmployee = root;
        firstTime = true;
		columnsVeryfier=false;
		searchClientResults = new ArrayList<>();
		searchEmployeeResults = new ArrayList<>();
		searchVehicleResults = new ArrayList<>();
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
    @param name A string who have the new client�s name
    @param id A string who have the new client�s ID
    @param cellNumber A string who have the new client�s cellNumber
    @return true or false
     * @throws NameAlreadyInUseException 
     * @throws IDAlreadyInUseException 
    */
    public boolean addClient(String name, String id, String cellNumber) throws IDAlreadyInUseException {
    	if(searchByName(name)!=null&&searchByName(name).isEnabled()==false) {
    		clientsPL.add(new Client(name,id,cellNumber));
    		return true;
    	}
    	else {
    		String temp=ClientVeryfier(name,id, cellNumber);
        	//A�adir excepci�n para verificar si el nombre est� repetido o si el id est� repetido
        	if(temp.contains("id")) {
        		throw new IDAlreadyInUseException();        	}
        	else {
        		Client aux=new Client(name, id, cellNumber);
        		clientsPL.add(aux);
        		return true;
        	}
    	}
    	
    }

    public void removeLastClient(){
    	clientsPL.remove(clientsPL.size()-1);
	}
    
    
    /**
    Disable a specific client in the parking lot <br>
    <b> pre: </b>Needs verify if the client already exists and if he does not own a car in the parking lot<br>
    <b> post: </b>The specific client will be disabled<br>
    @param id A string who have the client�s id
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
    @param name A string who have the client�s name
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
    @param newName A string who have the client�s newName
    @param id A string who have the clients id
    @return true or false
    */
    public boolean updateClientName(String id, String newName) {
    	if((searchByID(id)!=null)&&(searchByName(newName)==null)) {
    		searchByID(id).setName(newName);
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
    @param newID A string who have the client�s new ID
    @param pastID A string who have the client�s past ID
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
    @param id A string who have the client�s ID
    @param newCellNumber A string who have the client�s new cell number
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
    <b> post: </b>The user verify if the client�s name or ID is duplicated<br>
    @param name A string who have the new client�s name
    @param id A string who have the new client�s ID
    @param cellNumber A string who have the new client�s cellNumber
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
    Search a specific client by It�s ID <br>
    <b> pre: </b>Needs the specific client were created<br>
    <b> post: </b>The specific client will be founded<br>
    @param id A string who have the specific client�s id
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
    Search a specific client by It�s name <br>
    <b> pre: </b>Needs the specific client were created<br>
    <b> post: </b>The specific client will be founded<br>
    @param name A string who have the specific client�s name
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
    @param name A string who have the new employee�s name
    @param id A string who have the new employee�s ID
    @param username A string who have the new employee�s username
	@param password  A string who have the new employee�s password
    @return true or false
     * @throws NameAlreadyInUseException 
     * @throws IDAlreadyInUseException 
     * @throws UsernameAlreadyInUseException 
     * @throws PasswordAlreadyInUseException 
    */
    public boolean addEmployee(String name, String id, String username, String password) throws IDAlreadyInUseException, UsernameAlreadyInUseException{
    	if(searchEmployeeByName(name)!=null&&searchEmployeeByName(name).getState()==false) {
    		for(int i=0;i<employeesPL.size();i++) {
    			if(employeesPL.get(i).getId().equalsIgnoreCase(searchEmployeeByName(name).getId())) {
    				employeesPL.remove(i);
    				employeesPL.add(new Employee(name, id, username, password));
    			}
    		}
    	}
    	String temp=employeeVeryfier(name,id,username,password);
    	//A�adir excepci�n para verificar si el nombre est� repetido o si el id est� repetido
    	if(temp.contains("id")) {
    		throw new IDAlreadyInUseException();
    	}
    	else if(temp.contains("username")) {
    		throw new UsernameAlreadyInUseException();
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
    @param id A string who have the employee�s id
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
    @param name A string who have the employee�s name
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
    @param newName A string who have the employee�s new Name
    @param id A string who have the employees id
    @return true or false
    */
    public boolean updateEmployeeName(String id, String newName) {
    	if((searchEmployeeByID(id)!=null)&&(searchEmployeeByName(newName)==null)) {
    		searchEmployeeByID(id).setName(newName);
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
    @param newID A string who have the employee�s new ID
    @param pastID A string who have the employee�s past ID
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
	 @param id A string who have the employee�s ID
	 @param newUsername A string who have the employee�s new username
    @return true or false
    */
    public boolean updateEmployeeUsername(String id, String newUsername) {
		if(searchEmployeeByID(id)!=null) {
			searchEmployeeByID(id).setUsername(newUsername);
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
	 @param id A string who have the employee�s ID
    @param newPassword A string who have the employee�s new password
    @return true or false
    */
    public boolean updateEmployeePassword(String id,String newPassword) {
    	if(searchEmployeeByID(id)!=null) {
			searchEmployeeByID(id).setPassword(newPassword);
			return true;
		}
		else {
			return false;
		}
    }
    
    /**
    Verify if the new employee's name, id, username or password is already in use<br>
    <b> pre: </b>Needs ask to the user the necessary parameters to create a employee<br>
    <b> post: </b>The user verify if the employee�s name or ID is duplicated<br>
    @param name A string who have the new employee�s name
    @param id A string who have the new employee�s ID
    @param password A string who have the new employee�s password
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
    <b> post: </b>The user verify if the employee�s user name or password is duplicated<br>
    @param username A string who have the employee�s username
    @param password A string who have the new employee�s password
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
    Search a specific employee by It�s name <br>
    <b> pre: </b>Needs the specific employee were created<br>
    <b> post: </b>The specific employee will be founded<br>
    @param name A string who have the specific employee�s name
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
    Search a specific employee by It�s ID <br>
    <b> pre: </b>Needs the specific employee were created<br>
    <b> post: </b>The specific employee will be founded<br>
    @param id A string who have the specific employee�s id
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
    @param model String with the vehicle�s model
    @param licensePlate String with the vehicle's plate information
    @param color String with the vehicle's color
    @param owner The object client with the owner's information
    @param spot int with the vehicle's position at the parking lot
    @param stayIndicator int with the indicator for the StayTime enum
    @param numberOfTime int with the amount of time that vehicle will spend in the PL
    @return true or false
     * @throws NotAllowedException 
    */
    public boolean addVehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, int stayIndicator, int numberOfTime) throws NotAllowedException {
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
		    			throw new NotAllowedException();
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
		    			throw new NotAllowedException();
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
							throw new NotAllowedException();
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
	
	/* public void initList(){
		 initPerHODList(perHODVehiclesPL,perHourOrDailyVehicles);
		 initMontlyList(monthlyVehiclesPL,monthlyVehicles);
	 }
	 
	 private ArrayList<Vehicle> initPerHODList(ArrayList<Vehicle> a, BTPerHourOrDaily r){
	    	if ( r!= null) {
		    		if (r.getLeft()!=null) {
		    			initPerHODList(a,(BTPerHourOrDaily)r.getLeft());
		    		}
		    		else {
		    			goForward(a,r);
		    		}
		    	
			}
	    	return a;
	    	
					
	    }
	 
	 private void goForward(ArrayList<Vehicle> a,BTPerHourOrDaily r) {
		 while(r!=null) {
			 if(r.getBtVehicle()!=null) {
				 a.add(r.getBtVehicle());
			 }
			 goForward(a,(BTPerHourOrDaily)r.getRight());
		 }
	 }

	private ArrayList<Vehicle> initMontlyList(ArrayList<Vehicle> a, BTMonthly r){
		if ( r!= null) {
			if (r.getLeft()!=null) {
				initMontlyList(a,(BTMonthly)r.getLeft());
			}
			else {
				goForwardForMonthly(a,r);
			}

		}
		return a;


	}

	private void goForwardForMonthly(ArrayList<Vehicle> a,BTMonthly r) {
		while(r!=null) {
			a.add(r.getBtVehicle());
			goForwardForMonthly(a,(BTMonthly) r.getRight());
		}
	}*/
    //Binary trees finish
    
	
	public void fillPerHODVehiclesPL(){
		fillPerHODVehiclesPL(perHODVehiclesPL,perHourOrDailyVehicles);
		fillMonthlyVehiclesPL(monthlyVehiclesPL,monthlyVehicles);
	}
	
	public void fillPerHODVehiclesPL(ArrayList<Vehicle> a,BTPerHourOrDaily b){
		boolean temp=false;
		if (b != null) {
			
			fillPerHODVehiclesPL(a,(BTPerHourOrDaily)b.getLeft());
			if(b.getBtVehicle()!=null) {
				for(int i=0;i<a.size();i++) {
					if(b.getBtVehicle()==a.get(i)) {
						temp=true;
					}
				}
				if(temp==false) {
					b.getBtVehicle().setPay(b.getBtVehicle().getValueToPay()+"");
					a.add(b.getBtVehicle());
				}
			
			}
			fillPerHODVehiclesPL(a,(BTPerHourOrDaily)b.getRight());
		}
	}
	
	public void fillMonthlyVehiclesPL(ArrayList<Vehicle> a,BTMonthly b){
		boolean temp=false;
		if (b != null) {
			
			fillMonthlyVehiclesPL(a,(BTMonthly)b.getLeft());
			if(b.getBtVehicle()!=null) {
				for(int i=0;i<a.size();i++) {
					if(b.getBtVehicle()==a.get(i)) {
						temp=true;
					}
				}
				if(temp==false) {
					b.getBtVehicle().setPay(b.getBtVehicle().getValueToPay()+"");
					a.add(b.getBtVehicle());
				}
			
			}
			fillMonthlyVehiclesPL(a,(BTMonthly)b.getRight());
		}
	}
    /**
    Disable a vehicle searching it by It's license plate <br>
    <b> pre: </b>Needs that the vehicle is already created<br>
    <b> post: </b>Disable the vehicle<br>
    @param plate A string who have the specific vehicle�s license plate
    @return true or false
    */
    public boolean disableVehicleByPlate(String plate) {
    	for(int i=0;i<vehiclesPL.size();i++) {
    		if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
    			vehiclesPL.get(i).setEnabled(false);
    			cleanSpots(vehiclesPL.get(i).getSpot());
    			return true;
    		}
    	}
    	return false;
    }

    public void cleanSpots(int number){
    	if(number < 0){
    		if(number % 2 == 0){
				((MotorcycleSpot)plMap.spotAt(number)).setSpotVehicle1(null);
			}
    		else{
				((MotorcycleSpot)plMap.spotAt(number)).setSpotVehicle2(null);
			}
		}
    	else{
			((VehicleSpot)plMap.spotAt(number)).setSpotVehicle(null);
		}
	}
    
    public void addSpot(int number,Vehicle vehicle) {
    	if(number < 0){
    		if(number % 2 == 0){
				((MotorcycleSpot)plMap.spotAt(number)).setSpotVehicle1(vehicle);
			}
    		else{
				((MotorcycleSpot)plMap.spotAt(number)).setSpotVehicle2(vehicle);
			}
		}
    	else{
			((VehicleSpot)plMap.spotAt(number)).setSpotVehicle(vehicle);
		}
    }
    
    
    /**
    Update a vehicle's model searching it by It's license plate <br>
    <b> pre: </b>Needs that the vehicle is already created<br>
    <b> post: </b>Update the model<br>
    @param plate A string who have the specific vehicle�s license plate
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
    @param plate A string who have the specific vehicle�s license plate
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
    Update a vehicle's license plate searching it by It's past license plate <br>
    <b> pre: </b>Needs that the vehicle is already created<br>
    <b> post: </b>Update the license plate<br>
    @param plate A string who have the specific vehicles license plate
    @param newPlate A string who have the new license plate
    @return true or false
    */
    public boolean updateVehiclePlate(String plate, String newPlate) {
    	for(int i=0;i<vehiclesPL.size();i++) {
    		if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
    			
    			if(!(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(newPlate))){
    				vehiclesPL.get(i).setLicensePlate(newPlate);
    				return true;
    			}
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
    				cleanSpots(vehiclesPL.get(i).getSpot());
    				addSpot(newSpot,vehiclesPL.get(i));
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
    @param spot int with the place's number. <br>
    @return false if the spot is in use, or true if not. <br>
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
    @param plate A string who have the specific vehicle�s license plate
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
   @param searchId String with the id to search
   @return Pos int with the position
   */
   public int binarySearchPerson(ArrayList<?> aL,String searchId) {
		int pos = -1;
		int i = 0;
		int j = aL.size() - 1;

		while(i<=j && pos<0) {
			int m = (i + j)/2;
			String objectName= ((Person) aL.get(m)).getId();
			String id = searchId;
			if(objectName.equals(id)) {
				pos = m;
			}
			else if((objectName.compareTo(id)) > 0){
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
		String columns = "Nombre y apellidos" + separator + "Identificaci�n" + separator + "Telefono del cliente";
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
		+"Nombre de propietario"+ separator +"Identificaci�n propietario"+ separator +"Fecha de entrada"+ separator +"Fecha de salida"+"Valor a pagar";
		pw.println(columns);
		 for(int i = 0; i < vehiclesPL.size(); i++) {
			 LocalDateTime dateOfClient=vehiclesPL.get(i).getEntryDate();
			 if(dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
		    		pw.println(vehiclesPL.get(i).showInformation());
		    	}
			 
		 }
		pw.close();
	  }

	  //So deep
   
   /**
   Generate a csv about the vehicle's monthly information <br>
   <b> pre: </b>Needs a file in which write the information<br>
   <b> post: </b>Generate the csv<br>
   @param startDate LocalDateTime with the begin date 
   @param endDate LocalDateTime with the end date 
   */
   public void reportInfoMonthly(LocalDateTime startDate,LocalDateTime endDate) throws FileNotFoundException {
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
						+"Nombre de propietario"+ separator +"Identificaci�n propietario"+ separator +"Fecha de entrada"+ separator +"Fecha de salida";
						pw.println(columns);
						columnsVeryfier=true;
			   }
	   String temp="";
			   if (monthlyVehicles != null) {
					if(monthlyVehicles.getBtVehicle() != null){
						reportInfoMonthly((BTMonthly) monthlyVehicles.getLeft(), startDate, endDate, columnsVeryfier);
						LocalDateTime dateOfClient=monthlyVehicles.getBtVehicle().getEntryDate();
						if(dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
							temp=monthlyVehicles.getBtVehicle().getType().toString()+";"+monthlyVehicles.getBtVehicle().getModel()+";"
									+monthlyVehicles.getBtVehicle().getLicensePlate()+";"+monthlyVehicles.getBtVehicle()+";"+monthlyVehicles.getBtVehicle().getOwner().getName()
									+";"+monthlyVehicles.getBtVehicle().getOwner().getId()+";"+monthlyVehicles.getBtVehicle().getValueToPay()
									+"\n";
							pw.println(temp);
						}
						reportInfoMonthly((BTMonthly)monthlyVehicles.getRight(), startDate, endDate,columnsVeryfier);
					}
				}
		pw.close();
		
	}
   
   
   /**
   Generate a csv about the vehicle's per hour or daily information <br>
   <b> pre: </b>Needs a file in which write the information<br>
   <b> post: </b>Generate the csv<br>
   @param startDate LocalDateTime with the begin date 
   @param endDate LocalDateTime with the end date 
   */
   public void reportInfoDaily(LocalDateTime startDate,LocalDateTime endDate) throws FileNotFoundException {
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
			+"Nombre de propietario"+ separator +"Identificaci�n propietario"+ separator +"Fecha de entrada"+ separator +"Fecha de salida";
			pw.println(columns);
			columnsVeryfier=true;
		   }
	   String temp="";
		if (perHourOrDailyVehicles != null) {
			if(perHourOrDailyVehicles.getBtVehicle() != null){
				reportInfoDaily((BTPerHourOrDaily) perHourOrDailyVehicles.getLeft(), startDate, endDate, columnsVeryfier);
				LocalDateTime dateOfClient=perHourOrDailyVehicles.getBtVehicle().getEntryDate();
				if(dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
					temp=perHourOrDailyVehicles.getBtVehicle().getType().toString()+";"+perHourOrDailyVehicles.getBtVehicle().getModel()+";"
							+perHourOrDailyVehicles.getBtVehicle().getLicensePlate()+";"+perHourOrDailyVehicles.getBtVehicle()+";"+perHourOrDailyVehicles.getBtVehicle().getOwner().getName()
							+";"+perHourOrDailyVehicles.getBtVehicle().getOwner().getId()+";"+perHourOrDailyVehicles.getBtVehicle().getValueToPay()
							+"\n";
					pw.println(temp);
				}
				reportInfoDaily((BTPerHourOrDaily)perHourOrDailyVehicles.getRight(), startDate, endDate,columnsVeryfier);
			}
		}
		pw.close();
	}

	/**
	 Create an arraylist with the closest results <br>
	 <b> pre: </b>There had to be an array list to search in<br>
	 <b> post: </b>Initializes the array<br>
	 @param match An String that is what is going to be searched
	 */
	public void setSearchClientResults(String match) {
		searchClientResults = new ArrayList<>();
		for (Client c : clientsPL) {
			String compare = c.getId();
			if (compare.contains(match)) searchClientResults.add(c);
		}
	}

	/**
	 Create an arraylist with the closest results <br>
	 <b> pre: </b>There had to be an array list to search in<br>
	 <b> post: </b>Initializes the array<br>
	 @param match An String that is what is going to be searched
	 */
	public void setSearchEmployeeResults(String match) {
		searchEmployeeResults = new ArrayList<>();
		for (Employee c : employeesPL) {
			String compare = c.getId();
			if (compare.contains(match)) searchEmployeeResults.add(c);
		}
	}

	/**
	 Create an arraylist with the closest results <br>
	 <b> pre: </b>There had to be an array list to search in<br>
	 <b> post: </b>Initializes the array<br>
	 @param match An String that is what is going to be searched
	 */
	public void setSearchVehicleResults(String match) {
		searchVehicleResults = new ArrayList<>();
		for (Vehicle c : vehiclesPL) {
			String compare = c.getLicensePlate();
			if (compare.contains(match)) searchVehicleResults.add(c);
		}
	}

	/**
	 Adds an element to the ArrayList<br>
	 <b> pre: </b><br>
	 <b> post: </b>Initializes the array<br>
	 @param toAdd A Client that is what was found
	 */
	public void singleElementToSearchClient(Client toAdd){
		searchClientResults = null;
		searchClientResults.add(toAdd);
	}

	/**
	 Adds an element to the ArrayList<br>
	 <b> pre: </b><br>
	 <b> post: </b>Initializes the array<br>
	 @param toAdd A Vehicle that is what was found
	 */
	public void singleElementToSearchVehicle(Vehicle toAdd){
		searchVehicleResults = null;
		searchVehicleResults.add(toAdd);
	}

	/**
	 Adds an element to the ArrayList<br>
	 <b> pre: </b><br>
	 <b> post: </b>Initializes the array<br>
	 @param toAdd An employee that is what was found
	 */
	public void singleElementToSearchEmployee(Employee toAdd){
		searchEmployeeResults = null;
		searchEmployeeResults.add(toAdd);
	}

   //jd gei //Algun problema con eso perra?//Dimelo de frente cag�n
	
	//Serialization 
	//
	//
	
	/**
	 Export the employees information to save it<br>
	 <b> pre: </b>Needs a list of employees<br>
	 <b> post: </b>Save the information in a text file<br>
	 */
	public void plainTextSaveEmployees() throws FileNotFoundException{
	    PrintWriter pw = new PrintWriter("data/Serializable/plain_text/Employees.txt");
	    for(int i=0;i<employeesPL.size();i++){
	      Employee employee = employeesPL.get(i);
	      pw.println(employee.getName()+";"+employee.getId()+";"+employee.getUsername()+";"+employee.getPassword());
	    }
	    pw.close();
	  }
	
	
	
	/**
	 Export the parking lot information to save it<br>
	 <b> pre: </b>Needs a file in which save the class<br>
	 <b> post: </b>Save the class in a file<br>
	 */
	public void saveParkingLot() throws IOException{
	    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Serializable/plain_text/data.1jz"));
	    oos.writeObject(this);
	    oos.close();
	  }

	
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

	public ArrayList<Client> getSearchClientResults() {
		return searchClientResults;
	}

	public void setSearchClientResults(ArrayList<Client> searchClientResults) {
		this.searchClientResults = searchClientResults;
	}

	public ArrayList<Employee> getSearchEmployeeResults() {
		return searchEmployeeResults;
	}

	public void setSearchEmployeeResults(ArrayList<Employee> searchEmployeeResults) {
		this.searchEmployeeResults = searchEmployeeResults;
	}

	public ArrayList<Vehicle> getSearchVehicleResults() {
		return searchVehicleResults;
	}

	public void setSearchVehicleResults(ArrayList<Vehicle> searchVehicleResults) {
		this.searchVehicleResults = searchVehicleResults;
	}
	public ArrayList<Vehicle> getPerHODVehiclesPL() {
		return perHODVehiclesPL;
	}

	public void setPerHODVehiclesPL(ArrayList<Vehicle> perHODVehiclesPL) {
		this.perHODVehiclesPL = perHODVehiclesPL;
	}

	public ArrayList<Vehicle> getMonthlyVehiclesPL() {
		return monthlyVehiclesPL;
	}

	public void setMonthlyVehiclesPL(ArrayList<Vehicle> monthlyVehiclesPL) {
		this.monthlyVehiclesPL = monthlyVehiclesPL;
	}
}
