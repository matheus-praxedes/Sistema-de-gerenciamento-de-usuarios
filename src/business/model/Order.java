package business.model;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Order implements Serializable{

    private final User user;
    private final Map<Product, Integer> products;
    private final Date date;

    public Order(User user, Date date){
        this.user = user;
        this.date = date;
        products = new HashMap<Product, Integer>();
    }

    public Order() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addProduct( Product product ){
        if(products.keySet().contains(product))
            products.put(product, products.get(product)+1);
        else
            products.put(product, 1);
    }

    public void deleteProduct( Product product ){
        if(products.keySet().contains(product))
            products.put(product, products.get(product)-1);
        
        if(products.get(product) <= 0)
            products.remove(product);
    }

    public Date getDate(){
        return date;
    }

    public User getUser(){
        return user;
    }

    public float getValue(){
        float total = 0.0f;

        for( Product p : products.keySet())
            total += p.getPrice() * products.get(p);

        return total;

    }
}
