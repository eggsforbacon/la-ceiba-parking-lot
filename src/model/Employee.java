package model;

/**
 * Represents an employee of the parking lot. <br>
 */
@SuppressWarnings("unused")
public class Employee extends Person implements Comparable<Employee> {

    private String username;
    private String password;
    private boolean state;

    /**
     * This is the main constructor of the class. <br>
     *
     * @param name     The name of the employee. @NotEmpty <br>
     * @param id       The id of the employee. @NotEmpty. Must <b>only contain digits</b>. <br>
     * @param username The username of the employee. @NotEmpty <br>
     * @param password The password of the employee. @NotEmpty
     */
    public Employee(String name, String id, String username, String password) {
        super(name, id);
        this.username = username;
        this.password = password;
        state = true;
    }

    /**
     * @return The state of the client. <br>
     */
    public boolean getState() {
        return state;
    }

    /**
     * @param state The state of the client. <br>
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * @return The password of the client. <br>
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return The username of the client. <br>
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username of the client. <br>
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the current status of the vehicle as a string value. <br>
     */
    public String getStatus() {
        if (state) {
            return "SI";
        } else {
            return "NO";
        }
    }

    /**
     * @param password The password of the employee. <br>
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Employee o) {
        return getUsername().compareToIgnoreCase(o.getUsername());
    }

    /**
     * @return The general information of the person as a String. <br>
     */
    @Override
    public String showInformation() {
        return getName() + ";" + getId() + ";" + getUsername();
    }
}
