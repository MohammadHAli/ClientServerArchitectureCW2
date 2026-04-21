/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.data;

import com.smartcampus.model.Room;
import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author w2074744
 */
public class DataStore {
    
    public static final Map<String, Room> rooms = new ConcurrentHashMap<>();
    public static final Map<String, Sensor> sensors = new ConcurrentHashMap<>();
    public static final Map<String, List<SensorReading>> readings = new ConcurrentHashMap<>();
    
    static {
    // Create two sample rooms
    Room room1 = new Room("LIB-301", "Library Quiet Study", 50);
    Room room2 = new Room("LAB-101", "Computer Lab", 30);

    // Add them to the rooms map
    rooms.put(room1.getId(), room1);
    rooms.put(room2.getId(), room2);

    // Create two sample sensors using setters instead of constructor
    Sensor sensor1 = new Sensor();
    sensor1.setId("TEMP-001");
    sensor1.setType("Temperature");
    sensor1.setStatus("ACTIVE");
    sensor1.setCurrentValue(21.5);
    sensor1.setRoomId("LIB-301");

    Sensor sensor2 = new Sensor();
    sensor2.setId("CO2-001");
    sensor2.setType("CO2");
    sensor2.setStatus("ACTIVE");
    sensor2.setCurrentValue(450.0);
    sensor2.setRoomId("LIB-301");

    // Add sensors to the sensors map
    sensors.put(sensor1.getId(), sensor1);
    sensors.put(sensor2.getId(), sensor2);

    // Link sensor IDs to room1 so the room knows what sensors it has
    room1.getSensorIds().add(sensor1.getId());
    room1.getSensorIds().add(sensor2.getId());

    // Initialise empty reading lists for each sensor
    readings.put(sensor1.getId(), new ArrayList<>());
    readings.put(sensor2.getId(), new ArrayList<>());
}
    
}
