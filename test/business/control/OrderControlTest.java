package business.control;

import business.model.User;
import business.model.Date;
import business.model.Order;
import business.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class OrderControlTest {
   
    static ProductControl product_control;
    static OrderControl   order_control;
    static UserControl    user_control;
    static AccessControl  access;
    static User           user;
    
    @BeforeClass
    public static void init() throws ControlException{     
        
       user_control = UserControl.getInstance();
       product_control = ProductControl.getInstance();
       order_control = OrderControl.getInstance();
       access = AccessControl.getInstance();
       order_control.clear();
        
    }
    
    @Before
    public void setUp() throws ControlException, LoginException, PasswordException, UserException {
       
       
       user_control.addUser("matheus", "12345678","ma@gmail.com","90015-1214"); 
       access.login("matheus", "12345678"); 
    
    }
    
    @After
    public void tearDown() throws ControlException {
    
        order_control.clear();
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
            order_control.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");    
        }
        
        list.add("café");
        
        try {
            order_control.makeOrder(access.getUser(), date,list);
            fail("Test fail. Should throws control exception");
        } catch (ControlException ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testListAll() throws ControlException{
        
        List<Order> list2 = new ArrayList<>();
        
        Date date = new Date(10,11,2017);
        List<String> list1 = new ArrayList<>();
        
        //product_control.addProduct("chá", 1.30f);
        product_control.addProduct("batata frita", 1.25f);
         
        //list1.add("chá");
        list1.add("batata frita");
       
        try {
            order_control.makeOrder(access.getUser(), date, list1);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");    
        }
        
        Order o = new Order(new User("matheus", "12345678","ma@gmail.com","90015-1214"), new Date(10,11,2017));
        o.addProduct(new Product("batata frita",1.25f));
        //o.addProduct(new Product("chá",1.30f));
        
        list2.add(o);
        
        System.out.println(list2.toString());
        System.out.println("eu\n\n" +order_control.listAll());
     
        try {
            assertEquals(list2.toString(), order_control.listAll().toString());
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
            order_control.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");    
        }
      
        String result_user = "matheus";
        float result_price = 10.50f;
        String result_date = "10/11/2017";
        
        try {
            assertEquals(result_user, order_control.list("1").getUser().getLogin());
            assertEquals(result_price, order_control.list("1").getValue(),0.0001f);
            assertEquals(result_date, order_control.list("1").getDate().toString());
        } catch (ControlException ex) {
            fail("List should not be empty");
        }
    }
    
    @Test
    public void testDelete() throws ControlException{
           
        Date date = new Date(10,11,2017);
        List<String> list = new ArrayList<>();
    
        try {
            order_control.delete("3");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        
        product_control.addProduct("sorvete", 2.60f);
        list.add("sorvete");
               
        try {
            order_control.makeOrder(access.getUser(), date, list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should not throws control exception");   
            ex.printStackTrace(System.out);
        }

        try {
            order_control.delete("1");
            assertEquals(0,order_control.countOrders());
        } catch (ControlException ex) {
            fail("List is not empty");
        }  
        
        try {
            order_control.delete("5");
            fail("List is not empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }  
    }
}
