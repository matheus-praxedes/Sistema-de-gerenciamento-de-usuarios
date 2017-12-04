package business.control;

import business.model.memento.Sale;
import util.ControlException;

public interface Command{
    public Sale execute() throws ControlException;
    public void unexecute() throws ControlException;

}