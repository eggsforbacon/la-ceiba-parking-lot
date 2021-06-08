package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.IDAlreadyInUseException;
import exceptions.NameAlreadyInUseException;
import exceptions.NotAllowedException;
import exceptions.PasswordAlreadyInUseException;
import exceptions.UsernameAlreadyInUseException;

class ParkingLotTest {

	ParkingLot parkingLotTest=new ParkingLot();
	
	public void parkingLotScenary1() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotTest.addClient("a", "1", "2");
	}
	
	public void parkingLotScenary2() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotTest.addEmployee("a", "1", "user","pass");
	}
	
	public void parkingLotScenary3() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotTest.addClient("a", "1", "2");
		parkingLotTest.addVehicle(0,"a","aaa","b",parkingLotTest.searchByName("a"),2,1,2);
	}
	
	public void parkingLotScenary4() throws NameAlreadyInUseException, IDAlreadyInUseException, NotAllowedException {
		parkingLotTest.addClient("a", "1", "2");
		parkingLotTest.addVehicle(0,"a","aaa","b",parkingLotTest.searchByName("a"),2,2,2);
	}
	
	
	
	//Client tests
	//
	//
	
	@Test
	void addClientTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		assertTrue(parkingLotTest.addClient("b", "3", "4"));
	}
	
	@Test
	void disableClientByNameTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		assertTrue(parkingLotTest.disableClientByName("a"));
	}
	
	@Test
	void disableClientByIDTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		assertTrue(parkingLotTest.disableClientByID("1"));
	}
	
	@Test
	void updateClientNameTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		assertTrue(parkingLotTest.updateClientName("1","c"));
	}
	
	@Test
	void updateClientIDTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		assertTrue(parkingLotTest.updateClientID("1","3"));
	}
	
	@Test
	void updateClientCellNumberTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		assertTrue(parkingLotTest.updateClientCellNumber("1","4"));
	}
	
	@Test
	void clientVeryfierTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		String nameVeryfier="name";
		assertEquals(parkingLotTest.ClientVeryfier("a","2","3"),nameVeryfier);
	}
	
	@Test
	void searchByIDTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		Client clientTest=parkingLotTest.searchByName("a");
		assertEquals(parkingLotTest.searchByID("1"),clientTest);
	}
	
	@Test
	void searchByNameTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		Client clientTest=parkingLotTest.searchByID("1");
		assertEquals(parkingLotTest.searchByName("a"),clientTest);
	}
	
	//Employee tests
	//
	//
	
	@Test
	void addEmployeeTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertTrue(parkingLotTest.addEmployee("b", "2", "name","word"));
	}
	
	@Test
	void disableEmployeeByIDTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertTrue(parkingLotTest.disableemployeeByID("1"));
	}
	
	@Test
	void disableEmployeeByNameTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertTrue(parkingLotTest.disableemployeeByName("a"));
	}
	
	@Test
	void updateEmployeeNameTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertTrue(parkingLotTest.updateEmployeeName("1","c"));
	}
	
	@Test
	void updateEmployeeIDTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertTrue(parkingLotTest.updateEmployeeID("1","3"));
	}
	
	@Test
	void updateEmployeeUsernameTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertTrue(parkingLotTest.updateEmployeeUsername("1","name","user","pass"));
	}
	
	@Test
	void updateEmployeePasswordTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertTrue(parkingLotTest.updateEmployeePassword("1","user","pass","new"));
	}
	
	@Test
	void employeeVeryfierTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertEquals(parkingLotTest.employeeVeryfier("a", "","", ""),"name");
	}
	
	@Test
	void employeeVeryfierLoginTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		assertTrue(parkingLotTest.employeeVeryfierLogin("user", "pass"));
	}
	
	@Test
	void searchEmployeeByNameTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		Employee aux=parkingLotTest.searchEmployeeByID("1");
		assertEquals(parkingLotTest.searchEmployeeByName("a"),aux);
	}
	
	@Test
	void searchEmployeeByIDTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		Employee aux=parkingLotTest.searchEmployeeByName("a");
		assertEquals(parkingLotTest.searchEmployeeByID("1"),aux);
	}
	
	//vehicles tests
	//
	//
	
	@Test
	void addVehicleTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		assertTrue(parkingLotTest.addVehicle(0,"a","bbb","b",parkingLotTest.searchByName("a"),3,1,2));
	}
	
	@Test
	void disableVehicleTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		assertTrue(parkingLotTest.disableVehicleByPlate("aaa"));
	}
	
	@Test
	void updateVehicleModelTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		assertTrue(parkingLotTest.updateVehicleModel("aaa","aveo"));
	}
	
	@Test
	void updateVehicleColorTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		assertTrue(parkingLotTest.updateVehicleColor("aaa","negro"));
	}
	
	@Test
	void updateVehicleSpotTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		assertTrue(parkingLotTest.updateSpot("aaa",3));
	}
	
	
	
	@Test
	void verifySpotTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		assertTrue(parkingLotTest.verifySpot(10));
	}
	
	@Test
	void verifyVehicleByPlateTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		assertFalse(parkingLotTest.verifyVehicleByPlate("aaa"));
	}
	
	@Test
	void addAVehicleToPerHourOrDailyVehiclesTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		assertEquals(parkingLotTest.getPerHourOrDailyVehicles().getBtVehicle().getLicensePlate(),"aaa");
	}
	
	@Test
	void addAVehicleToMonthlyVehiclesTest() throws NameAlreadyInUseException, IDAlreadyInUseException, NotAllowedException {
		parkingLotScenary4();
		assertEquals(parkingLotTest.getMonthlyVehicles().getBtVehicle().getLicensePlate(),"aaa");
	}
	
	@Test
	void clientBubbleSortNameTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		Client aux=parkingLotTest.getClientsPL().get(0);
		parkingLotTest.addClient("b", "2", "3");
		parkingLotTest.clientBubbleSortName();
		assertTrue(parkingLotTest.getClientsPL().get(0)==aux);
	}
	
	@Test
	void clientBubbleSortByIDTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		Client aux=parkingLotTest.getClientsPL().get(0);
		parkingLotTest.addClient("b", "2", "3");
		parkingLotTest.clientBubbleSortByID();
		assertTrue(parkingLotTest.getClientsPL().get(0)==aux);
	}
	
	@Test
	void vehicleInsertionSortByPlateTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		Vehicle aux=parkingLotTest.getVehiclesPL().get(0);
		parkingLotTest.addClient("b", "2", "3");
		parkingLotTest.addVehicle(0,"a","bbb","b",parkingLotTest.searchByName("b"),2,1,2);
		parkingLotTest.vehicleInsertionSortByPlate();
		assertTrue(parkingLotTest.getVehiclesPL().get(0)==aux);
	}
	
	@Test
	void vehicleInsertionSortByOwnerTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		Vehicle aux=parkingLotTest.getVehiclesPL().get(0);
		parkingLotTest.addClient("b", "2", "3");
		parkingLotTest.addVehicle(0,"a","bbb","b",parkingLotTest.searchByName("b"),2,1,2);
		parkingLotTest.vehicleInsertionSortByOwner();
		assertTrue(parkingLotTest.getVehiclesPL().get(0)==aux);
	}
	
	@Test
	void employeeselectionSortByUsernameTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		Employee aux=parkingLotTest.getEmployeesPL().get(0);
		parkingLotTest.addEmployee("b", "2", "tame","word");
		parkingLotTest.employeeselectionSortByUsername();
		assertTrue(parkingLotTest.getEmployeesPL().get(0)==aux);
	}
	
	@Test
	void employeeselectionSortByIdTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		Employee aux=parkingLotTest.getEmployeesPL().get(0);
		parkingLotTest.addEmployee("b", "2", "tame","word");
		parkingLotTest.employeeselectionSortById();
		assertTrue(parkingLotTest.getEmployeesPL().get(0)==aux);
	}
	
	@Test
	void sortEmployeeByNameTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		Employee aux=parkingLotTest.getEmployeesPL().get(0);
		parkingLotTest.addEmployee("b", "2", "tame","blah");
		parkingLotTest.sortEmployeeByName();
		assertTrue(parkingLotTest.getEmployeesPL().get(0)==aux);
	}
	
	@Test
	void sortVehicleByModelTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		Vehicle aux=parkingLotTest.getVehiclesPL().get(0);
		parkingLotTest.addVehicle(0,"c","bbb","b",parkingLotTest.searchByName("b"),2,1,2);
		parkingLotTest.sortVehicleByModel();
		assertTrue(parkingLotTest.getVehiclesPL().get(0)==aux);
	}
	
	@Test
	void binarySearchVehicleTest() throws NotAllowedException, NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary3();
		parkingLotTest.addVehicle(0,"c","bbb","b",parkingLotTest.searchByName("b"),2,1,2);
		parkingLotTest.sortVehicleByModel();
		ArrayList<Vehicle> temp=parkingLotTest.getVehiclesPL();
		assertTrue(parkingLotTest.binarySearchVehicle(temp, "bbb")==1);
	}
	
	@Test
	void binarySearchPersonTest() throws NameAlreadyInUseException, IDAlreadyInUseException {
		parkingLotScenary1();
		parkingLotTest.addClient("b", "2", "3");
		parkingLotTest.clientBubbleSortName();
		ArrayList<Client> temp=parkingLotTest.getClientsPL();
		assertTrue(parkingLotTest.binarySearchPerson(temp, "b")==1);
	}
	
	@Test
	void loginTest() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		parkingLotTest.addEmployee("b", "2", "tame","blah");
		parkingLotTest.sortEmployeeByName();
		assertTrue(parkingLotTest.login("tame", "blah")==1);
	}
	
	@Test
	void loginTest2() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		parkingLotTest.addEmployee("b", "2", "tame","blah");
		parkingLotTest.sortEmployeeByName();
		assertTrue(parkingLotTest.login("user", "pass")==0);
	}
	
	@Test
	void loginTest3() throws NameAlreadyInUseException, IDAlreadyInUseException, UsernameAlreadyInUseException, PasswordAlreadyInUseException {
		parkingLotScenary2();
		parkingLotTest.addEmployee("b", "2", "tame","blah");
		parkingLotTest.sortEmployeeByName();
		assertTrue(parkingLotTest.login("admin","1234")==-2);
	}
	
}
