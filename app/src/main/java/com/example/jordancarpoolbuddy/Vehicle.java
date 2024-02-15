package com.example.jordancarpoolbuddy;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * @author Jordan Ren
 * @version 1.0
 */
public class Vehicle implements Serializable {
    private String owner;
    private String model;
    private int capacity;
    private String vehicleID;
    private ArrayList<String> ridersUIDs;

    private boolean open;

    public Vehicle(){};

    /**
     * A getter method for the Owner property
     * @return String
     */
    public String getOwner() {
        return owner;
    }

    /**
     * A setter method for the Owner property
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * A getter method for the Model property
     * @return String
     */
    public String getModel() {
        return model;
    }

    /**
     * A setter method for the Model property
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * A getter method for the Capacity property
     * @return int
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * A setter method for the Capacity property
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * A getter method for the VehicleID property
     * @return String
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * A setter method for the VehicleID property
     * @param vehicleID
     */
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * A getter method for the ridersEmail property
     * @return ArrayList<String>
     */
    public ArrayList<String> getRidersEmails() {
        return ridersUIDs;
    }

    /**
     * A setter method for the ridersEmail property
     * @param ridersUIDs
     */
    public void setRidersEmails(ArrayList<String> ridersUIDs) {
        this.ridersUIDs = ridersUIDs;
    }

    /**
     * A getter method for the Open property
     * @return boolean
     */
    public Boolean isOpen() {
        return open;
    }

    /**
     * A setter method for the Open property
     * @param open
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * A getter method for the VehicleType property
     * @return String
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * A setter method for the VehicleType property
     * @param vehicleType
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * A getter method for the BasePrice property
     * @return double
     */
    public Double getBasePrice() {
        return basePrice;
    }

    /**
     * A setter method for the BasePrice property
     * @param basePrice
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    private String vehicleType;
    private Double basePrice;

    /**
     * A constructor that initializes all properties
     * @param owner
     * @param model
     * @param capacity
     * @param vehicleID
     * @param open
     * @param vehicleType
     * @param basePrice
     */
    public Vehicle(String owner, String model, int capacity, String vehicleID, boolean open, String vehicleType, double basePrice) {
        this.owner = owner;
        this.model = model;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.open = open;
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
    }
}
