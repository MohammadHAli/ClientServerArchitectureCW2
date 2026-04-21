/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

/**
 *
 * @author w2074744
 */
import com.smartcampus.data.DataStore;
import com.smartcampus.exception.LinkedResourceNotFoundException;
import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SensorResource {

    @GET
    public Response getAllSensors(@QueryParam("type") String type) {

        List<Sensor> sensorList = new ArrayList<>(DataStore.sensors.values());

        if (type != null && !type.isEmpty()) {
            List<Sensor> filtered = new ArrayList<>();
            for (Sensor s : sensorList) {
                if (s.getType().equalsIgnoreCase(type)) {
                    filtered.add(s);
                }
            }
            return Response.ok(filtered).build();
        }
        return Response.ok(sensorList).build();
    }

    @POST
    public Response createSensor(Sensor sensor) {
        if (DataStore.sensors.containsKey(sensor.getId())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"Room with this ID already exists\"}").build();
        }

        if (!DataStore.rooms.containsKey(sensor.getRoomID())) {
            throw new LinkedResourceNotFoundException(
                    "Room with ID " + sensor.getRoomID() + " does not exist.");
        }

        DataStore.sensors.put(sensor.getId(), sensor);
        DataStore.rooms.get(sensor.getRoomID()).getSensorIds().add(sensor.getId());
        DataStore.readings.put(sensor.getId(), new ArrayList<>());

        return Response.status(Response.Status.CREATED)
                .entity(sensor).build();
    }

    @GET
    @Path("/{sensorId}")
    public Response getSensorByID(@PathParam("sensorId") String sensorId) {
        Sensor sensor = DataStore.sensors.get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Sensor not found\"}").build();
        }

        return Response.ok(sensor).build();
    }
    
    @DELETE
    @Path("/{sensorId}")
    public Response deleteSensor(@PathParam("sensorId") String sensorId){
        
        Sensor sensor = DataStore.sensors.get(sensorId);
        
        if(sensor == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Sensor not found\"}")
                    .build();
        }
        
         String roomId = sensor.getRoomID();
        if (roomId != null && DataStore.rooms.containsKey(roomId)) {
            DataStore.rooms.get(roomId).getSensorIds().remove(sensorId);
        }
        
        DataStore.sensors.remove(sensorId);
        DataStore.readings.remove(sensorId);
        
        return Response.ok("{\"message\": \"Sensor "+ sensorId + "deleted successfully\"}").build();
        
    }
    
    @Path("/{sensorID}/readings")
    public SensorReadingResource getReadingResource(@PathParam("sensorId") String sensorId){
        return new SensorReadingResource(sensorId);
    }
}
