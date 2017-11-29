package business.control;

import business.model.Date;
import business.model.Order;
import business.model.User;

import infra.EmailSystem;
import infra.NotificationSystem;
import infra.Persistence;
import infra.SmsSystem;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import util.ControlException;
import util.InfraException;

public class OrderControl{

    private Map<String,Order> orders;
    private Persistence persistence;
    private ProductControl product_control;
    private NotificationSystem notification;
    private int id = 0;

    private static OrderControl instance;

    public static OrderControl getInstance() throws ControlException{

        if(instance == null)
            instance = new OrderControl();

        return instance;
    }
    
    private OrderControl() throws ControlException{
 
        persistence = PersistenceFactory.getPersistence("fileOrder") ;
        this.product_control = ProductControl.getInstance();
        try {
            orders = persistence.load();
        } catch (InfraException ex) {
            throw new ControlException("Can not access order data");
        }
        for(String s: orders.keySet()){
          
            id = id > Integer.parseInt(s)? id: Integer.parseInt(s); 
        }
        id++;
    }

    public void makeOrder(User loggedUser, Date date, List<String> productNames) throws ControlException{
       
        Order order = new Order(loggedUser, date);
        
        for(String s: productNames){
        
            if(!product_control.containsProduct(s)){
                
                throw new ControlException("Not listed product");
            }
            order.addProduct(product_control.list(s));
            
        }
       
       orders.put(id++ +"", order);
        
        try {
            persistence.save(orders);
        } catch (InfraException ex) {
            throw new ControlException("Can not save order data");
        }

        if(order.getUser().getPhoneNumber() != null){
            notifyUserBySms(order);            
        }
        if( order.getUser().getEmail() != null){
            notifyUserByEmail(order);
        }
        else if( order.getUser().getPhoneNumber() == null){
            throw new ControlException("Order was made successfully!\nWarning: Can not notify user by any media!");
        }  
        
    }

    public List<Order> listAll() throws ControlException{
        
        List<Order> output = new ArrayList<>();
        
        if(orders.isEmpty()){
            throw new ControlException("There are no registered orders");
        }
        
        for(String s : orders.keySet()){  
            output.add(orders.get(s));
        }

        return output;
    }

    public Order list(String name) throws ControlException{
        
        if(orders.get(name) != null){
            return orders.get(name);
        }else{
            throw new ControlException("Order not registered");
        }
    }

    public void delete(String name) throws ControlException{
    
        if(orders.get(name) != null){
            orders.remove(name);
        }else{
            throw new ControlException("Order not registered");
        }
        try {
            persistence.save(orders);
        } catch (InfraException ex) {
            throw new ControlException("Can not save order data");
        }        
    }
    
    public void clear() throws ControlException{
    
        orders.clear();
        try {
            persistence.save(orders);
        } catch (InfraException ex) {
            throw new ControlException("Can not save order data");
        }
        id = 1;
    }
    
    public int countOrders(){
  
        return orders.size(); 
    } 

    private void notifyUserBySms(Order order) throws ControlException{

        notification = new SmsSystem();
        
        try {
            notification.setDestiny(order.getUser().getPhoneNumber());
        } catch (InfraException ex) {
            throw new ControlException("Can not set the destiny for sms");
        }
        try {
            notification.notifyUser(order.toString());
        } catch (InfraException ex) {
            throw new ControlException("Can not notify user by sms");
        }

    }

    private void notifyUserByEmail(Order order) throws ControlException{

        notification = new EmailSystem();
        
        try {
            notification.setDestiny(order.getUser().getEmail());
        } catch (InfraException ex) {
            throw new ControlException("Can not set the destiny for email");
        }
        try {
            notification.notifyUser(order.toString());
        } catch (InfraException ex) {
            throw new ControlException("Can not notify user by email");
        }

    }
     
}