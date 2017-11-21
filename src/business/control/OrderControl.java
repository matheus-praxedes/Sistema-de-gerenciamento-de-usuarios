package business.control;

import business.model.Date;
import business.model.Order;
import business.model.User;
import infra.Persistence;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.ControlException;
import util.InfraException;

public class OrderControl{

    private Map<String,Order> orders;
    private Persistence persistence;
    private ProductControl product_control;
    
    public OrderControl(ProductControl product_control) throws ControlException{
 
        persistence = PersistenceFactory.getPersistence("fileOrder") ;
        this.product_control = product_control;
        try {
            orders = persistence.load();
        } catch (InfraException ex) {
            throw new ControlException("Can not access order data");
        }
    
    }

    public void makeOrder(User loggedUser, Date date, List<String> productNames) throws ControlException{
       
        Order order = new Order(loggedUser, date);
        
        for(String s: productNames){
        
            if(!product_control.containsProduct(s)){
                
                throw new ControlException("Not listed product");
            }
            order.addProduct(product_control.list(s));
            
        }
       
       orders.put((orders.size()+1)+"",order);
        
       
        try {
            persistence.save(orders);
        } catch (InfraException ex) {
            throw new ControlException("Can not save order data");
        }
       
    }

    public String listAll() throws ControlException{
        
        String output = "";
        
        if(orders.isEmpty()){
            
            throw new ControlException("There are no registered orders");
        }
        for(String s: orders.keySet()){
          
            output = output + orders.get(s) + "\n";
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
}