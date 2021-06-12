package model;

import exceptions.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The parking lot and all of its attributes and functionality. <br>
 */
@SuppressWarnings("unused")
public class ParkingLot implements Serializable {

    private static final long serialVersionUID = 2L;
    private ArrayList<Client> clientsPL;
    private ArrayList<Vehicle> vehiclesPL;
    transient ArrayList<Employee> employeesPL;
    private ParkingLotMap plMap;
    private BTHourlyOrDaily perHourOrDailyVehicles;
    private BTMonthly monthlyVehicles;
    transient Employee root;
    transient Employee currentEmployee;
    private boolean firstTime;
    private boolean columnsVerifier;
    private ArrayList<Client> searchClientResults;
    private ArrayList<Employee> searchEmployeeResults;
    private ArrayList<Vehicle> searchVehicleResults;
    private ArrayList<Vehicle> perHODVehiclesPL;
    private ArrayList<Vehicle> monthlyVehiclesPL;

    /**
     * This is the main constructor of the class. <br>
     */
    public ParkingLot() {
        clientsPL = new ArrayList<>();
        vehiclesPL = new ArrayList<>();
        employeesPL = new ArrayList<>();
        perHODVehiclesPL = new ArrayList<>();
        monthlyVehiclesPL = new ArrayList<>();
        plMap = new ParkingLotMap();
        root = new Employee("Admin", "12345", "admin", "1234");
        currentEmployee = root;
        firstTime = true;
        columnsVerifier = false;
        searchClientResults = new ArrayList<>();
        searchEmployeeResults = new ArrayList<>();
        searchVehicleResults = new ArrayList<>();
    }

    /**
     * @return True if the app has never been opened before. <br>
     */
    public boolean checkFirstTime() {
        return firstTime && currentEmployee.equals(root);
    }

    /*							Client methods						*/

    /**
     * Add a client to the parking lot <br>
     * <b> pre: </b>Needs verify if the clients is already in the parking lot<br>
     * <b> post: </b>The client will be created and added to the parking lot<br>
     *
     * @param name       A string with the new client's name
     * @param id         A string with the new client's ID
     * @param cellNumber A string with the new client's cellNumber
     * @return true when the client was added successfully. <br>
     * @throws IDAlreadyInUseException When the imputed ID for a new client is already in use. <br>
     */
    public boolean addClient(String name, String id, String cellNumber) throws IDAlreadyInUseException {
        if (searchByName(name) != null && !searchByName(name).isEnabled()) {
            clientsPL.add(new Client(name, id, cellNumber));
            return true;
        } else {
            String temp = ClientVerifier(name, id, cellNumber);
            if (temp.contains("id")) {
                throw new IDAlreadyInUseException();
            } else {
                Client aux = new Client(name, id, cellNumber);
                clientsPL.add(aux);
                return true;
            }
        }
    }

    /**
     * Removes the last added client. <br>
     */
    public void removeLastClient() {
        clientsPL.remove(clientsPL.size() - 1);
    }


    /**
     * Disables a specific client in the parking lot according to the imputed id. <br>
     * <b> pre: </b>Needs to verify if the client already exists and if they do not own a car in the parking lot. <br>
     * <b> post: </b>The specific client will be disabled. <br>
     *
     * @param id A string with the client's id. <br>
     * @return true if the client was successfully disabled. <br>
     */
    public boolean disableClientByID(String id) {
        Client aux = searchByID(id);
        if (aux != null) {
            aux.setStatus(false);
            return true;
        }
        return false;
    }

    //A juan david le gusta el monitor <- confirmamos

    /**
     * Disables a specific client in the parking lot according to the imputed name. <br>
     * <b> pre: </b>Needs to verify if the client already exists and if they do not own a car in the parking lot. <br>
     * <b> post: </b>The specific client will be disabled. <br>
     *
     * @param name A string with the client's name. <br>
     * @return true if the client is successfully disabled. <br>
     */
    public boolean disableClientByName(String name) {
        Client aux = searchByName(name);
        if (aux != null) {
            aux.setStatus(false);
            return true;
        }
        return false;
    }

