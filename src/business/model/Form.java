package business.model;

import java.io.Serializable;

public abstract class Form implements Serializable{

    public final void generate(){

        String output = "";

        output += generateHeader();
        output += "---------------------------------";
        output += generateData();
        output += "---------------------------------";
        output += generateEnd();

        action(output);
        
    }

    public abstract String generateHeader();

    public abstract String generateData();

    public abstract String generateEnd();

    public abstract void action(String content);
}