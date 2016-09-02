package com.woolworthspetinsurance.datatable;

public class Pet {
	private String species;
    private String name;
    private String breed;
    private Integer day;
    private Integer month;
    private Integer year;
    private String gender;
    private String desexed;

    public Pet(String species, String name, String breed, Integer day, Integer month, Integer year, String gender, String desexed) {
        this.species = species;
        this.name = name;
        this.breed = breed;
        this.day = day;
        this.month = month;
        this.year = year;
        this.gender = gender;
        this.desexed = desexed;
    }

    public String getSpecies() {
        return species;
    }
    
    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Integer getDay() {
        return day;
    }
    public Integer getMonth() {
        return month;
    }
    public Integer getYear() {
        return year;
    }

    public String getGender() {
        return gender;
    }
    
    public String getDeSexed() {
        return desexed;
    }

}
