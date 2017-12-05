// Padrão Proxy
package business.control;

import business.model.Order;
import business.model.Product;
import business.model.User;
import java.util.List;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class FacadeAccessProxy implements FacadeInterface{

    private FacadeInterface realFacade;
    private AccessControl access;

    public FacadeAccessProxy() throws ControlException{
        realFacade = new Facade();
        access = AccessControl.getInstance();
    }
     
    @Override
    public void addUser(String login, String password, String email, String phone) throws LoginException, PasswordException, ControlException{
        realFacade.addUser(login, password, email, phone);
    }
    
    @Override
    public void deleteUser(String login) throws UserException, ControlException{
        if( access.getAccessLevel() > 1 )
            realFacade.deleteUser(login);
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
    
    @Override
    public User listUser(String login) throws UserException, ControlException{
        if( access.getAccessLevel() > 1 )
            return realFacade.listUser(login);
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
    
    @Override
    public List<User> listAllUsers() throws UserException, ControlException{
        if( access.getAccessLevel() > 1 )
            return realFacade.listAllUsers();
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }

    // --------------------------------------------------------------------------------------
    
    @Override
    public void addProduct(String name, float price) throws ControlException{
        if( access.getAccessLevel() > 1 )
            realFacade.addProduct(name, price);
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
    
    @Override
    public void deleteProduct(String name) throws ControlException{
        if( access.getAccessLevel() > 1 )
            realFacade.deleteProduct(name);
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
    
    @Override
    public Product listProduct(String name) throws ControlException{
        return realFacade.listProduct(name);
    }
    
    @Override
    public List<Product> listAllProducts() throws ControlException{
        return realFacade.listAllProducts();
    }

    // ---------------------------------------------------------------------------------------
    
    @Override
    public void newOrder(List<String> orders) throws ControlException{
        
        if(access.getAccessLevel() == 0)
            throw new ControlException("Shoud be logged to make order.");

        realFacade.newOrder(orders);
    }
    
    @Override
    public void deleteOrder(String name) throws ControlException{
        realFacade.deleteOrder(name);
    }

    @Override
    public Order listOrder(String name) throws ControlException{
        return realFacade.listOrder(name);
    }
    
    @Override
    public List<Order> listAllOrders() throws ControlException{
        return realFacade.listAllOrders();
    }

    // ---------------------------------------------------------------------------------

    @Override
    public void loginSystem(String login, String password) throws UserException{
        realFacade.loginSystem(login, password);
    }
    
    @Override
    public void logoutSystem(){
        realFacade.logoutSystem();
    }
    
    @Override
    public User getUserLogged(){
        return realFacade.getUserLogged();
    }

    // ----------------------------------------------------------------------------------

    @Override
    public String getOrderReport() throws ControlException{
        if( access.getAccessLevel() > 2 )
            return realFacade.getOrderReport();
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }

    @Override
    public String getClientReport() throws ControlException{

        if( access.getAccessLevel() > 2 )
            return realFacade.getClientReport();
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }

    }

    @Override
    public String getProductReport() throws ControlException{

        if( access.getAccessLevel() > 2 )
            return realFacade.getProductReport();
        else {
            throw new ControlException("Access denied! Logged user doesn't have required permissions");
        }
    }
}
