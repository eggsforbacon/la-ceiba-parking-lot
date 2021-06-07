package model;

import java.time.LocalDateTime;

public abstract class Person {
    private String name;
    private String id;
    private LocalDateTime entryDate;

    public Person(String name,String id){
        this.name = name;
        this.id=id;
        entryDate=LocalDateTime.now();
    }

    
	//Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

    public abstract String showInformation();
}
