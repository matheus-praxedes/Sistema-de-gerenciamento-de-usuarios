package business.control;

import business.model.Product;
import business.model.memento.Sale;
import infra.Persistence;
import java.util.List;
import java.util.Map;
import util.ControlException;
import util.InfraException;

public class SaleControl {
    
    private Map<String,Sale> sales;
    private Persistence persistence;
    private ProductControl product_control;
    private int id_count = 0;
    
    public SaleControl(ProductControl product_control) throws ControlException{
       
        persistence = PersistenceFactory.getPersistence("fileSale") ;
        this.product_control = product_control;
        try {
            sales = persistence.load();
        } catch (InfraException ex) {
            throw new ControlException("Can not access sale data");
        }
        
        for(String s: sales.keySet()){
          
            id_count = id_count > Integer.parseInt(s)? id_count: Integer.parseInt(s); 
        }
        id_count++;
    }

    public void addSale(String id , float discount, List<String> productNames) throws ControlException{
        
        if(discount > 1 || discount < 0){
            throw new ControlException("Invalid discount");
        }
       
        Sale sale = new Sale(id,discount);
        
        for(String s: productNames){
        
            if(!product_control.containsProduct(s)){
                
                throw new ControlException("Not listed product");
            }
            sale.addProduct(product_control.list(s));
            
        }
       
       sales.put(id_count++ +"",sale);
        
        try {
            persistence.save(sales);
        } catch (InfraException ex) {
            throw new ControlException("Can not save sale data");
        }
        
    }

    public String listAll() throws ControlException{
        
        String output = "";
        
        if(sales.isEmpty()){
            
            throw new ControlException("There are no registered sales");
        }
        for(String s: sales.keySet()){
          
            output = output + sales.get(s) + "\n";
        }
        return output;
    }

    public Sale list(String name) throws ControlException{
        
        if(sales.get(name) != null){
            return sales.get(name);
        }else{
            throw new ControlException("Sale not registered");
        }
    }

    public void delete(String name) throws ControlException{
    
        if(sales.get(name) != null){
            sales.remove(name);
        }else{
            throw new ControlException("Sale not registered");
        }
        try {
            persistence.save(sales);
        } catch (InfraException ex) {
            throw new ControlException("Can not save sale data");
        }        
    }
    
    public void clear() throws ControlException{
    
        sales.clear();
        try {
            persistence.save(sales);
        } catch (InfraException ex) {
            throw new ControlException("Can not save sale data");
        }
        id_count = 1;
    }
    
    public int countOrders(){
  
        return sales.size(); 
    } 
    
 }
