package model;

/**
 * Abstract methods to be used by the vehicle classes. <br>
 */
public interface ChildrenVehicleMethods {
    StayTime changeStayTime();

    double calculateValueAdditionalTime();

    double calculateMinimumValue(long months, long days, long hours);
}
