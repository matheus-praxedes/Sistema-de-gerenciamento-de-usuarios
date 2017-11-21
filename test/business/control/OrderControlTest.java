package business.control;

import business.model.User;
import business.model.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import util.ControlException;

public class OrderControlTest {
   
    ProductControl product_control;
    OrderControl   instance;
    //User           logged_user;
    //Date           date;
    //List<String>   list;
    
    void init() throws ControlException{     
        instance.clear();
    }
    @Before
    public void setUp() throws ControlException {
       
       product_control = new ProductControl();
       instance = new OrderControl(product_control);
       //logged_user = new User();
       //date = new Date(23,11,2017);
       //list = new ArrayList<>();
    }
    @After
    public void tearDown() throws ControlException {
    
        instance.clear();
    }

    @Test
    public void testMakeOrder() throws ControlException{
        
        String name;
        Float price;
        
        price = 1.30f;
        name  = "suco";
        
        try {
            product_control.addProduct(name, price);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should throws control exception");    
        }
        
        price = 1.25f;
        name  = "água mineral";
        
        try {
            product_control.addProduct(name, price);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should throws control exception");    
        }
        
        User           logged_user = new User("miguelx","2526272829");
        Date           date = new Date(10,11,2017);
        List<String>   list = new ArrayList<>();
        
        list.add("suco");
        list.add("água mineral");
        
        try {
            instance.makeOrder(logged_user, date,list);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should throws control exception");    
        }
        
        list.add("café");
        
        try {
            instance.makeOrder(logged_user, date,list);
            fail("Test fail. Should throws control exception");
        } catch (ControlException ex) {
            assertTrue(true);
        } 
    }
    
    public void testListAll() throws ControlException{
        
        try {
            instance.delete("banana");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }

        product_control.addProduct("suco", 1.30f);
        product_control.addProduct("água mineral", 1.25f);
        
        User           logged_user = new User("miguely","2526272830");
        Date           date = new Date(11,11,2017);
        List<String>   list = new ArrayList<>();
        
        list.add("suco");
        list.add("água mineral");
        
        instance.makeOrder(logged_user, date,list);

        String result = "suco\tR$ 1.30\nágua mineral\tR$ 1.25\n";
        
        try {
            assertEquals( result, instance.listAll());
        } catch (ControlException ex) {
            fail("List should not be empty");
        }
       
    }
    
    public void testList() throws ControlException{
           
        try {
            instance.delete("hamburger");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        
        
        product_control.addProduct("suco", 1.30f);
        
        User           logged_user = new User("miguelz","2526272831");
        Date           date = new Date(12,11,2017);
        List<String>   list = new ArrayList<>();
        
        list.add("suco");
        instance.makeOrder(logged_user, date,list);
        
        String result_name = "suco";
        float result_price = 1.30f;
        User  user = new User("miguelz","2526272831");
        Date  date_local = new Date(12,11,2017);
        
        try {
            assertEquals(user, instance.list("suco").getUser());
            assertEquals(date_local, instance.list("suco").getDate());
            assertEquals(result_price, instance.list("suco").getValue(),0.0001f);
        } catch (ControlException ex) {
            fail("List should not be empty");
        }
                
    
    }
    
    @Test
    public void testDelete() throws ControlException{
       
        try {
            instance.delete("sorvete");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        product_control.addProduct("sorvete", 1.25f);
        
        User           logged_user = new User("raquel","3026312838");
        Date           date = new Date(15,11,2017);
        List<String>   list = new ArrayList<>();
        
        list.add("sorvete");
        instance.makeOrder(logged_user,date,list);
        
        try {
            instance.delete("sorvete");
            assertEquals(1,instance.countOrders());
        } catch (ControlException ex) {
            fail("List is not empty");
        }
        
    }
}
