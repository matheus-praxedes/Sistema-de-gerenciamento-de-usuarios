// Padrão Command
package business.control;

import business.model.memento.Sale;
import util.ControlException;

public class SearchCommand implements Command{

    private String saleName;
    private SaleControl sc;

    public SearchCommand(SaleControl sc, String saleName){
        this.sc = sc;
        this.saleName = saleName;
    }

    @Override
    public Sale execute() throws ControlException{
        Sale sale = sc.list(saleName);
        return sale;
    }

    @Override
    public void unexecute(){
        // do nothing
    }
}