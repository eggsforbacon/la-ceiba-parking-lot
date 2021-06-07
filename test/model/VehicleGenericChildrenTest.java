package model;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class VehicleGenericChildrenTest {

    LocalDateTime dateTime;

    public void vehicleSetupScenary1() {
        String str = "2021-05-27 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = LocalDateTime.parse(str, formatter);
    }

    public void vehicleSetupScenary2(){
        //Nothing, funny isn't it?  2// No it´s not funny
    }

    @Test
    void calculateExitDateTest1() {
        vehicleSetupScenary1();
        //Stay time: 1 Month
        VehicleGenericChildren  testVehicle = new VehicleGenericChildren(1,"a","a","a",null,1,2,1);
        testVehicle.setEntryDate(dateTime);
        testVehicle.calculateExitDate();
        String str = "2021-06-27 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime testDate = LocalDateTime.parse(str, formatter);
        assertEquals(testVehicle.getSupposedExitDate(),testDate);
    }

    @Test
    void calculateExitDateTest2() {
        vehicleSetupScenary1();
        //Stay time: 3 hours
        VehicleGenericChildren  testVehicle = new VehicleGenericChildren(1,"a","a","a",null,1,0,3);
        testVehicle.setEntryDate(dateTime);
        testVehicle.calculateExitDate();
        String str = "2021-05-27 14:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime testDate = LocalDateTime.parse(str, formatter);
        assertEquals(testVehicle.getSupposedExitDate(),testDate);
    }

    @Test
    void calculateExitDateTest3() {
        vehicleSetupScenary1();
        //Stay time: 5 days
        VehicleGenericChildren  testVehicle = new VehicleGenericChildren(1,"a","a","a",null,1,1,5);
        testVehicle.setEntryDate(dateTime);
        testVehicle.calculateExitDate();
        String str = "2021-06-01 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime testDate = LocalDateTime.parse(str, formatter);
        assertEquals(testVehicle.getSupposedExitDate(),testDate);
    }
    @Test
    void calculateExitDateTest4() {
        vehicleSetupScenary1();
        //Stay time: Undefined
        VehicleGenericChildren  testVehicle = new VehicleGenericChildren(1,"a","a","a",null,1,3,0);
        testVehicle.setEntryDate(dateTime);
        testVehicle.calculateExitDate();
        assertEquals(testVehicle.getSupposedExitDateString(),"No se puede calcular. Indefinido");
    }


    @Test
    void takeOutTest() {
        //Can fail but is extremely unlikely
        vehicleSetupScenary2();
        VehicleGenericChildren  testVehicle = new VehicleGenericChildren(1,"a","a","a",null,1,3,0);
        testVehicle.takeOut();
        assertTrue(!testVehicle.isEnabled());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String actualDate = LocalDateTime.now().format(formatter);
        assertEquals(testVehicle.getActualExitDateString(),actualDate);
    }

    @Test
    void checkAdditionalTimeTest1() {
        vehicleSetupScenary1();
        //Stay time: 2 hours
        VehicleGenericChildren  testVehicle = new VehicleGenericChildren(1,"a","a","a",null,1,0,2);
        testVehicle.setEntryDate(dateTime);
        testVehicle.calculateExitDate();
        testVehicle.checkAdditionalTime();
        assertTrue(testVehicle.isAdditionalTime());

    }

    @Test
    void checkAdditionalTimeTest2() {
        vehicleSetupScenary2();
        //Stay time: 2 hours
        VehicleGenericChildren  testVehicle = new VehicleGenericChildren(1,"a","a","a",null,1,0,2);
        testVehicle.checkAdditionalTime();
        assertFalse(testVehicle.isAdditionalTime());
    }

    @Test
    void checkAdditionalTimeTest3() {
        vehicleSetupScenary2();
        //Stay time: Undefined
        VehicleGenericChildren  testVehicle = new VehicleGenericChildren(1,"a","a","a",null,1,3,0);
        testVehicle.checkAdditionalTime();
        assertFalse(testVehicle.isAdditionalTime());
    }
}