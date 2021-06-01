package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SmallVehicleTest {
    LocalDateTime dateTime;

    public void smallVehicleSetupScenary1() {
        String str = "2021-05-27 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = LocalDateTime.parse(str, formatter);
    }

    public void smallVehicleSetupScenary2() {
        //Nothing, oh yeah
    }

    @Test
    void calculateValueToPayTest1() {
        smallVehicleSetupScenary2();
        //1 hora
        //Automovil
        SmallVehicle testVehicle = new SmallVehicle(0,"a","a","a",null,1,"",0,1);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),2000);
    }
    @Test
    void calculateValueToPayTest2() {
        smallVehicleSetupScenary2();
        //2 hora
        //Automovil
        SmallVehicle testVehicle = new SmallVehicle(0,"a","a","a",null,1,"",0,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),3000);
    }

    @Test
    void calculateValueToPayTest3() {
        smallVehicleSetupScenary2();
        //5 hora
        //Automovil
        SmallVehicle testVehicle = new SmallVehicle(0,"a","a","a",null,1,"",0,5);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),4500);
    }

    @Test
    void calculateValueToPayTest4() {
        smallVehicleSetupScenary2();
        //11 horas
        //Automovil
        SmallVehicle testVehicle = new SmallVehicle(0,"a","a","a",null,1,"",0,11);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),7500);
    }

    @Test
    void calculateValueToPayTest5() {
        smallVehicleSetupScenary1();
        //2 hora
        //Automovil
        //No se cumplio con el tiempo definido
        SmallVehicle testVehicle = new SmallVehicle(0,"a","a","a",null,1,"",0,2);
        testVehicle.setEntryDate(dateTime);
        testVehicle.calculateValueToPay();
        assertNotEquals(testVehicle.getValueToPay(),3000);
    }

    @Test
    void calculateValueToPayTest6() {
        smallVehicleSetupScenary2();
        //2 dias
        //Automovil
        SmallVehicle testVehicle = new SmallVehicle(0,"a","a","a",null,1,"",1,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),16000);
    }

    @Test
    void calculateValueToPayTest7() {
        smallVehicleSetupScenary2();
        //2 meses
        //Automovil
        SmallVehicle testVehicle = new SmallVehicle(0,"a","a","a",null,1,"",2,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),110000);
    }
}