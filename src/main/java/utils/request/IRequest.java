package utils.request;

/**
 * Request interface
 * 
 * @author Patrick and Ivan Ivanov
 */
public interface IRequest {
    
    /**
     * 1. Sends the request
     * 2. Reads results
     * 3. Closes connection
     */
    public abstract void executeGet();
    public abstract void executePost();
    
}
