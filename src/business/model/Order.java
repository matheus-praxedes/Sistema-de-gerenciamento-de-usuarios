package business.model;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Order implements Serializable{

    private User user;
    private Map<Product, Integer> products;
    private Date date;

    public Order(User user, Date date){
        this.user = user;
        this.date = date;
        products = new HashMap<Product, Integer>();
    }

    public Order() {
        
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
    @Override
    public String toString(){
        
        String result = "";
        result += String.format("%-20s%10s", user.getLogin(), date.toString());
        result += "\n";
        
        for(Product p : products.keySet() ){
            result += products.get(p) + " x " + p.toString() + "\n";
        }

        result += "------------------------------\n";
        result += String.format("%-24sR$%5.2f", "Total", getValue());
        result += "\n";
        
        return result;
    }
}
