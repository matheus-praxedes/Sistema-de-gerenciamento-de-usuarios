package business.model;

import java.util.HashSet;
import java.util.Set;

public class Sale {

    private String id;
    private float  discount;
    private Set<Product> products;

    public Sale(String id, float discount) {
        this.id = id;
        this.discount = discount;
        this.products = new HashSet<>();
    }
    
    public void addProduct( Product product ){
        
        products.add(product);
        
    }

    public void deleteProduct( Product product ){
         
        if(products.contains(product))
            products.remove(product);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public float getNormalPrice(){
    
        float price = 0.0f;

        for( Product p : products)
            price += p.getPrice();

        return price;
    }
    
    public float getSalePrice(){
    
        return getNormalPrice()* (1.0f - discount);
    }
    
    @Override
    public String toString() {
        
        String result = "";
         
        for(Product p : products){
            result += p.toString() + " " +  p.getPrice();
        }
       
        return result + getNormalPrice() + "\n" + discount + "\n" + getSalePrice();
    }
    
}
