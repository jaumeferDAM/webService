/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import bd.Autobus;
import bd.Conexion;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author 
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
  
    

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarAutobuses ()
    {
        
        Conexion conexion = new Conexion();
        List<Autobus> lista = null;
        try {
            lista = conexion.obtenerAutobuses();
            
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        Gson gson = new Gson();
        
        return gson.toJson(lista);
    }
         
       

    /**
     * PUT method for updating or creating an instance of GenericResource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean insertarAutobus (String cli) {
         Conexion conexion = new Conexion();
         Gson gson = new Gson();
         Autobus autobus;
         autobus = gson.fromJson(cli, Autobus.class);
         boolean result = true;
        try {
          
            conexion.ActualizarAutobus(autobus);
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            result = false;             
        }
        return result;
    }
     
    @GET
     @Path("{id}")
     @Produces(MediaType.APPLICATION_JSON)
     public String mostrarCliente(@PathParam("id") int id)
     {
         Autobus cli = null;
          Conexion conexion = new Conexion();
         try
         {
             cli = conexion.obtenerAutobus(id);
         }
         catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
         }
         Gson gson = new Gson();
         
         return gson.toJson(cli);
     }
     
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
     public boolean ActualizarAutobus(String cli)
     {
          Conexion conexion = new Conexion();
         Gson gson = new Gson();
         Autobus autobus;
         autobus = gson.fromJson(cli, Autobus.class);
         boolean result = true;
        try {
          
            conexion.insertarCliente(autobus);
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
            
            
            
        }
        return result;
     }
     
@DELETE
@Path("/delete/{id}")
 
  
     public boolean eliminarAutobus( @PathParam("id")int id)
     {
          Conexion conexion = new Conexion();
          boolean result = true;
            
        try {
          
            conexion.eliminarAutobus(id);
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
            
        }
        return result;
     }
}
