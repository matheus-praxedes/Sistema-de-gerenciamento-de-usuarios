package business.control;

import business.model.Product;
import business.model.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ControlException;
import util.PasswordException;
import util.UserException;

public class ProductControlTest {
   
    ProductControl instance;
    
    void init() throws ControlException{     
        instance.clear();
    }
    @Before
    public void setUp() throws ControlException {

       instance = new ProductControl();
    }
    @After
    public void tearDown() throws ControlException {
    
        instance.clear();
    }

    @Test
    public void testAddProduct() throws ControlException{
        
        String name;
        Float price;
        
        price = -1.30f;
        name  = "banana";
        
        try {
            instance.addProduct(name, price);
            fail("Test fail. Should throws control exception");
        } catch (ControlException ex) {
            assertTrue(true);
        } 
        
        price = 1.30f;
        name  = "banana";
        
        try {
            instance.addProduct(name, price);
            assertTrue(true);
        } catch (ControlException ex) {
            fail("Test fail. Should throws control exception");    
        }
    }
   
    @Test
    public void testListAll() throws ControlException{
        
        List<Product> list = new ArrayList<>();
        
        try {
            instance.delete("banana");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        
        instance.addProduct("suco", 1.25f);
        instance.addProduct("agua", 1.35f );
        list.add(new Product("suco", 1.25f));
        list.add(new Product("agua", 1.35f));
        
        try {
            assertEquals( list.toString(),instance.listAll().toString());
        } catch (ControlException ex) {
            fail("List should not be empty");
        }
       
    }

    @Test
    public void testList() throws ControlException, util.LoginException, PasswordException {
           
        try {
            instance.delete("hamburger");
            fail("List is empty");
        } catch (ControlException ex) {
            assertTrue(true);
        }
        
        instance.addProduct("hamburger", 5.25f);
        String result_name = "hamburger";
        float result_price = 5.25f;
        
        try {
            assertEquals(result_name, instance.list("hamburger").getName());
            assertEquals(result_price, instance.list("hamburger").getPrice(),0.0001f);
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
        
        instance.addProduct("sorvete", 1.25f);
        
        try {
            instance.delete("sorvete");
            assertEquals(0,instance.countProducts());
        } catch (ControlException ex) {
            fail("List is not empty");
        }
        
    }
}

