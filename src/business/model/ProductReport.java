// Padrão Template Method
package business.model;

import business.control.OrderControl;
import util.ControlException;
import java.util.Map;
import java.util.TreeMap;

public class ProductReport extends Report{

    private OrderControl ordersList;
    Map<Product, Integer> product_list;
    float total_orders = 0.0f;

    public ProductReport(OrderControl orders){
        ordersList = orders;
    }

    @Override
    public String generateHeader(){
        return "Product Search Report for the Period";
    }

    @Override
    public String generateData() throws ControlException{

        product_list = new TreeMap<>();

        String result = "";
        total_orders = 0.0f;

        for( Order order : ordersList.listAll()){

            IteratorInterface it = order.createIterator();

            for( it.first(); !it.isDone(); it.next() ){
                if( product_list.keySet().contains( it.currentItemKey() ) )
                    product_list.put( it.currentItemKey(), product_list.get(it.currentItemKey()) + it.currentItemValue());
                else
                    product_list.put( it.currentItemKey(), it.currentItemValue());
            }

        }

        for( Product p : product_list.keySet()){
            result += String.format("%3s", product_list.get(p)) + " x " + p.toString() + "\n";
            total_orders += product_list.get(p) * p.getPrice();
        }

        result += "\n=================================\n";
        result += String.format("%-26sR$%5.2f", "Total in the period", total_orders);
        result += "\n";

        return result;
    }

    @Override
    public String generateEnd(){

        Product money = new Product(" ", 0.0f);
        Product count = new Product(" ", 0.0f);
        float max_money = 0.0f;
        int max_count = 0;

        for( Product p : product_list.keySet()){
            if(product_list.get(p) > max_count){
                max_count = product_list.get(p);
                count = p;
            }

            if(product_list.get(p)*p.getPrice() > max_money){
                max_money = product_list.get(p) * p.getPrice();
                money = p;
            }
        }

        String result = "";

        result += "The report concludes that the most profitable product was: ";
        result += money.getName();
        result += "\nRendering a total of: " + String.format("R$ %5.2f", max_money);
        result += "\n\nAnd the most searched product was: ";
        result += count.getName();
        result += "\nWith "+ max_count +" units sold";

        return result;
    }
}