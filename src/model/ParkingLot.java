package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ParkingLot implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private static ArrayList<Client> clientsPL;
    private ArrayList<Vehicle> vehiclesPL;
    private static ArrayList<Employee> employeesPL;
    private ParkingLotMap plMap;
    private BTPerHourOrDaily perHourOrDailyVehicles;
    private BTMonthly monthlyVehicles;
  

    public ParkingLot(){
        clientsPL = new ArrayList<>();
        vehiclesPL = new ArrayList<>();
        employeesPL=new ArrayList<>();
        plMap = new ParkingLotMap();
    }
    
    //Client methods
    //
    //
    //
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
   
    //Employee methods
    //
    //
    //
    
    /**
    Add a employee to the parking lot <br>
    <b> pre: </b>Needs verify if the employee is already in the parking lot<br>
    <b> post: </b>The employee will be created and added to the parking lot<br>
    @param name A string who have the new employee´s name
    @param id A string who have the new employee´s ID
    @param username A string who have the new employee´s username
	@param password  A string who have the new employee´s password
    @return true or false
    */
    public boolean addEmployee(String name, String id, String username, String password) {
    	String temp=employeeVeryfier(name,id,username,password);
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
    Disable a specific employee in the parking lot searching by It's ID <br>
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
    Disable a specific employee in the parking lot searching by It's name <br>
    <b> pre: </b>Needs verify if the employee already exists in the parking lot<br>
    <b> post: </b>The specific employee will be disabled<br>
    @param name A string who have the employee´s name
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
	 @param id A string who have the employee´s ID
	 @param newUsername A string who have the employee´s new username
	 @param pastUsername A string who have the employee's past username
    @param password A string who have the employee´s password
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
	 @param id A string who have the employee´s ID
	 @param username A string who have the employee´s username
	 @param password A string who have the employee´s past password
    @param newPassword A string who have the employee´s new password
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
    <b> post: </b>The user verify if the employee´s name or ID is duplicated<br>
    @param name A string who have the new employee´s name
    @param id A string who have the new employee´s ID
    @param password A string who have the new employee´s password
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
    <b> post: </b>The user verify if the employee´s user name or password is duplicated<br>
    @param username A string who have the employee´s username
    @param password A string who have the new employee´s password
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

    public ParkingLotMap getPlMap(){
    	return plMap;
	}

    
    //Manage vehicles
    
    /**
    Add a vehicle to the vehicle's ArrayList creating it by It's specific type <br>
    <b> pre: </b>Needs there is no other vehicle with the same specifications<br>
    <b> post: </b>The specific vehicle will be created and added<br>
    @param typeIndicator int whose information stipulate the type of the vehicle
    @param model String with the vehicle´s model
    @param licensePlate String with the vehicle's plate information
    @param color String with the vehicle's color
    @param owner The object client with the owner's information
    @param spot int with the vehicle's position at the parking lot
    @param observations String with extra information about the vehicle
    @param stayIndicator int with the indicator for the StayTime enum
    @param numberOfTime int with the amount of time that vehicle will spend in the PL
    @return true or false
    */
    public boolean addVehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, String observations, int stayIndicator, int numberOfTime) {
    	boolean check = false;
    	Vehicle toAdd;
    	if(verifyVehicleByPlate(licensePlate)) {

		    	switch(typeIndicator) {
		    	case 0:
		    		toAdd=new SmallVehicle(typeIndicator, model, licensePlate, color, owner, spot, observations, stayIndicator, numberOfTime);
		    		vehiclesPL.add(toAdd);
		    		check = true;
		    		break;
		    	case 1:
		    		toAdd=new Motorcycle(typeIndicator, model, licensePlate, color, owner, spot, observations, stayIndicator, numberOfTime);
		    		vehiclesPL.add(toAdd);
		  			check = true;
		  			break;
		    	case 2:
					case 3:
					case 4:
					case 5:
						toAdd=new LargeVehicle(typeIndicator, model, licensePlate, color, owner, spot, observations, stayIndicator, numberOfTime);
						vehiclesPL.add(toAdd);
		    			check = true;
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

	public void addAVehicleToPerHourOrDailyVehicles(Vehicle newVehicle){
    	BTPerHourOrDaily newBt = new BTPerHourOrDaily();
		newBt.setBtVehicle(newVehicle);
		perHourOrDailyVehicles = addAVehicleToPerHourOrDailyVehicles(perHourOrDailyVehicles,newBt);
	}

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

	public void addAVehicleToMonthlyVehicles(Vehicle newVehicle){
		BTMonthly newBt = new BTMonthly();
		newBt.setBtVehicle(newVehicle);
		monthlyVehicles = addAVehicleToMonthlyVehicles(monthlyVehicles,newBt);
	}

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
    
    
    /**
    Disable a vehicle searching it by It's license plate <br>
    <b> pre: </b>Needs that the vehicle is already created<br>
    <b> post: </b>Disable the vehicle<br>
    @param plate A string who have the specific vehicle´s license plate
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
    @param plate A string who have the specific vehicle´s license plate
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
    @param plate A string who have the specific vehicle´s license plate
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
    Update the observation of the vehicle <br>
    <b> pre: </b>Needs the vehicle is already created<br>
    <b> post: </b>Change the observation<br>
    @param newObservation String with the new observation
    @param plate String with the license plate information
    @return true or false
    */
    public boolean updateObservation(String plate,String newObservation) {
    	for(int i=0;i<vehiclesPL.size();i++) {
    		if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
    			vehiclesPL.get(i).setObservations(newObservation);
    			return true;
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
    	if(vehiclesPL.get(i).getSpot()==spot) {
			return false;
			}
    	}
    	return true;
    }
    
    
    /**
    Verify if the new vehicle is already created <br>
    <b> pre: </b><br>
    <b> post: </b>Verify is the vehicle can be created<br>
    @param plate A string who have the specific vehicle´s license plate
    @return true or false
    */
    public boolean verifyVehicleByPlate(String plate) {
    	for(int i=0;i<vehiclesPL.size();i++) {
    		if(vehiclesPL.get(i).getLicensePlate().equalsIgnoreCase(plate)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    //Sorting algorithms
    //
    //
    
    //Bubble lleva 1 implementación
    public static void clientBubbleSortName() {
    	
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
    
    
	
    //"Implementado "1 vez"
   public void clientInsertionSortByName(){ //Client pero llama vehicle xd
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
   
   
   //Está implementado "1 vez"
   public static void EmployeeselectionSortName(Integer[] array) {
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
   //Comparator lleva 1 implementación
   public void sortEmployeeByName() {   //Note: This method must be used because when the first or last name is modified, the order of these in the arrayList is changed.
		Comparator<Employee> nComparator = new employeeComparator();
		Collections.sort(employeesPL,nComparator);
	}
}
