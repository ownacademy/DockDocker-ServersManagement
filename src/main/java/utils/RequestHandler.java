/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import utils.request.GetRequest;

/**
 *
 * @author Patrick
 * @Edited Ivan
 */
public class RequestHandler {
 
    public String getServers() {
        String selectQuery = "SELECT * "
                + "FROM list";
        GetRequest req = new GetRequest("http://localhost:5000", "/select/" + convertStringToASCII(selectQuery));
        req.execute();        
        return req.getRespone();
    }
    
    public String deleteServer(String id) {
        String deleteQuery = "DELETE "
                + "FROM list "
                + "WHERE id = " + id;
        GetRequest req = new GetRequest("http://localhost:5000", "/delete/" + convertStringToASCII(deleteQuery));
        req.execute();        
        return req.getRespone();
    }
    
    public String addServer(String username, String password, String server_name, String server_ip, String status) {
        String insertQquery = "INSERT "
                + "INTO `db_servers`.`list` (`id`, `user`, `password`, `server_name`, `server_ip`, `docker_status`) "
                + "VALUES (NULL, '"+username+"', '"+password+"', '"+server_name+"', '"+server_ip+"', '"+status+"')";
        GetRequest req = new GetRequest("http://localhost:5000", "/insert/" + convertStringToASCII(insertQquery));
        System.out.println(convertStringToASCII(insertQquery));
        req.execute();        
        return req.getRespone();
    }

    private static String convertStringToASCII(String value){
        return value.
                replace(", ", "%7Bcomma%7D").
                replace("/", "%7Bslash%7D").        
                replace(" ", "%20").
                replace("'", "%27").
                replace("`", "%60").
                replace("\"", "%22");
    }
    
}
