package com.example.jordancarpoolbuddy;

import java.util.ArrayList;
/**
 * @author Jordan Ren
 * @version 1.0
 */
public class User {
    private String name;
    private String email;
    private String type;
    private String password;
    private double budget;
    private ArrayList<String> vehicles;

    /**
     * This method is a constructor that initializes all properties
     * @param name
     * @param email
     * @param type
     * @param password
     * @param budget
     * @param vehicles
     */
    public User(String name, String email, String type, String password, double budget, ArrayList<String> vehicles) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.password = password;
        this.budget = budget;
        this.vehicles = vehicles;
    }

    /**
     * A getter method for the Name property
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * A setter method for the Name property
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A getter method for the Email property
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * A setter method for the Email property
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A getter method for the Type property
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * A setter method for the Type property
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * A getter method for the Password property
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * A setter method for the Password property
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * A getter method for the Budget property
     * @return double
     */
    public double getBudget() {
        return budget;
    }

    /**
     * A setter method for the Budget property
     * @param budget
     */
    public void setBudget(int budget) {
        this.budget = budget;
    }

    /**
     * A getter method for the Vehicles property
     * @return ArrayList<String></String>
     */
    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    /**
     * A setter method for the Vehicles property
     * @param vehicles
     */
    public void setVehicles(ArrayList<String> vehicles) {
        this.vehicles = vehicles;
    }
}
