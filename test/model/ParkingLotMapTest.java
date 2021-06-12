package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotMapTest {

    private ParkingLotMap plMap;
    public void parkingLotMapTestSetupScenary1(){
        plMap = new ParkingLotMap();
    }

    @Test
    void setLeftColumnTest1() {
        parkingLotMapTestSetupScenary1();
        plMap.setLeftColumn();
        assertNotEquals(plMap.getLeftColumn(),null);
        assertEquals("-10 -9",plMap.getLeftColumn().getActualPosition());
    }

    //Comentario para arreglar commits como cosa rara

    //a

    @Test
    void setBottomRowTest1() {
        parkingLotMapTestSetupScenary1();
        plMap.setBottomRow();
        assertNotEquals(plMap.getBottomRow(),null);
        assertEquals("10",plMap.getBottomRow().getActualPosition());
    }

    @Test
    void setRightColumn1() {
        parkingLotMapTestSetupScenary1();
        plMap.setRightColumn();
        assertNotEquals(plMap.getRightColumn(),null);
        assertEquals("17",plMap.getRightColumn().getActualPosition());
    }

    @Test
    void spotAtTest1() {
        parkingLotMapTestSetupScenary1();
        assertEquals(plMap.spotAt(7).getActualPosition(),"7");
    }

    @Test
    void spotAtTest2() {
        parkingLotMapTestSetupScenary1();
        assertEquals(plMap.spotAt(-4).getActualPosition(),"-4 -3");
    }

    @Test
    void uniteLeftAndRightTest1() {
        parkingLotMapTestSetupScenary1();
        plMap.uniteLeftAndRight();
        assertEquals(plMap.spotAt(4).getRight().getActualPosition(),"21");
    }

    @Test
    void uniteLeftAndRightTest2() {
        parkingLotMapTestSetupScenary1();
        plMap.uniteLeftAndRight();
        assertEquals(plMap.spotAt(-2).getRight().getActualPosition(),"24");
    }
}