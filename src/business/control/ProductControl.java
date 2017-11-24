package business.control;

import business.model.Product;
import java.util.Map;
import util.ControlException;
import util.InfraException;
import infra.Persistence;

public class ProductControl {
    
    private Map<String,Product> products;
    private Persistence persistence;
    
    public ProductControl() throws ControlException{
 
        persistence = PersistenceFactory.getPersistence("fileProduct") ;
        try {
            products = persistence.load();
        } catch (InfraException ex) {
            throw new ControlException("Can not access product data");
        }
    
    }

    public void addProduct(String name, float price) throws ControlException{
        
        if(price < 0){
            throw new ControlException("Invalid price");
        }
       
        products.put(name, new Product(name,price));
        
        try {
            persistence.save(products);
        } catch (InfraException ex) {
            throw new ControlException("Can not save product data");
        }
       
    }

    public String listAll() throws ControlException{
        
        String output = "";
        
        if(products.isEmpty()){
            throw new ControlException("There are no registered products");
        }

        for(String s : products.keySet()){  
            output += products.get(s) + "\n";
        }

        return output;
    }

    public Product list(String name) throws ControlException{
        
        if(products.get(name) != null){
            return products.get(name);
        }else{
            throw new ControlException("Product not registered");
        }
    }

    public void delete(String name) throws ControlException{
    
        if(products.get(name) != null){
            products.remove(name);
        }else{
            throw new ControlException("Product not registered");
        }
        try {
            persistence.save(products);
        } catch (InfraException ex) {
            throw new ControlException("Can not save product data");
        }        
    }

    public int countProducts(){
  
        return products.size(); 
    }

    public void clear() throws ControlException{
    
        products.clear();
        try {
            persistence.save(products);
        } catch (InfraException ex) {
            throw new ControlException("Can not save product data");
        }
    }

    public boolean containsProduct(String name){
        if(products.get(name) != null)
            return true;
        else
            return false;
    }
 }
