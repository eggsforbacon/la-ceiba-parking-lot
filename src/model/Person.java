package model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class represents a person generally and all their attributes and functionality. <br>
 */
@SuppressWarnings("unused")
public abstract class Person implements Serializable {
    private String name;
    private String id;
    private LocalDateTime entryDate;

    /**
     * This is the main constructor of the class. <br>
     *
     * @param name The name of the person. @NotEmpty. <br>
     * @param id   The id of the person. @NotEmpty. Must <b>NOT contain letters</b>. <br>
     */
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
        entryDate = LocalDateTime.now();
    }


    //Getters and setters

    /**
     * @return The name of the person. <br>
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name of the person. <br>
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The id of the person. <br>
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The name of the person. <br>
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The entry date of the person to the parking lot as a LocalDateTime object. <br>
     */
    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate The entry date of the person to the parking lot as a LocalDateTime object. <br>
     */
    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @return The general information of the person as a String. <br>
     */
    public abstract String showInformation();
}
