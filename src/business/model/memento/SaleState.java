package business.model.memento;

import java.util.Set;
import business.model.Product;

public class SaleState{

    String id;
    float  discount;
    Set<Product> products;

    public SaleState(String id, float discount, Set<Product> prod_name){

        this.id = id;
        this.discount = discount;
        this.products = prod_name;

    }
}