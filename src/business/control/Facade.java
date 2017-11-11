package business.control;

import business.model.Product;
import business.model.User;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class Facade {
 
    private ProductControl product;
    private UserControl    user;
    private AccessControl  access;
    
    public Facade() throws ControlException {
        
        try{
            product = new ProductControl();
            user    = new UserControl();
            access  = new AccessControl(user);
        }catch(ControlException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void addUser(String login, String password) throws LoginException, PasswordException, ControlException{
        
        user.addUser(login, password);
    }
    
    public void deleteUser(String login) throws UserException, ControlException{
        user.delete(login);
    }
    
    public User listUser(String login) throws UserException{
       return user.list(login);
    }
    
    public String listAllUsers() throws UserException{
        return user.listAll();
    }
    
    public void addProduct(String name, float price) throws ControlException{
        product.addProduct(name, price);
    }
    
    public void deleteProduct(String name) throws ControlException{
        product.delete(name);
    }
    
    public Product listProduct(String name) throws ControlException{
        return product.list(name);
    }
    
    public String listAllProducts() throws ControlException{
        return product.listAll();
    }
    
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
