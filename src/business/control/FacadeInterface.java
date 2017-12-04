package business.control;

import business.model.Order;
import business.model.Product;
import business.model.User;
import java.util.List;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public interface FacadeInterface {
     
    public void addUser(String login, String password, String email, String phone) throws LoginException, PasswordException, ControlException;
    
    public void deleteUser(String login) throws UserException, ControlException;
    
    public User listUser(String login) throws UserException, ControlException;
    
    public List<User> listAllUsers() throws UserException, ControlException;

    // --------------------------------------------------------------------------------------
    
    public void addProduct(String name, float price) throws ControlException;
    
    public void deleteProduct(String name) throws ControlException;
    
    public Product listProduct(String name) throws ControlException;
    
    public List<Product> listAllProducts() throws ControlException;

    // ---------------------------------------------------------------------------------------
    
    public void newOrder(List<String> orders) throws ControlException;
    
    public void deleteOrder(String name) throws ControlException;

    public Order listOrder(String name) throws ControlException;
    
    public List<Order> listAllOrders() throws ControlException;

    // ---------------------------------------------------------------------------------

    public void loginSystem(String login, String password) throws UserException;
    
    public void logoutSystem();
    
    public User getUserLogged();

    // ----------------------------------------------------------------------------------

    public String getOrderReport() throws ControlException;

    public String getClientReport() throws ControlException;

    public String getProductReport() throws ControlException;
}
