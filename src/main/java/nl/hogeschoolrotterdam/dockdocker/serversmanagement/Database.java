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
    
    public static ArrayList<ServerData> SELECT_SERVER_DATA(){
        Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            
            System.out.println("Executring SELECT guery!");
            String sql = "SELECT * FROM list";
            ResultSet rs = stmt.executeQuery(sql);
            
            ArrayList<ServerData> serverData = new ArrayList<>();
            while(rs.next()){
               int id  = rs.getInt("id");
               String name = rs.getString("server_name");
               String ip = rs.getString("server_ip");
               String status = rs.getString("docker_status");
               
               serverData.add(new ServerData(id, name, ip, status));
            }

            rs.close();
            stmt.close();
            System.out.println("Connection closed!");
            return serverData;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
    
    public static boolean DELETE_SERVER_DATA(int id){
        Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            
            System.out.println("Executing DELETE query!");
            String sql = "DELETE FROM list " +
                   "WHERE id = "+id+"";
            stmt.executeUpdate(sql);
           
            stmt.close();
            System.out.println("Connection closed!");
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public static boolean INSERT_SERVER_DATA(String server_name, String server_ip, String docker_status){
        Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            
            System.out.println("Executing INSERT query!");
            String sqlInstert = "INSERT INTO list (`server_name`, `server_ip`, `docker_status`)" +
                                "VALUES ('"+server_name+"', '"+server_ip+"', '"+docker_status+"')";
            stmt.executeUpdate(sqlInstert);
            System.out.println("Inserted records into the table...");
           
            stmt.close();
            System.out.println("Connection closed!");
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
}
