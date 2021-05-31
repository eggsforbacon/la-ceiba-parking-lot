package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LargeVehicleTest {

    LocalDateTime dateTime;

    public void largeVehicleSetupScenary1() {
        String str = "2021-05-27 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = LocalDateTime.parse(str, formatter);
    }

    public void largeVehicleSetupScenary2() {
        //Nothing, oh yeah
    }

    @Test
    void calculateValueToPayTest1() {
        largeVehicleSetupScenary2();
        //1 hora
        //Camion
        LargeVehicle testVehicle = new LargeVehicle(2,"a","a","a",null,1,"",0,1);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),3000);
    }
    @Test
    void calculateValueToPayTest2() {
        largeVehicleSetupScenary2();
        //2 hora
        //Camion
        LargeVehicle testVehicle = new LargeVehicle(2,"a","a","a",null,1,"",0,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),4000);
    }

    @Test
    void calculateValueToPayTest3() {
        largeVehicleSetupScenary2();
        //5 hora
        //Camion
        LargeVehicle testVehicle = new LargeVehicle(2,"a","a","a",null,1,"",0,5);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),7000);
    }

    @Test
    void calculateValueToPayTest4() {
        largeVehicleSetupScenary2();
        //11 horas
        //Camion
        LargeVehicle testVehicle = new LargeVehicle(2,"a","a","a",null,1,"",0,11);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),12000);
    }

    @Test
    void calculateValueToPayTest5() {
        largeVehicleSetupScenary1();
        //2 hora
        //Camion
        //No se cumplio con el tiempo definido
        LargeVehicle testVehicle = new LargeVehicle(2,"a","a","a",null,1,"",0,2);
        testVehicle.setEntryDate(dateTime);
        testVehicle.calculateValueToPay();
        assertNotEquals(testVehicle.getValueToPay(),4000);
    }

    @Test
    void calculateValueToPayTest6() {
        largeVehicleSetupScenary2();
        //2 dias
        //Bus
        LargeVehicle testVehicle = new LargeVehicle(3,"a","a","a",null,1,"",1,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),24000);
    }

    @Test
    void calculateValueToPayTest7() {
        largeVehicleSetupScenary2();
        //2 meses
        //Bus
        LargeVehicle testVehicle = new LargeVehicle(3,"a","a","a",null,1,"",2,2);
        testVehicle.calculateValueToPay();
        assertEquals(testVehicle.getValueToPay(),120000);
    }

}