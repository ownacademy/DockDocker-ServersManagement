/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.request;

/**
 * Realization for Request
 * 
 * @see: IRequest
 * @author Patrick
 */
public abstract class Request implements IRequest {
    
    protected String domain;
    protected String url;
    protected String params;
    protected String response;
    
    /**
     * Constructor
     * 
     * @param domain: domain to send request to
     * @param url: url / action 
     */
    public Request(String domain, String url, String params) {
        this.domain = domain;
        this.url = url;
        this.params = params;
    }
    
    /**
     * Gives response of request
     * 
     * @return String response
     */
    public String getRespone() {
        return response;
    }
    
    @Override
    public abstract void executeGet();
    
    @Override
    public abstract void executePost();
}
