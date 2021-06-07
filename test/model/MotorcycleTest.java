package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class MotorcycleTest {
    LocalDateTime dateTime;

    public void motorcycleSetupScenary1() {
        String str = "2021-05-27 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = LocalDateTime.parse(str, formatter);
    }

    public void motorcycleSetupScenary2() {
        //Nothing, oh yeah
    }

    @Test
    void calculateValueToPayTest1() {
        motorcycleSetupScenary2();
        //1 hora
        //motocicleta
        Motorcycle testVehicle = new Motorcycle(1,"a","a","a",null,1,0,1);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),1000);
    }
    @Test
    void calculateValueToPayTest2() {
        motorcycleSetupScenary2();
        //2 hora
        //motocicleta
        Motorcycle testVehicle = new Motorcycle(1,"a","a","a",null,1,0,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),1500);
    }

    @Test
    void calculateValueToPayTest3() {
        motorcycleSetupScenary2();
        //5 hora
        //motocicleta
        Motorcycle testVehicle = new Motorcycle(1,"a","a","a",null,1,0,5);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),3000);
    }

    @Test
    void calculateValueToPayTest4() {
        motorcycleSetupScenary2();
        //11 horas
        //motocicleta
        Motorcycle testVehicle = new Motorcycle(1,"a","a","a",null,1,0,11);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),4000);
    }

    @Test
    void calculateValueToPayTest5() {
        motorcycleSetupScenary1();
        //2 hora
        //motocicleta
        //No se cumplio con el tiempo definido
        Motorcycle testVehicle = new Motorcycle(1,"a","a","a",null,1,0,2);
        testVehicle.setEntryDate(dateTime);
        testVehicle.calculateValueToPay();
        assertNotEquals(testVehicle.getValueToPay(),1500);
    }

    @Test
    void calculateValueToPayTest6() {
        motorcycleSetupScenary2();
        //2 dias
        //motocicleta
        Motorcycle testVehicle = new Motorcycle(1,"a","a","a",null,1,1,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),8000);
    }

    @Test
    void calculateValueToPayTest7() {
        motorcycleSetupScenary2();
        //2 meses
        //motocicleta
        Motorcycle testVehicle = new Motorcycle(1,"a","a","a",null,1,2,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),60000);
    }
}