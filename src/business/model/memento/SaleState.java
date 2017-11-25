package business.model.memento;

import java.util.Set;
import java.util.TreeSet;
import business.model.Product;

public class SaleState{

    String id;
    float  discount;
    Set<Product> products;

    public SaleState(String id, float discount, Set<Product> prod_name){

        this.id = new String(id);
        this.discount = discount;
        this.products = new TreeSet<>();
        this.products.addAll(prod_name);

    }
}