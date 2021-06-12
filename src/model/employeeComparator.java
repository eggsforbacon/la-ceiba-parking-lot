package model;

import java.util.Comparator;

/**
 * The comparator associated to the employee class. <br>
 */
public class employeeComparator implements Comparator<Employee> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(Employee o1, Employee o2) {
        return (o1.getName().compareTo(o2.getName()));
    }
}
