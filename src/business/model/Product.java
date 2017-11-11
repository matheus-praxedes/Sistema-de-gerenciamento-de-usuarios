package business.model;

import java.io.Serializable;

public class Product implements Serializable{

    private String name;
    private float value;

    public Product(String name, float value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public float getValue(){
        return value;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setValue(float value){
        this.value = value;
    }

}