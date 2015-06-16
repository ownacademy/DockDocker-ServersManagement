/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hogeschoolrotterdam.dockdocker.serversmanagement;

import com.google.gson.Gson;
import java.util.ArrayList;
import static spark.Spark.*;
/**
 *
 * @author Ivan
 */

public class ServerManagementMain {
    
    private static Database database = null;
    
    public static void main(String[] args) {
        
        //  port(5678); <- Uncomment this if you want spark to listen on a port different than 4567
        try {
            database = new Database();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        get("/servers", (request, response) ->  {
            response.status(200);
            return new Gson().toJson(database.SELECT_SERVER_DATA());            
        });

        get("/deleteServer/:id", (request, response) -> { 
            int id = Integer.parseInt(request.params(":id"));
            
            if(database.DELETE_SERVER_DATA(id)){
                response.status(200);
                return "Success! ";
            } else {
                response.status(400);
                return "Failed!";
            }
        });
        
        get("/addServer/:serverName/:serverIp/:dockerStatus", (request, response) -> { 
            String name = request.params(":serverName");
            String ip = request.params(":serverIp");
            String status = request.params(":dockerStatus");
            
            if(database.INSERT_SERVER_DATA(name, ip, status)){
                response.status(200);
                return "Success! ";
            } else {
                response.status(400);
                return "Failed!";
            }
        });
    }
    
}
