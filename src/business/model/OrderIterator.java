package business.model;

import java.util.Map;

public class OrderIterator implements IteratorInterface{

    private Map<Product, Integer> products;
    private int counter;

    public OrderIterator(Map<Product, Integer> products){
        this.products = products;
    }

    @Override
    public void first() {
        counter = 0;
    }
 
    @Override
    public void next() {
        counter++;
    }
 
    @Override
    public boolean isDone() {
        return counter == products.size();
    }
 
    @Override
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

    @Override
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