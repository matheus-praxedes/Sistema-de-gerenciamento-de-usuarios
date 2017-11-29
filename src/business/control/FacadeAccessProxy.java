package business.control;

import business.model.Date;
import business.model.Order;
import business.model.Report;
import business.model.OrderReport;
import business.model.ProductReport;
import business.model.ClientReport;
import business.model.Product;
import business.model.User;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Calendar;
import java.util.List;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;
import business.control.AccessControl;

public class FacadeAccessProxy implements FacadeInterface{

    private FacadeInterface realFacade;
    private AccessControl access;

    public FacadeAccessProxy() throws ControlException{
        realFacade = new Facade();
        access = AccessControl.getInstance();
    }
     
    public void addUser(String login, String password, String email, String phone) throws LoginException, PasswordException, ControlException{
        realFacade.addUser(login, password, email, phone);
    }
    
    public void deleteUser(String login) throws UserException, ControlException{
        if( access.getAccessLevel() > 1 )
            realFacade.deleteUser(login);
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
    
    public User listUser(String login) throws UserException, ControlException{
        if( access.getAccessLevel() > 1 )
            return realFacade.listUser(login);
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
    
    public List<User> listAllUsers() throws UserException, ControlException{
        if( access.getAccessLevel() > 1 )
            return realFacade.listAllUsers();
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }

    // --------------------------------------------------------------------------------------
    
    public void addProduct(String name, float price) throws ControlException{
        if( access.getAccessLevel() > 1 )
            realFacade.addProduct(name, price);
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
    
    public void deleteProduct(String name) throws ControlException{
        if( access.getAccessLevel() > 1 )
            realFacade.deleteProduct(name);
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
    
    public Product listProduct(String name) throws ControlException{
        return realFacade.listProduct(name);
    }
    
    public List<Product> listAllProducts() throws ControlException{
        return realFacade.listAllProducts();
    }

    // ---------------------------------------------------------------------------------------
    
    public void newOrder(List<String> orders) throws ControlException{
        
        if(access.getAccessLevel() == 0)
            throw new ControlException("Shoud be logged to make order.");

        newOrder(orders);
    }
    
    public void deleteOrder(String name) throws ControlException{
        realFacade.deleteOrder(name);
    }

    public Order listOrder(String name) throws ControlException{
        return realFacade.listOrder(name);
    }
    
    public List<Order> listAllOrders() throws ControlException{
        return realFacade.listAllOrders();
    }

    // ---------------------------------------------------------------------------------

    public void loginSystem(String login, String password) throws UserException{
        realFacade.loginSystem(login, password);
    }
    
    public void logoutSystem(){
        realFacade.logoutSystem();
    }
    
    public User getUserLogged(){
        return realFacade.getUserLogged();
    }

    // ----------------------------------------------------------------------------------

    public String getOrderReport() throws ControlException{
        if( access.getAccessLevel() > 2 )
            return realFacade.getOrderReport();
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }

    public String getClientReport() throws ControlException{

        if( access.getAccessLevel() > 2 )
            return realFacade.getClientReport();
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }

    }

    public String getProductReport() throws ControlException{

        if( access.getAccessLevel() > 2 )
            return realFacade.getProductReport();
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
}
