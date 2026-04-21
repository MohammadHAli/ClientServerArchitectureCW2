/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
/**
 *
 * @author w2074744
 */
public class DiscoveryResource {

    @GET
    public Response discover() {

        Map<String, Object> response = new HashMap<>();

        response.put("name", "Smart Campus API");
        response.put("version", "1.0");
        response.put("description", "A RESTful API designed to manage capus rooms and sensors");

        Map<String, String> contact = new HashMap<>();
        contact.put("Name", "Campus Facilities Manager");
        contact.put("Email", "admin@smartcampus.ac.uk");
        contact.put("Department", "University IT Services");
        response.put("Contact", contact);

        Map<String, String> links = new HashMap<>();
        links.put("Rooms", "/api/v1/rooms");
        links.put("Sensors", "/api/v1/sensors");
        response.put("resources", links);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("status", "running");
        metadata.put("timesatamp", String.valueOf(System.currentTimeMillis()));
        response.put("metadata", metadata);

        return Response.ok(response).build();

    }

}
