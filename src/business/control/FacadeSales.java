package business.control;

import util.ControlException;
import business.model.memento.Sale;

public class FacadeSales {

    Caretaker zel;
    SaleControl saleControl;
    ProductControl product;

    public FacadeSales() throws ControlException {
        zel = new Caretaker();
        product = ProductControl.getInstance();
        saleControl = SaleControl.getInstance();
    }
    
    public Sale service(Command cmd) throws ControlException{
        zel.putCommand(cmd);
        return cmd.execute();
    }

    public void undo() throws ControlException{
        zel.getPrevCommand().unexecute();
    }

    public void redo() throws ControlException{
        zel.getNextCommand().execute();
    }

    public SaleControl getSaleControl(){
        return saleControl;
    }

}