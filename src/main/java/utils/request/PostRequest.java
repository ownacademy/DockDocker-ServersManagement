
package utils.request;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
public class PostRequest extends Request {
    private final String USER_AGENT = "Mozilla/5.0";
    /**
     * Constructor
     * 
     * @param domain: domain to send request to
     * @param url: url / action 
     * @param params: params for the request.
     */
    public PostRequest(String domain, String url, String params) {
        super(domain, url, params);
    }

    @Override
    public void executeGet() {
        throw new NotImplementedException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executePost() {
        try {
            String url_ = domain+url;
		URL obj = new URL(url_);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = params;
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
                
                if(responseCode == 200) {
                    System.out.println("\nSending 'POST' request to URL : " + url);
                    System.out.println("Post parameters : " + urlParameters);
                    System.out.println("Response Code : " + responseCode);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer reader_response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                            reader_response.append(inputLine);
                    }
                    in.close();

                    //print result
                    System.out.println(reader_response.toString());
                    response = reader_response.toString();
                } else if(responseCode == 404) {
                    response = "Can not find your record";
                } else {
                    response = "Can not connect SQL Manager;";
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
