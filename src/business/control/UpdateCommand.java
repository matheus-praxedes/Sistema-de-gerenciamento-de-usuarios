package business.control;

import java.util.List;
import java.util.ArrayList;

import business.model.Product;
import business.model.memento.Sale;
import business.model.memento.SaleMemento;
import util.ControlException;

public class UpdateCommand implements Command{

    private String id;
    private float discount;
    private List<String> productNames;
    private SaleControl sc;
    private SaleMemento mem;

    public UpdateCommand(SaleControl sc, String id, float discount, List<String> productNames){
        this.sc = sc;
        this.id = id;
        this.discount = discount;
        this.productNames = productNames;
    }

    public Sale execute() throws ControlException{
        mem = sc.list(id).createMemento();
        sc.addSale(id, discount, productNames);
        return sc.list(id);
    }

    public void unexecute() throws ControlException{
        Sale prev = new Sale();
        prev.setMemento(mem);
        
        List<String> product_name_list = new ArrayList<>();
        for(Product p : prev.getProducts())
            product_name_list.add(p.getName());

        sc.addSale(prev.getId(), prev.getDiscount(), product_name_list) ;
    }

}