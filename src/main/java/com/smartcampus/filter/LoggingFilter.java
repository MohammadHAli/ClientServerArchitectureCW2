/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.filter;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        String method = requestContext.getMethod();

        String uri = requestContext.getUriInfo().getRequestUri().toString();

        LOGGER.info("Incoming Request: [" + method + "] " + uri);
    }

    @Override
    public void filter(
            
            ContainerRequestContext requestContext,
            
            ContainerResponseContext responseContext) throws IOException {
        
        String method = requestContext.getMethod();
        
        String uri = requestContext.getUriInfo().getRequestUri().toString();
        
        int status = responseContext.getStatus();
        
        LOGGER.info("Outgoing Response: [" + method + "] " + uri + " -> Status: " + status);
    }
}
