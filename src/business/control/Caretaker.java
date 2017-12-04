package business.control;

import java.util.List;
import java.util.ArrayList;

public class Caretaker{

    private List<Command> commands;
    private int currentCommand;

    public Caretaker(){
        commands = new ArrayList<>();
        currentCommand = 0;
    }

    public void putCommand(Command cmd){

        if(currentCommand < commands.size()-1)
            commands.subList(currentCommand+1, commands.size()).clear();
        commands.add(cmd);

        if(commands.size() > 20){
            commands.remove(0);
        }
        else{
            currentCommand++;
        }
    }

    public Command getPrevCommand(){
        currentCommand = --currentCommand < 0 ? 0 : currentCommand;
        return commands.get(currentCommand);
    }

    public Command getNextCommand(){
        currentCommand = ++currentCommand >= commands.size() ? (commands.size()-1)  : currentCommand;
        return commands.get(currentCommand);
    }
}