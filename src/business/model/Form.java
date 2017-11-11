package business.model;

import java.io.Serializable;

public abstract class Form implements Serializable{

    public final void print(){
        printHeader();
        System.out.println("---------------------------------");
        printData();
        System.out.println("---------------------------------");
        printEnd();
    }

    public abstract void printHeader();

    public abstract void printData();

    public abstract void printEnd();
}