package nl.hogeschoolrotterdam.dockdocker.serversmanagement;

import java.util.HashMap;

/**
 *
 * @author Ivan
 */
public class ServerData {
    public int ID;
    public String SERVER_NAME;
    public String SERVER_IP;
    public String DOCKER_STATUS;
    
    public ServerData (int id, String server_name, String server_ip, String docker_status){
        this.ID = id;
        this.SERVER_NAME = server_name;
        this.SERVER_IP = server_ip;
        this.DOCKER_STATUS = docker_status;
    }
}