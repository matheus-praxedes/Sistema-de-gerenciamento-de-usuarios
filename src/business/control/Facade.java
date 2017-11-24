package business.control;

import business.model.Date;
import business.model.Order;
import business.model.Product;
import business.model.User;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Calendar;
import java.util.List;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class Facade {
 
    private ProductControl product;
    private UserControl    user;
    private OrderControl   order;
    private AccessControl  access;
    
    public Facade() throws ControlException {
        
        try{
            product = new ProductControl();
            user    = new UserControl();
            order   = new OrderControl(product);
            access  = new AccessControl(user);
        }catch(ControlException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void addUser(String login, String password, String email, String phone) throws LoginException, PasswordException, ControlException{
        
        user.addUser(login, password, email, phone);
    }
    
    public void deleteUser(String login) throws UserException, ControlException{
        user.delete(login);
    }
    
    public User listUser(String login) throws UserException{
       return user.list(login);
    }
    
    public List<User> listAllUsers() throws UserException{
        return user.listAll();
    }

    // --------------------------------------------------------------------------------------
    
    public void addProduct(String name, float price) throws ControlException{
        product.addProduct(name, price);
    }
    
    public void deleteProduct(String name) throws ControlException{
        product.delete(name);
    }
    
    public Product listProduct(String name) throws ControlException{
        return product.list(name);
    }
    
    public List<Product> listAllProducts() throws ControlException{
        return product.listAll();
    }

    // ---------------------------------------------------------------------------------------
    
    public void newOrder(List<String> orders) throws ControlException{
    
        java.util.Date date = new java.util.Date();
        Calendar cal;
 
        cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        order.makeOrder(access.getUser(), new Date(day,month,year), orders);
    }
    
    public void deleteOrder(String name) throws ControlException{
        order.delete(name);
    }
    
    public Order listOrder(String name) throws ControlException{
        return order.list(name);
    }
    
    public List<Order> listAllOrders() throws ControlException{
        return order.listAll();
    }

    // ---------------------------------------------------------------------------------

    public void loginSystem(String login, String password) throws UserException{
        access.login(login, password);
    }
    
    public void logoutSystem(){
        access.logout();
    }
    
    public User getUserLogged(){
        return access.getUser();
    }
}
