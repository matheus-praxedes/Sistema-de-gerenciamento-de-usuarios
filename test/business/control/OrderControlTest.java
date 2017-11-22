package business.control;

import business.model.User;
import business.model.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class OrderControlTest {
   
    ProductControl product_control;
    OrderControl   instance;
    UserControl    user_control;
    AccessControl  access;
    
    void init() throws ControlException{     
        
        instance.clear();
        product_control.clear();
        user_control.clear();
    }
    @Before
    public void setUp() throws ControlException {
       
       product_control = new ProductControl();
       instance = new OrderControl(product_control);
       access = new AccessControl(user_control);
    }
    @After
    public void tearDown() throws ControlException {
    
        instance.clear();
        product_control.clear();
        user_control.clear();
    }

    @Test
    public void testMakeOrder() throws ControlException{
        
        user_control = new UserControl();
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
        access = new AccessControl(user_control);
         
        String name;
        Float price;
        
        price = 1.30f;
        name  = "suco";
        product_control.addProduct(name, price);
        
        price = 1.25f;
        name  = "água mineral";
        product_control.addProduct(name, price);
                
        list.add("suco");
        list.add("água mineral");
        
        try {
            user_control.addUser("matheus", "12345678","ma@gmail.com","90015-1214");
            assertTrue(true);
        } catch (LoginException ex) {
            fail("Test fail. Should not throws login exception");
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        }
        try {
            access.login("matheus", "12345678");
            assertTrue(true);
        } catch (UserException ex) {
             fail("Test fail. Should not throws user exception");
        }
               
        try {
            instance.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");    
        }
        
        list.add("café");
        
        try {
            instance.makeOrder(access.getUser(), date,list);
            fail("Test fail. Should throws control exception");
        } catch (ControlException ex) {
            assertTrue(true);
        }
    }
    
    public void testListAll() throws ControlException{
        
        user_control = new UserControl();
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
        access = new AccessControl(user_control);
        
        try {
            instance.delete("banana");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        
        product_control.addProduct("suco", 1.30f);
        product_control.addProduct("água mineral", 1.25f);
         
        list.add("suco");
        list.add("água mineral");

        
        try {
            user_control.addUser("matheus", "12345678","ma@gmail.com","90015-1214");
            assertTrue(true);
        } catch (LoginException ex) {
            fail("Test fail. Should not throws login exception");
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        }
        try {
            access.login("matheus", "12345678");
            assertTrue(true);
        } catch (UserException ex) {
             fail("Test fail. Should not throws user exception");
        }
               
        try {
            instance.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");    
        }

        String result = "suco\tR$ 1.30\nágua mineral\tR$ 1.25\n";
        
        try {
            assertEquals(result, instance.listAll());
        } catch (ControlException ex) {
            fail("List should not be empty");
        }
    }
    
    public void testList() throws ControlException{
        
        user_control = new UserControl();
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
        access = new AccessControl(user_control);
        
        try {
            instance.delete("hamburguer");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        
        product_control.addProduct("hamburguer", 10.50f);
        list.add("hamburguer");

        try {
            user_control.addUser("matheus", "12345678","ma@gmail.com","90015-1214");
            assertTrue(true);
        } catch (LoginException ex) {
            fail("Test fail. Should not throws login exception");
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        }
        try {
            access.login("matheus", "12345678");
            assertTrue(true);
        } catch (UserException ex) {
             fail("Test fail. Should not throws user exception");
        }
               
        try {
            instance.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");    
        }
      
        String result_user = "matheus";
        float result_price = 10.50f;
        String result_date = "10/11/2017";
        
        try {
            assertEquals(result_user, instance.list("hamburger").getUser());
            assertEquals(result_price, instance.list("hamburger").getValue(),0.0001f);
            assertEquals(result_date, instance.list("hamburger").getDate());
        } catch (ControlException ex) {
            fail("List should not be empty");
        }
    }
    
    @Test
    public void testDelete() throws ControlException{
   
        user_control = new UserControl();
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
        access = new AccessControl(user_control);
        
        try {
            instance.delete("sorvete");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        
        product_control.addProduct("sorvete", 2.60f);
        list.add("sorvete");
        
        try {
            user_control.addUser("matheus", "12345678","ma@gmail.com","90015-1214");
            assertTrue(true);
        } catch (LoginException ex) {
            fail("Test fail. Should not throws login exception");
        } catch (PasswordException ex) {
            fail("Test fail. Should not throws password exception");
        }
        try {
            access.login("matheus", "12345678");
            assertTrue(true);
        } catch (UserException ex) {
             fail("Test fail. Should not throws user exception");
        }
               
        try {
            instance.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");   
            ex.printStackTrace(System.out);
        }
        try {
            instance.delete("sorvete");
            assertEquals(0,instance.countOrders());
        } catch (ControlException ex) {
            fail("List is not empty");
        }  
    }
}