    /**
     * Updates a specific client's name. <br>
     * <b> pre: </b>Needs to verify if the client already exists and if their new name is not used by another client<br>
     * <b> post: </b>The specific client will have their name updated. <br>
     *
     * @param newName A string with the client's new name.<br>
     * @param id      A string with the clients id. <br>
     * @return true if the client's name was updated. <br>
     */
    public boolean updateClientName(String id, String newName) {
        if ((searchByID(id) != null) && (searchByName(newName) == null)) {
            searchByID(id).setName(newName);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Updates a specific client's ID. <br>
     * <b> pre: </b>Needs to verify if the client already exists and if their new ID is not used by other client. <br>
     * <b> post: </b>The specific client will have their ID. <br>
     *
     * @param newID  A string with the client's new ID. <br>
     * @param pastID A string with the client's past ID. <br>
     * @return true if the the client's ID is updated successfully. <br>
     */
    public boolean updateClientID(String pastID, String newID) {
        if ((searchByID(newID) == null) && (searchByID(pastID) != null)) {
            searchByID(pastID).setId(newID);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates a specific client's cell number<br>
     * <b> pre: </b>Needs verify if the client already exists<br>
     * <b> post: </b>The specific client will be updated his cell number<br>
     *
     * @param id            A string with the client's ID
     * @param newCellNumber A string with the client's new cell number
     * @return true or false
     */
    public boolean updateClientCellNumber(String id, String newCellNumber) {
        Client aux = searchByID(id);
        if (aux != null) {
            aux.setCellNumber(newCellNumber);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify if the new client has already exist<br>
     * <b> pre: </b>Needs ask to the user the necessary parameters to create a client<br>
     * <b> post: </b>The user verify if the client's name or ID is duplicated<br>
     *
     * @param name       A string with the new client's name
     * @param id         A string with the new client's ID
     * @param cellNumber A string with the new client's cellNumber
     * @return temp A string
     */
    public String ClientVerifier(String name, String id, String cellNumber) {
        StringBuilder temp = new StringBuilder();
        if (clientsPL != null) {
            Client aux = new Client(name, id, cellNumber);
            for (Client client : clientsPL) {
                if (aux.getName().equalsIgnoreCase(client.getName())) {
                    temp.append("name");
                }
                if (aux.getId().equalsIgnoreCase(client.getId())) {
                    temp.append("id");
                }
            }
        }
        return temp.toString();
    }

    /**
     * Search a specific client by It's ID <br>
     * <b> pre: </b>Needs the specific client were created<br>
     * <b> post: </b>The specific client will be founded<br>
     *
     * @param id A string with the specific client's id
     * @return Object client or null
     */
    public Client searchByID(String id) {
        if (clientsPL != null) {
            for (Client client : clientsPL) {
                if (client.getId().equalsIgnoreCase(id)) {
                    return client;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * Search a specific client by It's name <br>
     * <b> pre: </b>Needs the specific client were created<br>
     * <b> post: </b>The specific client will be founded<br>
     *
     * @param name A string with the specific client's name
     * @return Object client or null
     */
    public Client searchByName(String name) {
        if (clientsPL != null) {
            for (Client client : clientsPL) {
                if (client.getName().equalsIgnoreCase(name)) {
                    return client;
                }
            }
            return null;
        }
        return null;
    }

    /*                      Employee methods                */

    /**
     * Add a employee to the parking lot <br>
     * <b> pre: </b>Needs verify if the employee is already in the parking lot<br>
     * <b> post: </b>The employee will be created and added to the parking lot<br>
     *
     * @param name     A string with the new employee's name
     * @param id       A string with the new employee's ID
     * @param username A string with the new employee's username
     * @param password A string with the new employee's password
     * @return true or false
     * @throws IDAlreadyInUseException When the ID is already in use. <br>
     */
    public boolean addEmployee(String name, String id, String username, String password) throws IDAlreadyInUseException, UsernameAlreadyInUseException {
        if (searchEmployeeByName(name) != null && !searchEmployeeByName(name).getState()) {
            for (int i = 0; i < employeesPL.size(); i++) {
                if (employeesPL.get(i).getId().equalsIgnoreCase(searchEmployeeByName(name).getId())) {
                    employeesPL.remove(i);
                    employeesPL.add(new Employee(name, id, username, password));
                }
            }
        }
        String temp = employeeVerifier(name, id, username, password);
        if (temp.contains("id")) {
            throw new IDAlreadyInUseException();
        } else if (temp.contains("username")) {
            throw new UsernameAlreadyInUseException();
        } else {
            Employee aux = new Employee(name, id, username, password);
            employeesPL.add(aux);
            return true;
        }
    }

    /**
     * Disable a specific employee in the parking lot searching by It's ID <br>
     * <b> pre: </b>Needs verify if the employee already exists in the parking lot<br>
     * <b> post: </b>The specific employee will be disabled<br>
     *
     * @param id A string with the employee's id
     * @return true or false
     */
    public boolean disableEmployeeByID(String id) {
        Employee aux = searchEmployeeByID(id);
        if (aux != null) {
            aux.setState(false);
            return true;
        }
        return false;
    }


    /**
     * Disable a specific employee in the parking lot searching by It's name <br>
     * <b> pre: </b>Needs verify if the employee already exists in the parking lot<br>
     * <b> post: </b>The specific employee will be disabled<br>
     *
     * @param name A string with the employee's name
     * @return true or false
     */
    public boolean disableEmployeeByName(String name) {
        Employee aux = searchEmployeeByName(name);
        if (aux != null) {
            aux.setState(false);
            return true;
        }
        return false;
    }

    /**
     * Update a specific employee's name <br>
     * <b> pre: </b>Needs verify if the employee already exists and if his new name is not used by other employee<br>
     * <b> post: </b>The specific employee will be updated his name<br>
     *
     * @param newName A string with the employee's new Name
     * @param id      A string with the employees id
     * @return true or false
     */
    public boolean updateEmployeeName(String id, String newName) {
        if ((searchEmployeeByID(id) != null) && (searchEmployeeByName(newName) == null)) {
            searchEmployeeByID(id).setName(newName);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Update a specific employee's ID<br>
     * <b> pre: </b>Needs verify if the employee already exists and if his new ID is not used by other employee<br>
     * <b> post: </b>The specific employee will be updated his ID<br>
     *
     * @param newID  A string with the employee's new ID
     * @param pastID A string with the employee's past ID
     * @return true or false
     */
    public boolean updateEmployeeID(String pastID, String newID) {
        if ((searchEmployeeByID(newID) == null) && (searchEmployeeByID(pastID) != null)) {
            searchEmployeeByID(pastID).setId(newID);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Update a specific employee's password<br>
     * <b> pre: </b>Needs verify if the employee already exists and if his new password is not used by other employee<br>
     * <b> post: </b>The specific employee will be updated his password<br>
     *
     * @param id          A string with the employee's ID
     * @param newUsername A string with the employee's new username
     * @return true or false
     */
    public boolean updateEmployeeUsername(String id, String newUsername) {
        if (searchEmployeeByID(id) != null) {
            searchEmployeeByID(id).setUsername(newUsername);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Update a specific employee's password<br>
     * <b> pre: </b>Needs verify if the employee already exists and if his new password is not used by other employee<br>
     * <b> post: </b>The specific employee will be updated his password<br>
     *
     * @param id          A string with the employee's ID
     * @param newPassword A string with the employee's new password
     * @return true or false
     */
    public boolean updateEmployeePassword(String id, String newPassword) {
        if (searchEmployeeByID(id) != null) {
            searchEmployeeByID(id).setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify if the new employee's name, id, username or password is already in use<br>
     * <b> pre: </b>Needs ask to the user the necessary parameters to create a employee<br>
     * <b> post: </b>The user verify if the employee's name or ID is duplicated<br>
     *
     * @param name     A string with the new employee's name
     * @param id       A string with the new employee's ID
     * @param password A string with the new employee's password
     * @param username A string with the new employee's username
     * @return temp A string
     */
    public String employeeVerifier(String name, String id, String username, String password) {
        StringBuilder temp = new StringBuilder();
        if (employeesPL != null) {
            Employee aux = new Employee(name, id, username, password);
            for (Employee employee : employeesPL) {
                if (aux.getName().equalsIgnoreCase(employee.getName())) {
                    temp.append("name");
                }
                if (aux.getId().equalsIgnoreCase(employee.getId())) {
                    temp.append("id");
                }
                if (aux.getUsername().equalsIgnoreCase(employee.getUsername())) {
                    temp.append("username");
                }
                if (aux.getPassword().equalsIgnoreCase(employee.getPassword())) {
                    temp.append("password");
                }
            }
        }
        return temp.toString();
    }


    /**
     * Verify if the employee username and password is already in use<br>
     * <b> pre: </b>Needs there to be employee created<br>
     * <b> post: </b>The user verify if the employee's user name or password is duplicated<br>
     *
     * @param username A string with the employee's username
     * @param password A string with the new employee's password
     * @return true or false
     */
    public boolean employeeVerifierLogin(String username, String password) {
        boolean temp = false;
        if (employeesPL != null) {
            for (Employee employee : employeesPL) {
                if (username.equalsIgnoreCase(employee.getUsername())) {
                    temp = true;
                }
                if (password.equalsIgnoreCase(employee.getPassword())) {
                    temp = true;
                }
            }
        }
        return temp;
    }


    /**
     * Search a specific employee by It's name <br>
     * <b> pre: </b>Needs the specific employee were created<br>
     * <b> post: </b>The specific employee will be founded<br>
     *
     * @param name A string with the specific employee's name
     * @return Object employee or null
     */
    public Employee searchEmployeeByName(String name) {
        if (employeesPL != null) {
            for (Employee employee : employeesPL) {
                if (employee.getName().equalsIgnoreCase(name)) {
                    return employee;
                }
            }
            return null;
        }
        return null;
    }


    /**
     * Search a specific employee by It's ID <br>
     * <b> pre: </b>Needs the specific employee were created<br>
     * <b> post: </b>The specific employee will be founded<br>
     *
     * @param id A string with the specific employee's id
     * @return Object employee or null
     */
    public Employee searchEmployeeByID(String id) {
        if (employeesPL != null) {
            for (Employee employee : employeesPL) {
                if (employee.getId().equalsIgnoreCase(id)) {
                    return employee;
                }
            }
            return null;
        }
        return null;

    }

    public ParkingLotMap getPlMap() {
        return plMap;
    }


    //Manage vehicles

    /**
     * Add a vehicle to the vehicle's ArrayList creating it by It's specific type <br>
     * <b> pre: </b>Needs there is no other vehicle with the same specifications<br>
     * <b> post: </b>The specific vehicle will be created and added<br>
     *
     * @param typeIndicator int whose information stipulate the type of the vehicle
     * @param model         String with the vehicle's model
     * @param licensePlate  String with the vehicle's plate information
     * @param color         String with the vehicle's color
     * @param owner         The object client with the owner's information
     * @param spot          int with the vehicle's position at the parking lot
     * @param stayIndicator int with the indicator for the StayTime enum
     * @param numberOfTime  int with the amount of time that vehicle will spend in the PL
     * @return true or false
     * @throws NotAllowedException when the spot is already in use. <br>
     */
    public boolean addVehicle(int typeIndicator, String model, String licensePlate, String color, Client owner, int spot, int stayIndicator, int numberOfTime) throws NotAllowedException {
        boolean check = false;
        Vehicle toAdd;
        if (verifyVehicleByPlate(licensePlate)) {

            switch (typeIndicator) {
                case 0:
                    toAdd = new SmallVehicle(typeIndicator, model, licensePlate, color, owner, spot, stayIndicator, numberOfTime);
                    for (int i = 0; i < toAdd.getAvailableSpots().length; i++) {
                        if (toAdd.getSpot() == toAdd.getAvailableSpots()[i]) {
                            vehiclesPL.add(toAdd);
                            check = true;
                        }
                    }
                    if (!check) {
                        throw new NotAllowedException();
                    }

                    break;
                case 1:
                    toAdd = new Motorcycle(typeIndicator, model, licensePlate, color, owner, spot, stayIndicator, numberOfTime);
                    for (int i = 0; i < toAdd.getAvailableSpots().length; i++) {
                        if (toAdd.getSpot() == toAdd.getAvailableSpots()[i]) {
                            vehiclesPL.add(toAdd);
                            check = true;
                        }
                    }
                    if (!check) {
                        throw new NotAllowedException();
                    }
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    toAdd = new LargeVehicle(typeIndicator, model, licensePlate, color, owner, spot, stayIndicator, numberOfTime);
                    for (int i = 0; i < toAdd.getAvailableSpots().length; i++) {
                        if (toAdd.getSpot() == toAdd.getAvailableSpots()[i]) {
                            vehiclesPL.add(toAdd);
                            check = true;
                        }
                    }
                    if (!check) {
                        throw new NotAllowedException();
                    }
                    break;
                default:
                    return false;
            }
            switch (stayIndicator) {
                case 0:
                case 1:
                    addAVehicleToPerHourOrDailyVehicles(toAdd);
                    break;
                case 2:
                    addAVehicleToMonthlyVehicles(toAdd);
                    break;
                default:
            }
            addSpot(toAdd.getSpot(), toAdd);
        }
        return check;
    }

    /*              Binary trees begin          */

    /**
     * Add a vehicle to the per hour or daily binary tree  <br>
     * <b> pre: </b>The vehicle was already created<br>
     * <b> post: </b>Add a vehicle to the binary tree<br>
     *
     * @param newVehicle Vehicle who is go to be added to the binary tree
     */
    public void addAVehicleToPerHourOrDailyVehicles(Vehicle newVehicle) {
        BTHourlyOrDaily newBt = new BTHourlyOrDaily();
        newBt.setBtVehicle(newVehicle);
        perHourOrDailyVehicles = addAVehicleToPerHourOrDailyVehicles(perHourOrDailyVehicles, newBt);
    }

    /**
     * Find a vehicle to the per hour or daily binary tree  <br>
     * <b> pre: </b><br>
     * <b> post: </b>Add a vehicle to the binary tree<br>
     *
     * @param newBT A node of the binary tree per hour or daily
     * @param r     A node of the binary tree per hour or daily
     * @return r node of the binary tree per hour or daily
     */
    private BTHourlyOrDaily addAVehicleToPerHourOrDailyVehicles(BTHourlyOrDaily r, BTHourlyOrDaily newBT) {
        if (r == null) {
            r = newBT;
            return r;
        }
        if ((newBT.getBtVehicle().compareTo(r.getBtVehicle())) <= 0) //no estoy seguro
            r.setLeft(addAVehicleToPerHourOrDailyVehicles((BTHourlyOrDaily) r.getLeft(), newBT));
        else if ((newBT.getBtVehicle().compareTo(r.getBtVehicle())) > 0)
            r.setRight(addAVehicleToPerHourOrDailyVehicles((BTHourlyOrDaily) r.getRight(), newBT));
        return r;
    }

    /**
     * Add a vehicle to the month binary tree  <br>
     * <b> pre: </b>The vehicle was already created<br>
     * <b> post: </b>Add a vehicle to the binary tree<br>
     *
     * @param newVehicle Vehicle who is go to be added to the binary tree
     */
    public void addAVehicleToMonthlyVehicles(Vehicle newVehicle) {
        BTMonthly newBt = new BTMonthly();
        newBt.setBtVehicle(newVehicle);
        monthlyVehicles = addAVehicleToMonthlyVehicles(monthlyVehicles, newBt);
    }

    /**
     * Fin a vehicle to the month binary tree  <br>
     * <b> pre: </b><br>
     * <b> post: </b>Add a vehicle to the binary tree<br>
     *
     * @param newBT A node of the binary tree monthly
     * @param r     A node of the binary tree monthly
     * @return r node of the binary tree monthly
     */
    private BTMonthly addAVehicleToMonthlyVehicles(BTMonthly r, BTMonthly newBT) {
        if (r == null) {
            r = newBT;
            return r;
        }
        if ((newBT.getBtVehicle().compareTo(r.getBtVehicle())) <= 0) //no estoy seguro
            r.setLeft(addAVehicleToMonthlyVehicles((BTMonthly) r.getLeft(), newBT));
        else if ((newBT.getBtVehicle().compareTo(r.getBtVehicle())) > 0)
            r.setRight(addAVehicleToMonthlyVehicles((BTMonthly) r.getRight(), newBT));
        return r;
    }

    //Binary trees finish

    /**
     * Fills the daily and hourly vehicles. <br>
     */
    public void fillPerHODVehiclesPL() {
        fillPerHODVehiclesPL(perHODVehiclesPL, perHourOrDailyVehicles);
        fillMonthlyVehiclesPL(monthlyVehiclesPL, monthlyVehicles);
    }

    /**
     * Fills the daily and hourly vehicles. <br>
     *
     * @param a An arraylist with the vehicles. @NotNull. <br>
     * @param b The binary tree of associated to the vehicle. @NotNull <br>
     */
    public void fillPerHODVehiclesPL(ArrayList<Vehicle> a, BTHourlyOrDaily b) {
        boolean temp = false;
        if (b != null) {

            fillPerHODVehiclesPL(a, (BTHourlyOrDaily) b.getLeft());
            if (b.getBtVehicle() != null) {
                for (Vehicle vehicle : a) {
                    if (b.getBtVehicle() == vehicle) {
                        temp = true;
                        break;
                    }
                }
                if (!temp) {
                    b.getBtVehicle().setPay(b.getBtVehicle().getValueToPay() + "");
                    a.add(b.getBtVehicle());
                }
            }
            fillPerHODVehiclesPL(a, (BTHourlyOrDaily) b.getRight());
        }
    }

    public void fillMonthlyVehiclesPL(ArrayList<Vehicle> a, BTMonthly b) {
        boolean temp = false;
        if (b != null) {
            fillMonthlyVehiclesPL(a, (BTMonthly) b.getLeft());
            if (b.getBtVehicle() != null) {
                for (Vehicle vehicle : a) {
                    if (b.getBtVehicle() == vehicle) {
                        temp = true;
                        break;
                    }
                }
                if (!temp) {
                    b.getBtVehicle().setPay(b.getBtVehicle().getValueToPay() + "");
                    a.add(b.getBtVehicle());
                }
            }
            fillMonthlyVehiclesPL(a, (BTMonthly) b.getRight());
        }
    }

    /**
     * Disable a vehicle searching it by It's license plate <br>
     * <b> pre: </b>Needs that the vehicle is already created<br>
     * <b> post: </b>Disable the vehicle<br>
     *
     * @param plate A string with the specific vehicle's license plate
     * @return true or false
     */
    public boolean disableVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehiclesPL) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(plate)) {
                vehicle.setEnabled(false);
                cleanSpots(vehicle.getSpot());
                return true;
            }
        }
        return false;
    }

    /**
     * Clears a spot of the parking lot map. <br>
     *
     * @param number The spot to be cleared. <br>
     */
    public void cleanSpots(int number) {
        if (number < 0) {
            if (number % 2 == 0) {
                ((MotorcycleSpot) plMap.spotAt(number)).setSpotVehicle1(null);
            } else {
                ((MotorcycleSpot) plMap.spotAt(number)).setSpotVehicle2(null);
            }
        } else {
            ((VehicleSpot) plMap.spotAt(number)).setSpotVehicle(null);
        }
    }

    /**
     * Adds a spot to the parking lot. <br>
     *
     * @param number  The number of the spot. Must be between <b>-10 and 29</b>. <br>
     * @param vehicle The vehicle hosted in the slot. <br>
     */
    public void addSpot(int number, Vehicle vehicle) {
        if (number < 0) {
            if (number % 2 == 0) {
                ((MotorcycleSpot) plMap.spotAt(number)).setSpotVehicle1(vehicle);
            } else {
                ((MotorcycleSpot) plMap.spotAt(number)).setSpotVehicle2(vehicle);
            }
        } else {
            ((VehicleSpot) plMap.spotAt(number)).setSpotVehicle(vehicle);
        }
    }

    /**
     * Update a vehicle's model searching it by It's license plate <br>
     * <b> pre: </b>Needs that the vehicle is already created<br>
     * <b> post: </b>Update the model<br>
     *
     * @param plate    A string with the specific vehicle's license plate
     * @param newModel A string with the new model
     * @return true or false
     */
    public boolean updateVehicleModel(String plate, String newModel) {
        for (Vehicle vehicle : vehiclesPL) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(plate)) {
                vehicle.setModel(newModel);
                return true;
            }
        }
        return false;
    }


    /**
     * Update a vehicle's color searching it by It's license plate <br>
     * <b> pre: </b>Needs that the vehicle is already created<br>
     * <b> post: </b>Update the color<br>
     *
     * @param plate    A string with the specific vehicle's license plate
     * @param newColor A string with the new color
     * @return true or false
     */
    public boolean updateVehicleColor(String plate, String newColor) {
        for (Vehicle vehicle : vehiclesPL) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(plate)) {
                vehicle.setColor(newColor);
                return true;
            }
        }
        return false;
    }

    /**
     * Update a vehicle's license plate searching it by It's past license plate <br>
     * <b> pre: </b>Needs that the vehicle is already created<br>
     * <b> post: </b>Update the license plate<br>
     *
     * @param plate    A string with the specific vehicles license plate
     * @param newPlate A string with the new license plate
     * @return true or false
     */
    public boolean updateVehiclePlate(String plate, String newPlate) {
        for (Vehicle vehicle : vehiclesPL) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(plate)) {
                if (!(vehicle.getLicensePlate().equalsIgnoreCase(newPlate))) {
                    vehicle.setLicensePlate(newPlate);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Update the spot in which is placed the vehicle <br>
     * <b> pre: </b>Needs the vehicle is already created<br>
     * <b> post: </b>Change the spot<br>
     *
     * @param newSpot int with the new place number
     * @param plate   String with the license plate information
     * @return true or false
     */
    public boolean updateSpot(String plate, int newSpot) {
        if (verifySpot(newSpot)) {
            for (Vehicle vehicle : vehiclesPL) {
                if (vehicle.getLicensePlate().equalsIgnoreCase(plate)) {
                    cleanSpots(vehicle.getSpot());
                    addSpot(newSpot, vehicle);
                    vehicle.setSpot(newSpot);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verify if the new spot is already in use <br>
     * <b> pre: </b><br>
     * <b> post: </b>Check if the place is vacate<br>
     *
     * @param spot int with the place's number. <br>
     * @return false if the spot is in use, or true if not. <br>
     */
    public boolean verifySpot(int spot) {
        for (Vehicle vehicle : vehiclesPL) {
            if (vehicle.isEnabled()) {
                if (vehicle.getSpot() == spot) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verify if the new vehicle is already created <br>
     * <b> pre: </b><br>
     * <b> post: </b>Verify is the vehicle can be created<br>
     *
     * @param plate A string with the specific vehicle's license plate
     * @return true or false
     */
    public boolean verifyVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehiclesPL) {
            if (vehicle.isEnabled()) {
                if (vehicle.getLicensePlate().equalsIgnoreCase(plate)) {
                    return false;
                }
            }
        }
        return true;
    }


    /*              Sorting algorithms          */

    /**
     * Use the bubble sort to organize the clients by name<br>
     * <b> pre: </b>There had to be an array list to sort<br>
     * <b> post: </b>Sort the list<br>
     */
    public void clientBubbleSortName() {
        for (int i = 1; i < clientsPL.size(); i++) {
            for (int j = 0; j < clientsPL.size() - i; j++) {
                if ((clientsPL.get(j).compareTo(clientsPL.get(j + 1)) > 0)) {
                    Client temp = clientsPL.get(j);
                    clientsPL.set(j, clientsPL.get(j + 1));
                    clientsPL.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Use the bubble sort to organize the clients by Id <br>
     * <b> pre: </b>There had to be an array list to sort<br>
     * <b> post: </b>Sort the list<br>
     */
    public void clientBubbleSortByID() {
        for (int i = 1; i < clientsPL.size(); i++) {
            for (int j = 0; j < clientsPL.size() - i; j++) {
                if ((clientsPL.get(j).getId().compareTo(clientsPL.get(j + 1).getId()) > 0)) {
                    Client temp = clientsPL.get(j);
                    clientsPL.set(j, clientsPL.get(j + 1));
                    clientsPL.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Use the insertion sort to organize the vehicles by license plate <br>
     * <b> pre: </b>There had to be an array list to sort<br>
     * <b> post: </b>Sort the list<br>
     */
    public void vehicleInsertionSortByPlate() {
        int i;
        int j;
        Vehicle aux;
        for (i = 1; i < vehiclesPL.size(); i++) {
            aux = vehiclesPL.get(i);
            j = i - 1;
            while ((j >= 0) && (aux.compareTo(vehiclesPL.get(j)) < 0)) {
                vehiclesPL.set(j + 1, vehiclesPL.get(j));
                j--;
            }
            vehiclesPL.set(j + 1, aux);
        }
    }

    /**
     * Use the insertion sort to organize the vehicles by owner's name <br>
     * <b> pre: </b>There had to be an array list to sort<br>
     * <b> post: </b>Sort the list<br>
     */
    public void vehicleInsertionSortByOwner() {
        int i;
        int j;
        Vehicle aux;
        for (i = 1; i < vehiclesPL.size(); i++) {
            aux = vehiclesPL.get(i);
            j = i - 1;
            while ((j >= 0) && (aux.getOwner().getName().compareTo((vehiclesPL.get(j).getOwner().getName())) < 0)) {
                vehiclesPL.set(j + 1, vehiclesPL.get(j));
                j--;
            }
            vehiclesPL.set(j + 1, aux);
        }
    }

    /**
     * Use the selection sort to organize the employees by username <br>
     * <b> pre: </b>There had to be an array list to sort<br>
     * <b> post: </b>Sort the list<br>
     */
    public void employeeSelectionSortByUsername() {
        if (employeesPL.size() > 2) {
            for (int i = 0; i < employeesPL.size(); i++) {
                Employee min = employeesPL.get(0);
                for (int j = i + 1; i < employeesPL.size(); j++) {
                    if (employeesPL.get(j).compareTo(min) < 0) {
                        Employee temp = employeesPL.get(j);
                        employeesPL.set(j, min);
                        min = temp;
                    }
                }
                employeesPL.set(i, min);
            }
        }
    }

    /**
     * Use the selection sort to organize the employees by id <br>
     * <b> pre: </b>There had to be an array list to sort<br>
     * <b> post: </b>Sort the list<br>
     */
    public void employeeSelectionSortById() {
        if (employeesPL.size() > 2) {
            for (int i = 0; i < employeesPL.size(); i++) {

                Employee min = employeesPL.get(0);
                for (int j = i + 1; i < employeesPL.size(); j++) {
                    if (employeesPL.get(j).getId().compareTo(min.getId()) < 0) {
                        Employee temp = employeesPL.get(j);
                        employeesPL.set(j, min);
                        min = temp;
                    }
                }
                employeesPL.set(i, min);
            }
        }
    }

    /**
     * Use the class comparator to organize the employees by name <br>
     * <b> pre: </b>There had to be an array list to sort<br>
     * <b> post: </b>Sort the list<br>
     */
    public void sortEmployeeByName() {
        Comparator<Employee> nComparator = new employeeComparator();
        Collections.sort(employeesPL, nComparator);
    }

    /**
     * Use the class comparator to organize the vehicles by model <br>
     * <b> pre: </b>There had to be an array list to sort<br>
     * <b> post: </b>Sort the list<br>
     */
    public void sortVehicleByModel() {
        Comparator<Vehicle> nComparator = new vehicleComparator();
        Collections.sort(vehiclesPL, nComparator);
    }

    /*                  Binary search               */

    /**
     * Use the binary search to find a person <br>
     * <b> pre: </b>There had to be an array list to search in<br>
     * <b> post: </b>Give the position of the person<br>
     *
     * @param aL       Array list generic with the list to search in
     * @param searchId String with the id to search
     * @return Pos int with the position
     */
    public int binarySearchPerson(ArrayList<?> aL, String searchId) {
        int pos = -1;
        int i = 0;
        int j = aL.size() - 1;
        while (i <= j && pos < 0) {
            int m = (i + j) / 2;
            String objectName = ((Person) aL.get(m)).getId();
            if (objectName.equals(searchId)) {
                pos = m;
            } else if ((objectName.compareTo(searchId)) > 0) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return pos;
    }

    /**
     * Use the binary search to find a vehicle by It's license plate <br>
     * <b> pre: </b>There had to be an array list to search in<br>
     * <b> post: </b>Give the position of the vehicle<br>
     *
     * @param aL          Array list generic with the list to search in
     * @param searchPlate String with the license plate to search
     * @return Pos int with the position
     */
    public int binarySearchVehicle(ArrayList<?> aL, String searchPlate) {
        int pos = -1;
        int i = 0;
        int j = aL.size() - 1;
        while (i <= j && pos < 0) {
            int m = (i + j) / 2;
            String objectName = ((Vehicle) aL.get(m)).getLicensePlate();
            if (objectName.equals(searchPlate)) {
                pos = m;
            } else if ((objectName.compareTo(searchPlate)) > 0) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return pos;
    }

    /*                Login               */

    /**
     * Verify the login <br>
     * <b> pre: </b><br>
     * <b> post: </b>Give the case of the login<br>
     *
     * @param password String with the employee password
     * @param username String with the employee user name
     * @return int with the login case
     */
    public int login(String username, String password) {
        int index = -1; //Not found
        boolean band = false;
        for (int i = 0; i < employeesPL.size() && !band; i++) {
            if ((employeesPL.get(i).getUsername().equals(username)) && (employeesPL.get(i).getPassword().equals(password))) {
                index = i;
                band = true;
            }
        }
        if (root.getUsername().equals(username) && root.getPassword().equals(password)) {
            index = -2; // The user is the rootUser
        }
        return index;
    }

    /*                  Reports                     */

    /**
     * Generate a csv about the client's information <br>
     * <b> pre: </b>Needs a file in which write the information<br>
     * <b> post: </b>Generate the csv<br>
     *
     * @param startDate LocalDateTime with the begin date
     * @param endDate   LocalDateTime with the end date
     */
    public void generateClientsReport(LocalDateTime startDate, LocalDateTime endDate) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("data/reports/clientReport.csv");
        String separator = ";";
        clientBubbleSortName();
        String columns = "Nombre y apellidos" + separator + "Identificación" + separator + "Telefono del cliente";
        pw.println(columns);
        for (Client client : clientsPL) {
            LocalDateTime dateOfClient = client.getEntryDate();
            if (dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
                pw.println(client.showInformation());
            }

        }
        pw.close();
    }

    /**
     * Generate a csv about the vehicle's general information <br>
     * <b> pre: </b>Needs a file in which write the information<br>
     * <b> post: </b>Generate the csv<br>
     *
     * @param startDate LocalDateTime with the begin date
     * @param endDate   LocalDateTime with the end date
     */
    public void generateVehiclesReport(LocalDateTime startDate, LocalDateTime endDate) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("data/reports/vehicleReport.csv");
        String separator = ";";
        vehicleInsertionSortByPlate();
        String columns = "Tipo de vehiculo" + separator + "Modelo" + separator + "Matricula" + separator + "Color" + separator
                + "Nombre de propietario" + separator + "Identificación propietario" + separator + "Fecha de entrada" + separator + "Fecha de salida" + "Valor a pagar";
        pw.println(columns);
        for (Vehicle vehicle : vehiclesPL) {
            LocalDateTime dateOfClient = vehicle.getEntryDate();
            if (dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
                pw.println(vehicle.showInformation());
            }

        }
        pw.close();
    }

    //So deep //yes sky daddy deeper

    /**
     * Generate a csv about the vehicle's monthly information <br>
     * <b> pre: </b>Needs a file in which write the information<br>
     * <b> post: </b>Generate the csv<br>
     *
     * @param startDate LocalDateTime with the begin date
     * @param endDate   LocalDateTime with the end date
     */
    public void reportInfoMonthly(LocalDateTime startDate, LocalDateTime endDate) throws FileNotFoundException {
        reportInfoMonthly(monthlyVehicles, startDate, endDate, false);
    }

    /**
     * Generate a csv about the vehicle's monthly information <br>
     * <b> pre: </b>Needs a file in which write the information<br>
     * <b> post: </b>Generate the csv<br>
     *
     * @param monthlyVehicles the node of the binary tree
     * @param startDate       LocalDateTime with the begin date
     * @param endDate         LocalDateTime with the end date
     */
    private void reportInfoMonthly(BTMonthly monthlyVehicles, LocalDateTime startDate, LocalDateTime endDate, boolean columnsVerifier) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("data/reports/vehicleMonthlyReport.csv");
        if (!this.columnsVerifier) {
            String separator = ";";
            String columns = "Tipo de vehiculo" + separator + "Modelo" + separator + "Matricula" + separator + "Color" + separator
                    + "Nombre de propietario" + separator + "Identificación propietario" + separator + "Fecha de entrada" + separator + "Fecha de salida";
            pw.println(columns);
            this.columnsVerifier = true;
        }
        String temp;
        if (monthlyVehicles != null) {
            if (monthlyVehicles.getBtVehicle() != null) {
                reportInfoMonthly((BTMonthly) monthlyVehicles.getLeft(), startDate, endDate, this.columnsVerifier);
                LocalDateTime dateOfClient = monthlyVehicles.getBtVehicle().getEntryDate();
                if (dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
                    temp = monthlyVehicles.getBtVehicle().getType().toString() + ";" + monthlyVehicles.getBtVehicle().getModel() + ";"
                            + monthlyVehicles.getBtVehicle().getLicensePlate() + ";" + monthlyVehicles.getBtVehicle() + ";" + monthlyVehicles.getBtVehicle().getOwner().getName()
                            + ";" + monthlyVehicles.getBtVehicle().getOwner().getId() + ";" + monthlyVehicles.getBtVehicle().getValueToPay()
                            + "\n";
                    pw.println(temp);
                }
                reportInfoMonthly((BTMonthly) monthlyVehicles.getRight(), startDate, endDate, this.columnsVerifier);
            }
        }
        pw.close();
    }


    /**
     * Generate a csv about the vehicle's per hour or daily information <br>
     * <b> pre: </b>Needs a file in which write the information<br>
     * <b> post: </b>Generate the csv<br>
     *
     * @param startDate LocalDateTime with the begin date
     * @param endDate   LocalDateTime with the end date
     */
    public void reportInfoDaily(LocalDateTime startDate, LocalDateTime endDate) throws FileNotFoundException {
        reportInfoDaily(perHourOrDailyVehicles, startDate, endDate, false);
    }


    /**
     * Generate a csv about the vehicle's per hour or daily information <br>
     * <b> pre: </b>Needs a file in which write the information<br>
     * <b> post: </b>Generate the csv<br>
     *
     * @param perHourOrDailyVehicles the node of the binary tree
     * @param startDate              LocalDateTime with the begin date
     * @param endDate                LocalDateTime with the end date
     */
    private void reportInfoDaily(BTHourlyOrDaily perHourOrDailyVehicles, LocalDateTime startDate, LocalDateTime endDate, boolean columnsVerifier) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("data/reports/vehiclePerHourOrDailyReport.csv");
        if (!columnsVerifier) {
            String separator = ";";
            String columns = "Tipo de vehiculo" + separator + "Modelo" + separator + "Matricula" + separator + "Color" + separator
                    + "Nombre de propietario" + separator + "Identificación propietario" + separator + "Fecha de entrada" + separator + "Fecha de salida";
            pw.println(columns);
        }
        String temp;
        if (perHourOrDailyVehicles != null) {
            if (perHourOrDailyVehicles.getBtVehicle() != null) {
                reportInfoDaily((BTHourlyOrDaily) perHourOrDailyVehicles.getLeft(), startDate, endDate, true);
                LocalDateTime dateOfClient = perHourOrDailyVehicles.getBtVehicle().getEntryDate();
                if (dateOfClient.isAfter(startDate) && dateOfClient.isBefore(endDate)) {
                    temp = perHourOrDailyVehicles.getBtVehicle().getType().toString() + ";" + perHourOrDailyVehicles.getBtVehicle().getModel() + ";"
                            + perHourOrDailyVehicles.getBtVehicle().getLicensePlate() + ";" + perHourOrDailyVehicles.getBtVehicle() + ";" + perHourOrDailyVehicles.getBtVehicle().getOwner().getName()
                            + ";" + perHourOrDailyVehicles.getBtVehicle().getOwner().getId() + ";" + perHourOrDailyVehicles.getBtVehicle().getValueToPay()
                            + "\n";
                    pw.println(temp);
                }
                reportInfoDaily((BTHourlyOrDaily) perHourOrDailyVehicles.getRight(), startDate, endDate, true);
            }
        }
        pw.close();
    }

    /**
     * Create an arraylist with the closest results <br>
     * <b> pre: </b>There had to be an array list to search in<br>
     * <b> post: </b>Initializes the array<br>
     *
     * @param match An String that is what is going to be searched
     */
    public void setSearchClientResults(String match) {
        searchClientResults = new ArrayList<>();
        for (Client c : clientsPL) {
            String compare = c.getId();
            if (compare.contains(match)) searchClientResults.add(c);
        }
    }

    /**
     * Create an arraylist with the closest results <br>
     * <b> pre: </b>There had to be an array list to search in<br>
     * <b> post: </b>Initializes the array<br>
     *
     * @param match An String that is what is going to be searched
     */
    public void setSearchEmployeeResults(String match) {
        searchEmployeeResults = new ArrayList<>();
        for (Employee c : employeesPL) {
            String compare = c.getId();
            if (compare.contains(match)) searchEmployeeResults.add(c);
        }
    }

    /**
     * Create an arraylist with the closest results <br>
     * <b> pre: </b>There had to be an array list to search in<br>
     * <b> post: </b>Initializes the array<br>
     *
     * @param match An String that is what is going to be searched
     */
    public void setSearchVehicleResults(String match) {
        searchVehicleResults = new ArrayList<>();
        for (Vehicle c : vehiclesPL) {
            String compare = c.getLicensePlate();
            if (compare.contains(match)) searchVehicleResults.add(c);
        }
    }

    /**
     * Adds an element to the ArrayList<br>
     * <b> pre: </b><br>
     * <b> post: </b>Initializes the array<br>
     *
     * @param toAdd A Client that is what was found
     */
    public void singleElementToSearchClient(Client toAdd) {
        searchClientResults = new ArrayList<>();
        searchClientResults.add(toAdd);
    }

    /**
     * Adds an element to the ArrayList<br>
     * <b> pre: </b><br>
     * <b> post: </b>Initializes the array<br>
     *
     * @param toAdd A Vehicle that is what was found
     */
    public void singleElementToSearchVehicle(Vehicle toAdd) {
        searchVehicleResults = new ArrayList<>();
        searchVehicleResults.add(toAdd);
    }

    /**
     * Adds an element to the ArrayList<br>
     * <b> pre: </b><br>
     * <b> post: </b>Initializes the array<br>
     *
     * @param toAdd An employee that is what was found
     */
    public void singleElementToSearchEmployee(Employee toAdd) {
        searchEmployeeResults = new ArrayList<>();
        searchEmployeeResults.add(toAdd);
    }

    /*                  Getters and setters                 */

    /**
     * @return The serial number. <br>
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return The clients of the parking lot. <br>
     */
    public ArrayList<Client> getClientsPL() {
        return clientsPL;
    }

    /**
     * @param clientsPL The clients of the parking lot. <br>
     */
    public void setClientsPL(ArrayList<Client> clientsPL) {
        this.clientsPL = clientsPL;
    }

    /**
     * @return The vehicles of the parking lot. <br>
     */
    public ArrayList<Vehicle> getVehiclesPL() {
        return vehiclesPL;
    }

    /**
     * @param vehiclesPL The vehicles of the parking lot. <br>
     */
    public void setVehiclesPL(ArrayList<Vehicle> vehiclesPL) {
        this.vehiclesPL = vehiclesPL;
    }

    /**
     * @return The employees of the parking lot. <br>
     */
    public ArrayList<Employee> getEmployeesPL() {
        return employeesPL;
    }

    /**
     * @param employeesPL The employees of the parking lot. <br>
     */
    public void setEmployeesPL(ArrayList<Employee> employeesPL) {
        this.employeesPL = employeesPL;
    }

    /**
     * @param plMap The map of the parking lot. <br>
     */
    public void setPlMap(ParkingLotMap plMap) {
        this.plMap = plMap;
    }

    /**
     * @return The hourly/daily vehicles of the parking lot. <br>
     */
    public BTHourlyOrDaily getPerHourOrDailyVehicles() {
        return perHourOrDailyVehicles;
    }

    /**
     * @param perHourOrDailyVehicles The hourly/daily vehicles of the parking lot. <br>
     */
    public void setPerHourOrDailyVehicles(BTHourlyOrDaily perHourOrDailyVehicles) {
        this.perHourOrDailyVehicles = perHourOrDailyVehicles;
    }

    /**
     * @return The monthly vehicles of the parking lot. <br>
     */
    public BTMonthly getMonthlyVehicles() {
        return monthlyVehicles;
    }

    /**
     * @param monthlyVehicles The monthly vehicles of the parking lot. <br>
     */
    public void setMonthlyVehicles(BTMonthly monthlyVehicles) {
        this.monthlyVehicles = monthlyVehicles;
    }

    /**
     * @return The root user. <br>
     */
    public Employee getRoot() {
        return root;
    }

    /**
     * @param root The root user. <br>
     */
    public void setRoot(Employee root) {
        this.root = root;
    }

    /**
     * @return The current employee. <br>
     */
    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    /**
     * @param currentEmployee The current employee. <br>
     */
    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    /**
     * @return whether or not the parking lot has been opened for the first time ever. <br>
     */
    public boolean isFirstTime() {
        return firstTime;
    }

    /**
     * @param firstTime whether or not the parking lot has been opened for the first time ever. <br>
     */
    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    /**
     * @return The column verifier. <br>
     */
    public boolean getColumnsVerifier() {
        return columnsVerifier;
    }

    /**
     * @param columnsVerifier The column verifier. <br>
     */
    public void setColumnsVerifier(boolean columnsVerifier) {
        this.columnsVerifier = columnsVerifier;
    }

    /**
     * @return The client search results. <br>
     */
    public ArrayList<Client> getSearchClientResults() {
        return searchClientResults;
    }

    /**
     * @param searchClientResults The client search results. <br>
     */
    public void setSearchClientResults(ArrayList<Client> searchClientResults) {
        this.searchClientResults = searchClientResults;
    }

    /**
     * @return The employee search results. <br>
     */
    public ArrayList<Employee> getSearchEmployeeResults() {
        return searchEmployeeResults;
    }

    /**
     * @param searchEmployeeResults The employee search results. <br>
     */
    public void setSearchEmployeeResults(ArrayList<Employee> searchEmployeeResults) {
        this.searchEmployeeResults = searchEmployeeResults;
    }

    /**
     * @return The vehicle search results. <br>
     */
    public ArrayList<Vehicle> getSearchVehicleResults() {
        return searchVehicleResults;
    }

    /**
     * @param searchVehicleResults The vehicle search results. <br>
     */
    public void setSearchVehicleResults(ArrayList<Vehicle> searchVehicleResults) {
        this.searchVehicleResults = searchVehicleResults;
    }

    /**
     * @return The hourly/daily vehicles. <br>
     */
    public ArrayList<Vehicle> getPerHODVehiclesPL() {
        return perHODVehiclesPL;
    }

    /**
     * @param perHODVehiclesPL The hourly/daily vehicles. <br>
     */
    public void setPerHODVehiclesPL(ArrayList<Vehicle> perHODVehiclesPL) {
        this.perHODVehiclesPL = perHODVehiclesPL;
    }

    /**
     * @return The monthly vehicles. <br>
     */
    public ArrayList<Vehicle> getMonthlyVehiclesPL() {
        return monthlyVehiclesPL;
    }

    /**
     * @param monthlyVehiclesPL The monthly vehicles. <br>
     */
    public void setMonthlyVehiclesPL(ArrayList<Vehicle> monthlyVehiclesPL) {
        this.monthlyVehiclesPL = monthlyVehiclesPL;
    }
}
