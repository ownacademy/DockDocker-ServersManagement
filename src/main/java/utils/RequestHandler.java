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
        GetRequest req = new GetRequest("http://localhost:5000", "/select/" + selectQuery);
        req.execute();        
        return req.getRespone();
    }
    
    public String deleteServer(String id) {
        String deleteQuery = "DELETE "
                + "FROM list "
                + "WHERE id = " + id;
        GetRequest req = new GetRequest("http://localhost:5000", "/delete/" + deleteQuery);
        req.execute();        
        return req.getRespone();
    }
    
    public String addServer(String name, String ip, String status) {
        String insertQquery = "INSERT "
                + "INTO `db_servers`.`list` (`id`, `server_name`, `server_ip`, `docker_status`) "
                + "VALUES (NULL, '"+ name +"', '"+ ip +"', '"+ status +"')";
        GetRequest req = new GetRequest("http://localhost:5000", "/insert/" + insertQquery);
        req.execute();        
        return req.getRespone();
    }

    
}
