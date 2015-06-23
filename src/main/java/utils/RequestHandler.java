/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import utils.request.GetRequest;
import utils.request.PostRequest;

/**
 *
 * @author Patrick
 * @Edited Ivan
 */
public class RequestHandler {
 

    public String postServers() {
        String domain = "http://localhost:5000";
        String prefix = "/select/";
        String selectQuery = "SELECT * FROM list";
        PostRequest req = new PostRequest(domain, prefix, selectQuery);
        req.executePost();
        return req.getRespone();
    }
    
    public String postDeleteServer(String id) {
        String domain = "http://localhost:5000";
        String prefix = "/update/";
        String deleteQuery = "DELETE FROM list WHERE id = " + id;
        PostRequest req = new PostRequest(domain, prefix, deleteQuery);
        req.executePost();
        return req.getRespone();
    }
    
    public String postAddServer(String username, String password, String server_name, String server_ip, String status) {
        String domain = "http://localhost:5000";
        String prefix = "/update/";
        String insertQquery = "INSERT "
                + "INTO `db_servers`.`list` (`id`, `user`, `password`, `server_name`, `server_ip`, `docker_status`) "
                + "VALUES (NULL, '"+username+"', '"+password+"', '"+server_name+"', '"+server_ip+"', '"+status+"')";
        PostRequest req = new PostRequest(domain, prefix, insertQquery);
        req.executePost();
        return req.getRespone();
    }
}
