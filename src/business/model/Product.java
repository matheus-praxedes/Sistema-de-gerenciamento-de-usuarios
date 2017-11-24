package business.model;

import java.io.Serializable;

public class Product implements Serializable{

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
}