package business.control;

import business.model.Sale;
import util.ControlException;

public interface Command{

    public Sale execute() throws ControlException;
    public void unexecute() throws ControlException;

}