package nl.hogeschoolrotterdam.dockdocker.serversmanagement;

import com.google.gson.Gson;
import java.sql.ResultSet;
import java.util.ArrayList;
import static spark.Spark.*;
import utils.RequestHandler;
/**
 *
 * @author Ivan
 */

public class ServerManagementMain {
    
    private static Database database = null;
    
    public static void main(String[] args) {
        
        //  port(5678); <- Uncomment this if you want spark to listen on a port different than 4567
        
        RequestHandler handler = new RequestHandler();
        
        get("/s_servers", "application/json", (request, response) -> {
            return handler.getServers();
        });
        
        get("/s_deleteServer/:id", "application/json", (request, response) -> {
            String id = request.params(":id");
            return handler.deleteServer(id);
        });
        
        get("/s_addServer/:serverName/:serverIp/:dockerStatus", "application/json", (request, response) -> {
            String name = replaceString(request.params(":serverName"));
            String ip = replaceString(request.params(":serverIp"));
            String status = replaceString(request.params(":dockerStatus"));
            
            return handler.addServer(name, ip, status);
        });
        
        /*
        *   When spark works
        *   delete everything below and change s_links
        *   and delete Database.java
        */
        
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
            String name = replaceString(request.params(":serverName"));
            String ip = replaceString(request.params(":serverIp"));
            String status = replaceString(request.params(":dockerStatus"));
            
            if(database.INSERT_SERVER_DATA(name, ip, status)){
                response.status(200);
                return "Success! ";
            } else {
                response.status(400);
                return "Failed!";
            }
        });
    }
    
    private static String replaceString (String value) {
        String replacedData = value.
                    replace("%7Bcomma%7D", ", ").
                    replace("%7Bslash%7D", "/").
                    replace("{comma}", ", ").
                    replace("{slash}", "/").
                    replace("%20", " ");
        
        return replacedData;
    }
    
}