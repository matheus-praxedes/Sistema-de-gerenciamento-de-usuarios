// Padrão Façade/Proxy
package business.control;

import business.model.Date;
import business.model.Order;
import business.model.Report;
import business.model.OrderReport;
import business.model.ProductReport;
import business.model.ClientReport;
import business.model.Product;
import business.model.User;
import java.util.Calendar;
import java.util.List;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class Facade implements FacadeInterface{
 
    private ProductControl product;
    private UserControl    user;
    private OrderControl   order;
    private AccessControl  access;
    
    public Facade() throws ControlException {
        
        try{
            product = ProductControl.getInstance();
            user    = UserControl.getInstance();
            order   = OrderControl.getInstance();
            access  = AccessControl.getInstance();
        }catch(ControlException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    @Override
    public void addUser(String login, String password, String email, String phone) throws LoginException, PasswordException, ControlException{
        
        user.addUser(login, password, email, phone);
    }
    
    @Override
    public void deleteUser(String login) throws UserException, ControlException{
        user.delete(login);
    }
    
    @Override
    public User listUser(String login) throws UserException, ControlException{
       return user.list(login);
    }
    
    @Override
    public List<User> listAllUsers() throws UserException, ControlException{
        return user.listAll();
    }

    // --------------------------------------------------------------------------------------
    
    @Override
    public void addProduct(String name, float price) throws ControlException{
        product.addProduct(name, price);
    }
    
    @Override
    public void deleteProduct(String name) throws ControlException{
        product.delete(name);
    }
    
    @Override
    public Product listProduct(String name) throws ControlException{
        return product.list(name);
    }
    
    @Override
    public List<Product> listAllProducts() throws ControlException{
        return product.listAll();
    }

    // ---------------------------------------------------------------------------------------
    
    @Override
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
    
    @Override
    public void deleteOrder(String name) throws ControlException{
        order.delete(name);
    }
    
    @Override
    public Order listOrder(String name) throws ControlException{
        return order.list(name);
    }
    
    @Override
    public List<Order> listAllOrders() throws ControlException{
        return order.listAll();
    }

    // ---------------------------------------------------------------------------------

    @Override
    public void loginSystem(String login, String password) throws UserException{
        access.login(login, password);
    }
    
    @Override
    public void logoutSystem(){
        access.logout();
    }
    
    @Override
    public User getUserLogged(){
        return access.getUser();
    }

    // ----------------------------------------------------------------------------------

    @Override
    public String getOrderReport() throws ControlException{
        
        Report report = new OrderReport(order);

        report.generate();
        return report.getContent();
    }

    @Override
    public String getClientReport() throws ControlException{
        
        Report report = new ClientReport(order);

        report.generate();
        return report.getContent();
    }

    @Override
    public String getProductReport() throws ControlException{
        
        Report report = new ProductReport(order);

        report.generate();
        return report.getContent();
    }
}
