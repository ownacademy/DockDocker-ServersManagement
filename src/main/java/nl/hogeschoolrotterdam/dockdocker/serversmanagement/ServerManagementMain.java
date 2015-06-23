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
    
    public static void main(String[] args) {
        
        port(5100); // <- Uncomment this if you want spark to listen on a port different than 4567
        
        RequestHandler handler = new RequestHandler();
                
        get("/post_servers", "application/json", (request, response) -> {
            return handler.postServers();
        });
        
        get("/post_deleteServer/:id", "application/json", (request, response) -> {
            String id = request.params(":id");
            return handler.postDeleteServer(id);
        });
        
        get("/post_addServer/:username/:password/:serverName/:serverIp/:dockerStatus",
                "application/json", (request, response) -> {
            String username = request.params(":username");
            String password = request.params(":password");
            String name = request.params(":serverName");
            String ip = request.params(":serverIp");
            String status = request.params(":dockerStatus");
            
            return handler.postAddServer(username, password, name, ip, status);
        });
        
    }  
}