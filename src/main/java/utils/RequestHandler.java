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
 * @author Patrick, Ivan Ivanov
 * 
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
        String _user = clearStringFromASCII(username);
        String _password = clearStringFromASCII(password);
        String _server_name = clearStringFromASCII(server_name);
        String _server_ip = clearStringFromASCII(server_ip);
        String _status = clearStringFromASCII(status);
        String domain = "http://localhost:5000";
        String prefix = "/update/";
        String insertQquery = "INSERT "
                + "INTO `db_servers`.`list` (`id`, `user`, `password`, `server_name`, `server_ip`, `docker_status`) "
                + "VALUES (NULL, '"+_user+"', '"+_password+"', '"+_server_name+"', '"+_server_ip+"', '"+_status+"')";
        PostRequest req = new PostRequest(domain, prefix, insertQquery);
        req.executePost();
        return req.getRespone();
    }
    
    public String getAddServer(String username, String password, String server_name, String server_ip, String status) {
        String _user = username;
        String _password = password;
        String _server_name = server_name;
        String _server_ip = server_ip;
        String _status = status;
        String domain = "http://localhost:5000";
        String prefix = "/update/";
        String insertQquery = "INSERT "
                + "INTO `db_servers`.`list` (`id`, `user`, `password`, `server_name`, `server_ip`, `docker_status`) "
                + "VALUES (NULL, '"+_user+"', '"+_password+"', '"+_server_name+"', '"+_server_ip+"', '"+_status+"')";
        GetRequest req = new GetRequest(domain, prefix, replaceStringToASCII(insertQquery));
        req.executeGet();
        return req.getRespone();
    }
    
    /**
     * Removes special ASCII characters form a String.
     * @param value String to be converted.
     * @return converted String.
     */
    public String clearStringFromASCII(String value){
        return value.
                replace("%7Bcomma%7D", ", ").
                replace("%7Bslash%7D", "/").
                replace("{comma}", ", ").
                replace("{slash}", "/").
                replace("%20", " ").
                replace("%22", "\"").
                replace("%27", "'").
                replace("%60", "`");
    }
    
    private static String replaceStringToASCII(String value){
        return value.
                replace(", ", "%7Bcomma%7D").
                replace("/", "%7Bslash%7D").
                replace(", ", "{comma}").
                replace("/", "{slash}").
                replace(" ", "%20").
                replace("\"", "%22").
                replace("'", "%27").
                replace("`", "%60");
    }
}
