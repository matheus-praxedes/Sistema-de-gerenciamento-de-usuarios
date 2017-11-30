package business.model;

import java.util.Map;

public class OrderIterator implements IteratorInterface{

    private Map<Product, Integer> products;
    private int counter;

    public OrderIterator(Map<Product, Integer> products){
        this.products = products;
    }

    public void first() {
        counter = 0;
    }
 
    public void next() {
        counter++;
    }
 
    public boolean isDone() {
        return counter == products.size();
    }
 
    public Product currentItemKey() {
        if (isDone()) {
            counter = products.size() - 1;
        } else if (counter < 0) {
            counter = 0;
        }

        int i = 0;
        for(Product product : products.keySet() )
            if( i++ == counter)
                return product;
        
        return null;
    }

    public Integer currentItemValue() {
        if (isDone()) {
            counter = products.size() - 1;
        } else if (counter < 0) {
            counter = 0;
        }
        
        int i = 0;
        for(Product product : products.keySet() )
            if( i++ == counter)
                return products.get(product);

        return null;
    }

}