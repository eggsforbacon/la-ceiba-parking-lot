package model;

import java.util.Comparator;

public class vehicleComparator implements Comparator<Vehicle>{

	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return (o1.getModel().compareTo(o2.getModel()));
	}

}
