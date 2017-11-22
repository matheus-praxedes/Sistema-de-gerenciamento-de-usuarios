/****************************************************************************
 * Métodos de Projeto de Software - 2017.1
 * Alunos: Francisco Matheus Gonçalves de Souza - 11403723 
 *         Fabricio Leita Soares - 11311014
 *         Matheus Maranhão Rêgo Praxedes - 11403744
 * 
 * Data de entrega: 23 de outubro de 2017
 * Laboratório 3
 ****************************************************************************/
package business.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ControlException;
import util.PasswordException;
import util.UserException;

public class UserControlTest {
   
    UserControl instance;
    
    void init() throws ControlException{     
        instance.clear();
    }
    @Before
    public void setUp() throws ControlException {

       instance = new UserControl();
    }
    @After
    public void tearDown() throws ControlException {
    
        instance.clear();
    }

    @Test
    public void testAddUser() throws ControlException{
        
        String login;
        String password;
        String email;
        String phone;
                
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
       
        login = "francisco";
        password = "12345678";
        try {
            instance.addUser(login, password);
            assertEquals(1,instance.countUsers());
        } catch (util.LoginException ex) {
            fail("Test fail.Should throws login exception");
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        }
        
        password = "12345679";
        login    = "miguel";
        phone    = "90000-0000";
        
        email = "mx@gmail.com";
        try {
            instance.addUser(login, password, email, phone);
            assertTrue(true);  
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        } catch (util.LoginException ex) {
            fail("Test fail.Should not throws login exception");
        } catch (ControlException ex) {
            fail("Test fail.Should not throws control exception");
        }        
        
        email = "@mxgmail.com";
        try {
            instance.addUser(login, password, email, phone);
            fail("Test fail.Should throws control exception");  
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        } catch (util.LoginException ex) {
            fail("Test fail.Should not throws login exception");
        } catch (ControlException ex) {
            assertTrue(true);
        }        
        
        email = "mxgmail.com";
        try {
            instance.addUser(login, password, email, phone);
            fail("Test fail.Should throws control exception");  
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        } catch (util.LoginException ex) {
            fail("Test fail.Should not throws login exception");
        } catch (ControlException ex) {
            assertTrue(true);
        }        
        
        email = "mx@gmail.com";
        
        phone = "900000000";
        try {
            instance.addUser(login, password, email, phone);
            fail("Test fail.Should throws control exception");  
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        } catch (util.LoginException ex) {
            fail("Test fail.Should not throws login exception");
        } catch (ControlException ex) {
            assertTrue(true);
        }    
        
        phone = "90000-000";
        try {
            instance.addUser(login, password, email, phone);
            fail("Test fail.Should throws control exception");  
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        } catch (util.LoginException ex) {
            fail("Test fail.Should not throws login exception");
        } catch (ControlException ex) {
            assertTrue(true);
        }
    }
   
    @Test
    public void testListAll() throws ControlException, PasswordException, util.LoginException{
        
        try {
            instance.delete("andre");
            fail("List is empty");
        } catch (UserException ex) {
            assertTrue(true);
        }
        
        instance.addUser("andre", "12345678","and@gmail.com","98881-0215");
        instance.addUser("karla", "123456789","kl@gmail.com","97745-2114");
        String result = "andre\t12345678\tand@gmail.com\t98881-0215\nkarla\t123456789\tkl@gmail.com\t97745-2114\n";
        
        try {
            assertEquals( result,instance.listAll());
        } catch (UserException ex) {
            fail("List should not be empty");
        }
       
    }

    @Test
    public void testList() throws ControlException, util.LoginException, PasswordException {
           
        try {
            instance.delete("cebolinha");
            fail("List is empty");
        } catch (UserException ex) {
            assertTrue(true);
        }
        
        instance.addUser("cebolinha", "monica123");
        String result_login = "cebolinha";
        String result_password = "monica123";
        
        try {
            assertEquals(result_login, instance.list("cebolinha").getLogin());
            assertEquals(result_password, instance.list("cebolinha").getPassword());
        } catch (UserException ex) {
            fail("List should not be empty");
        }
                
    
    }
    
    @Test
    public void testDelete() throws ControlException, util.LoginException, PasswordException{
       
        try {
            instance.delete("cascao");
            fail("List is empty");
        } catch (UserException ex) {
            assertTrue(true);
        }
        
        instance.addUser("cascao", "celular123");
        
        try {
            instance.delete("cascao");
            assertEquals(0,instance.countUsers());
        } catch (UserException ex) {
            fail("List is not empty");
        }
        
    }
}
