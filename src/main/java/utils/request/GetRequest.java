
package utils.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Generalization of Request. 
 * Executes GET request
 * 
 * @author: DockDocker development team
 * 
 */
public class GetRequest extends Request {

    /**
     * Constructor
     * 
     * @param domain: domain to send request to
     * @param url: url / action 
     * @param params: params for the request.
     */
    public GetRequest(String domain, String url, String params) {
        super(domain, url, params);
    }

    @Override
    public void executeGet() {
        HttpURLConnection con = null;
        try {            
            URL obj = new URL(domain+url+params);
            con = (HttpURLConnection) obj.openConnection();
            
            con.setRequestMethod("GET");            
            int responseCode = con.getResponseCode();
            
            // If status OK
            if(responseCode == 200) {
                 // Read response
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer responseReader = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    responseReader.append(inputLine);
                }
                in.close();
                
                // Set response
                response = responseReader.toString();
            } else if(responseCode == 404) {
                response = "Error 404: SQL Manager API not found";
            } else {
                response = "Unable to send request to DockDocker SQL Manager Api";
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.disconnect();
        }
    }

    @Override
    public void executePost() {
        throw new NotImplementedException(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
