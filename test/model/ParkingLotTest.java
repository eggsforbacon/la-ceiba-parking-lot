package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParkingLotTest {

	ParkingLot parkingLotTest=new ParkingLot();
	
	public void parkingLotScenary1() {
		parkingLotTest.addClient("a", "1", "2");
	}
	
	public void parkingLotScenary2() {
		parkingLotTest.addEmployee("a", "1", "user","pass");
	}
	
	public void parkingLotScenary3() {
		parkingLotTest.addClient("a", "1", "2");
		parkingLotTest.addVehicle(0,"a","aaa","b",parkingLotTest.searchByName("a"),2,"None",1,2);
	}
	
	//Client tests
	//
	//
	
	@Test
	void addClientTest() {
		parkingLotScenary1();
		assertTrue(parkingLotTest.addClient("b", "3", "4"));
	}
	
	@Test
	void disableClientByNameTest() {
		parkingLotScenary1();
		assertTrue(parkingLotTest.disableClientByName("a"));
	}
	
	@Test
	void disableClientByIDTest() {
		parkingLotScenary1();
		assertTrue(parkingLotTest.disableClientByID("1"));
	}
	
	@Test
	void updateClientNameTest() {
		parkingLotScenary1();
		assertTrue(parkingLotTest.updateClientName("c","a"));
	}
	
	@Test
	void updateClientIDTest() {
		parkingLotScenary1();
		assertTrue(parkingLotTest.updateClientID("1","3"));
	}
	
	@Test
	void updateClientCellNumberTest() {
		parkingLotScenary1();
		assertTrue(parkingLotTest.updateClientCellNumber("1","4"));
	}
	
	@Test
	void clientVeryfierTest() {
		parkingLotScenary1();
		String nameVeryfier="name";
		assertEquals(parkingLotTest.ClientVeryfier("a","2","3"),nameVeryfier);
	}
	
	@Test
	void searchByIDTest() {
		parkingLotScenary1();
		Client clientTest=parkingLotTest.searchByName("a");
		assertEquals(parkingLotTest.searchByID("1"),clientTest);
	}
	
	@Test
	void searchByNameTest() {
		parkingLotScenary1();
		Client clientTest=parkingLotTest.searchByID("1");
		assertEquals(parkingLotTest.searchByName("a"),clientTest);
	}
	
	//Employee tests
	//
	//
	
	@Test
	void addEmployeeTest() {
		parkingLotScenary2();
		assertTrue(parkingLotTest.addEmployee("b", "2", "name","word"));
	}
	
	@Test
	void disableEmployeeByIDTest() {
		parkingLotScenary2();
		assertTrue(parkingLotTest.disableemployeeByID("1"));
	}
	
	@Test
	void disableEmployeeByNameTest() {
		parkingLotScenary2();
		assertTrue(parkingLotTest.disableemployeeByName("a"));
	}
	
	@Test
	void updateEmployeeNameTest() {
		parkingLotScenary2();
		assertTrue(parkingLotTest.updateEmployeeName("c","a"));
	}
	
	@Test
	void updateEmployeeIDTest() {
		parkingLotScenary2();
		assertTrue(parkingLotTest.updateEmployeeID("1","3"));
	}
	
	@Test
	void updateEmployeeUsernameTest() {
		parkingLotScenary2();
		assertTrue(parkingLotTest.updateEmployeeUsername("1","name","user","pass"));
	}
	
	@Test
	void updateEmployeePasswordTest() {
		parkingLotScenary2();
		assertTrue(parkingLotTest.updateEmployeePassword("1","user","pass","new"));
	}
	
	@Test
	void employeeVeryfierTest() {
		parkingLotScenary2();
		assertEquals(parkingLotTest.employeeVeryfier("a", "","", ""),"name");
	}
	
	@Test
	void employeeVeryfierLoginTest() {
		parkingLotScenary2();
		assertTrue(parkingLotTest.employeeVeryfierLogin("user", "pass"));
	}
	
	@Test
	void searchEmployeeByNameTest() {
		parkingLotScenary2();
		Employee aux=parkingLotTest.searchEmployeeByID("1");
		assertEquals(parkingLotTest.searchEmployeeByName("a"),aux);
	}
	
	@Test
	void searchEmployeeByIDTest() {
		parkingLotScenary2();
		Employee aux=parkingLotTest.searchEmployeeByName("a");
		assertEquals(parkingLotTest.searchEmployeeByID("1"),aux);
	}
	
	//vehicles tests
	//
	//
	
	@Test
	void addVehicleTest() {
		parkingLotScenary3();
		assertTrue(parkingLotTest.addVehicle(0,"a","bbb","b",parkingLotTest.searchByName("a"),3,"None",1,2));
	}
}
