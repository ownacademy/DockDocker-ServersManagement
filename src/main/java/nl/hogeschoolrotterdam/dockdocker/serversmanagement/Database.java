/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hogeschoolrotterdam.dockdocker.serversmanagement;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class Database {
    // JDBC driver name and database URL
       static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
       static final String DB_URL = "jdbc:mysql://localhost/db_servers";

       //  Database credentials
       static final String USER = "root";
       static final String PASS = "";
       //TO DO: Encrypt password.
       
       private Connection Connection = null;
       private Statement Statement = null;
        
    public Database() throws Exception{
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to a selected database...");
            Connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            Statement = Connection.createStatement();
    }
    
    public ArrayList<ServerData> SELECT_SERVER_DATA(){
        try {
            System.out.println("Executring SELECT guery!");
            String sql = "SELECT * FROM list";
            ResultSet results = Statement.executeQuery(sql);
            
            ArrayList<ServerData> serverData = new ArrayList<>();
            while(results.next()){
               int id  = results.getInt("id");
               String name = results.getString("server_name");
               String ip = results.getString("server_ip");
               String status = results.getString("docker_status");
               
               serverData.add(new ServerData(id, name, ip, status));
            }

            results.close();
            return serverData;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
    
    public boolean DELETE_SERVER_DATA(int id){
        
        try {           
            System.out.println("Executing DELETE query!");
            String sql = "DELETE FROM list " +
                   "WHERE id = "+id+"";
            Statement.executeUpdate(sql);
            
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean INSERT_SERVER_DATA(String server_name, String server_ip, String docker_status){
        try {            
            System.out.println("Executing INSERT query!");
            String sqlInstert = "INSERT INTO list (`server_name`, `server_ip`, `docker_status`)" +
                                "VALUES ('"+server_name+"', '"+server_ip+"', '"+docker_status+"')";
            Statement.executeUpdate(sqlInstert);
            System.out.println("Inserted records into the table...");

            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean INSERT_SERVER_DATA(ServerData data){
        try {            
            System.out.println("Executing INSERT query!");
            String sqlInstert = "INSERT INTO list (`server_name`, `server_ip`, `docker_status`)" +
                                "VALUES ('"+data.SERVER_NAME+"', '"+data.SERVER_IP+"', '"+data.DOCKER_STATUS+"')";
            Statement.executeUpdate(sqlInstert);
            System.out.println("Inserted records into the table...");

            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
}
