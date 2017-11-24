package business.model;

import util.ControlException;
import java.io.Serializable;

public abstract class Report implements Serializable{

    String content;

    public final void generate() throws ControlException{

        String output = "";

        output += "\n\n##################################################\n\n";

        output += generateHeader();
        output += "\n---------------------------------\n\n";
        output += generateData();
        output += "\n---------------------------------\n\n";
        output += generateEnd();

        output += "\n\n##################################################\n\n";

        content = output;
        
    }

    public abstract String generateHeader();

    public abstract String generateData() throws ControlException;

    public abstract String generateEnd();

    public String getContent(){
        return content;
    }
}