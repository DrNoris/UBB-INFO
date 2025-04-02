package org.example.practic.Domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Cursa {
    private int id;
    private int driverId;
    private String status;
    private Timestamp startDate;
    private Timestamp endDate;
    private String pickupAddress;
    private String destinationAddress;
    private String clientName;

    // Constructor
    public Cursa(int id, int driverId, String status, Timestamp startDate, Timestamp endDate,
                 String pickupAddress, String destinationAddress, String clientName) {
        this.id = id;
        this.driverId = driverId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupAddress = pickupAddress;
        this.destinationAddress = destinationAddress;
        this.clientName = clientName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    // Override toString for better debugging
    @Override
    public String toString() {
        return "Cursa{" +
                "id=" + id +
                ", driverId=" + driverId +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
