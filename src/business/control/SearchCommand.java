package business.control;

import business.model.Sale;
import util.ControlException;

public class SearchCommand implements Command{

    private String saleName;
    private SaleControl sc;

    public SearchCommand(SaleControl sc, String saleName){
        this.sc = sc;
        this.saleName = saleName;
    }

    public Sale execute() throws ControlException{
        Sale sale = sc.list(saleName);
        return sale;
    }

    public void unexecute(){
        // do nothing
    }

}