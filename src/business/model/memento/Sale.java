package business.model.memento;

import java.util.HashSet;
import java.util.Set;
import business.model.Product;
import java.io.Serializable;

public class Sale implements Serializable{

    private String id;
    private float  discount;
    private Set<Product> products;

    public Sale() {
        this.id = "";
        this.discount = 0.0f;
        this.products = new HashSet<>();
    }

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
            result += p.toString() + "\n";
        }

        result += "---------------------------\n";
        result += String.format("%-20sR$ %-7.2f\n%-22s%3.0f %%\n%-20sR$ %-7.2f", "Total", getNormalPrice(), "Discount", discount*100, "Total - discount", getSalePrice() );
       
        return result;
    }

    public void setMemento(SaleMemento mem){

        SaleState state = mem.getState();

        id = state.id;
        discount = state.discount;
        products = state.products;

    }

    public SaleMemento createMemento(){

        return new SaleMemento(new SaleState(id, discount, products));
    }
    
}
