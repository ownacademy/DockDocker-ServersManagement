/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.RequestHandler;

/**
 *
 * @author ivan
 */
public class JunitTest {
    
    public JunitTest() {
    }

    @Test
    public void testStringCleaner() {
        assertEquals("The result have to be: '/'", "/", new RequestHandler().clearStringFromASCII("%7Bslash%7D"));
        assertEquals("The result have to be: ', '", ", ", new RequestHandler().clearStringFromASCII("%7Bcomma%7D"));
        assertEquals("The result have to be: ' '", " ", new RequestHandler().clearStringFromASCII("%20"));
        assertEquals("The result have to be: '\"'", "\"", new RequestHandler().clearStringFromASCII("%22"));
        assertEquals("The result have to be: ' ' '", "'", new RequestHandler().clearStringFromASCII("%27"));
        assertEquals("The result have to be: '`'", "`", new RequestHandler().clearStringFromASCII("%60"));
        
    }
}
