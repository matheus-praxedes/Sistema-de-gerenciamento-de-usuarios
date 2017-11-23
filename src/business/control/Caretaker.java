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
        commands.add(cmd);
        currentCommand++;
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