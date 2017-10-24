package bussiness.control;

import bussiness.model.User;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ControlException;
import util.PasswordException;
import util.UserException;
//import util.LoginException;

public class UserControlTest {
    
   
    @Before
    public void setUp() {

    }
    @After
    public void tearDown() {
    
    }

    @Test
    public void testAddUser() throws ControlException{
        
        UserControl instance = new UserControl();
        
        String login;
        String password;
        
        password = "12345678";
        
        login = "matheuspraxedes";
        try {
            instance.addUser(login, password);
            fail("Test fail. Should throws login exception");
        } catch (util.LoginException ex) {
            assertTrue(true);
        } catch (PasswordException ex) {
            fail("Test fail.Should not throws password exception");
        }
        
        login = "";
        try {
            instance.addUser(login, password);
            fail("Test fail. Should throws login exception");
        } catch (util.LoginException ex) {
            assertTrue(true);
        } catch (PasswordException ex) {
            fail("Test fail.Should not throws password exception");
        }
        
        login = "matheus15";
        try {
            instance.addUser(login, password);
            fail("Test fail. Should throws login exception");
        } catch (util.LoginException ex) {
            assertTrue(true);
        } catch (PasswordException ex) {
            fail("Test fail.Should not throws password exception");
        }
        
        
        login = "matheus";
        
        password = "123456789101112131415";
        try {
            instance.addUser(login, password);
            fail("Test fail. Should throws password exception");
        } catch (PasswordException ex) {
            assertTrue(true);
        } catch (util.LoginException ex) {
            fail("Test fail.Should not throws login exception");
        }
        
        password = "1234567";
        try {
            instance.addUser(login, password);
            fail("Test fail. Should throws password exception");
        } catch (PasswordException ex) {
            assertTrue(true);
        } catch (util.LoginException ex) {
            fail("Test fail.Should not throws login exception");
        }
        
        password = "casabola";
        try {
            instance.addUser(login, password);
            fail("Test fail. Should throws password exception");
        } catch (PasswordException ex) {
            assertTrue(true);
        } catch (util.LoginException ex) {
            fail("Test fail.Should not throws login exception");
        }
       
        /*login = "francisco";
        password = "12345678";
        try {
            instance.addUser(login, password);
            assertEquals(instance.countUsers(),1);
        } catch (util.LoginException ex) {
            fail("Test fail.Should throws login exception");
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        }*/
    
        instance.clear();
    }
   
    @Test
    public void testListAll() throws ControlException, PasswordException, util.LoginException{
        
        UserControl instance = new UserControl();
        instance.addUser("andre", "12345678");
        instance.addUser("karla", "123456789");
        
        String result = "andre\t12345678\nkarla\t123456789";
        try {
            assertEquals( instance.listAll(),result);
        } catch (UserException ex) {
            Logger.getLogger(UserControlTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("List should not be empty");
        }
        instance.clear();
    }

   /*
    @Test
    public void testList() throws Exception {
        System.out.println("list");
        String login = "";
        UserControl instance = new UserControl();
        instance.list(login);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        String login = "";
        UserControl instance = new UserControl();
        instance.delete(login);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
