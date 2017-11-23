package business.control;

import java.util.List;
import business.model.Sale;
import util.ControlException;

public class AddCommand implements Command{

    private String id;
    private float discount;
    private List<String> productNames;
    private SaleControl sc;

    public AddCommand(SaleControl sc, String id , float discount, List<String> productNames){
        this.sc = sc;
        this.id = id;
        this.discount = discount;
        this.productNames = productNames;
    }

    public Sale execute() throws ControlException{
        sc.addSale(id , discount, productNames);
        return sc.list(id);
    }

    public void unexecute() throws ControlException{
        sc.delete(id);
    }

}