package business.control;

import util.ControlException;

public class FacadeSales {

    Caretaker zel;
    SaleControl saleControl;

    public FacadeSales(ProductControl product) throws ControlException {
        zel = new Caretaker();
        saleControl = new SaleControl(product);
    }
    
    public void service(Command cmd) throws ControlException{
        cmd.execute();
        zel.putCommand(cmd);
    }

    public void undo() throws ControlException{
        zel.getPrevCommand().unexecute();
    }

    public void redo() throws ControlException{
        zel.getNextCommand().execute();
    }

}