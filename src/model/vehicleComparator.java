package model;

import java.util.Comparator;

/**
 * The comparator for the class Vehicle. <br>
 */
public class vehicleComparator implements Comparator<Vehicle> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return (o1.getModel().compareTo(o2.getModel()));
    }

}
