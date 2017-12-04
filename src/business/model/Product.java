package business.model;

import java.io.Serializable;

@SuppressWarnings("EqualsAndHashcode")
public class Product implements Serializable, Comparable{

    private String name;
    private float price;

    public Product(String name, float price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public float getPrice(){
        return price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(float price){
        this.price = price;
    }

    @Override
    public String toString() {

        return String.format("%-20sR$ %-7.2f", name, price );
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (!Product.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Product other = (Product) obj;

        return other.name.equals(name);
    }

    @Override
    public int compareTo(Object obj){
        if (obj == null) {
            return -1;
        }
        if (!Product.class.isAssignableFrom(obj.getClass())) {
            return -1;
        }
        final Product other = (Product) obj;

        return other.name.compareToIgnoreCase(name);
    }
}