/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.model;

/**
 *
 * @author w2074744
 */
public class Sensor {

    private String id;
    private String type;
    private String status;
    private double currentValue;
    private String RoomId;

    public Sensor() {
    }

    public Sensor(String id, String type, String status, double currentValue, String RoomId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.currentValue = currentValue;
        this.RoomId = RoomId;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public String getRoomID() {
        return RoomId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public void setRoomId(String roomId) {
        this.RoomId = RoomId;
    }

}
