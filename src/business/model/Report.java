package business.model;

import java.io.Serializable;

public abstract class Report implements Serializable{

    String content;

    public final void generate(){

        String output = "";

        output += generateHeader();
        output += "---------------------------------";
        output += generateData();
        output += "---------------------------------";
        output += generateEnd();

        content = output;
        
    }

    public abstract String generateHeader();

    public abstract String generateData();

    public abstract String generateEnd();

    public String getContent(){
        return content;
    }
}