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
    User           user;
    
    void init() throws ControlException{     
        
        instance.clear();
        
    }
    
    @Before
    public void setUp() throws ControlException, LoginException, PasswordException, UserException {
       
       user_control = new UserControl(); 
       product_control = new ProductControl();
       instance = new OrderControl(product_control);
       access = new AccessControl(user_control);
       
       user_control.addUser("matheus", "12345678","ma@gmail.com","90015-1214"); 
       access.login("matheus", "12345678"); 
    
    }
    
    @After
    public void tearDown() throws ControlException {
    
        instance.clear();
        product_control.clear();
        user_control.clear();

    }

    @Test
    public void testMakeOrder() throws ControlException{
       
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
         
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
    
    @Test
    public void testListAll() throws ControlException{
        
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
        
        product_control.addProduct("chá", 1.30f);
        product_control.addProduct("batata frita", 1.25f);
         
        list.add("chá");
        list.add("batata frita");
       
        try {
            instance.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");    
        }

        String result = "\nmatheus\t12345678\tma@gmail.com\t90015-121410/11/20171\tx\tchá\tR$ 1.31\tx\tbatata frita\tR$ 1.25\n";
        System.out.println( instance.listAll());
        try {
            assertEquals(result, instance.listAll());
        } catch (ControlException ex) {
            fail("List should not be empty");
        }
    }
    
    @Test
    public void testList() throws ControlException{
        
       
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
       
        
        product_control.addProduct("hamburguer", 10.50f);
        list.add("hamburguer");

               
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
            assertEquals(result_user, instance.list("1").getUser().getLogin());
            assertEquals(result_price, instance.list("1").getValue(),0.0001f);
            assertEquals(result_date, instance.list("1").getDate().toString());
        } catch (ControlException ex) {
            fail("List should not be empty");
        }
    }
    
    @Test
    public void testDelete() throws ControlException{
           
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
    
        try {
            instance.delete("3");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        
        product_control.addProduct("sorvete", 2.60f);
        list.add("sorvete");
               
        try {
            instance.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");   
            ex.printStackTrace(System.out);
        }

        try {
            instance.delete("1");
            assertEquals(0,instance.countOrders());
        } catch (ControlException ex) {
            fail("List is not empty");
        }  
        
        try {
            instance.delete("5");
            fail("List is not empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }  
    }
}
