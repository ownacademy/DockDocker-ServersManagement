package nl.hogeschoolrotterdam.dockdocker.serversmanagement;

import com.google.gson.Gson;
import java.sql.ResultSet;
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
//            try {
//                ResultSet results = Database.getInstance().executeSelectQuery("SELECT * FROM list");
//                ArrayList<ServerData> serverData = new ArrayList<>();
//                while(results.next()){
//                   int id  = results.getInt("id");
//                   String name = results.getString("server_name");
//                   String ip = results.getString("server_ip");
//                   String status = results.getString("docker_status");
//
//                   System.out.println(name);
//                   serverData.add(new ServerData(id, name, ip, status));
//                }
//                
//                return new Gson().toJson(serverData);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                return e.getMessage();
//            }

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
            String name = request.params(":serverName").
                    replace("%7Bcomma%7D", ", ").
                    replace("%7Bslash%7D", "/").
                    replace("{comma}", ", ").
                    replace("{slash}", "/").
                    replace("%20", " ");
            String ip = request.params(":serverIp").
                    replace("%7Bcomma%7D", ", ").
                    replace("%7Bslash%7D", "/").
                    replace("{comma}", ", ").
                    replace("{slash}", "/").
                    replace("%20", " ");
            String status = request.params(":dockerStatus").
                    replace("%7Bcomma%7D", ", ").
                    replace("%7Bslash%7D", "/").
                    replace("{comma}", ", ").
                    replace("{slash}", "/").
                    replace("%20", " ");
            
            
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