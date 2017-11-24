package business.model;

import business.control.OrderControl;
import util.ControlException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ProductReport extends Report{

    private OrderControl ordersList;
    Map<Product, Integer> product_list;
    float total_orders = 0.0f;

    public ProductReport(OrderControl orders){
        ordersList = orders;
    }

    @Override
    public String generateHeader(){
        return "Relatório de Procura por Produtos no Período";
    }

    @Override
    public String generateData() throws ControlException{

        product_list = new HashMap<>();

        String result = "";
        float total_orders = 0.0f;

        for( Order order : ordersList.listAll()){
            for(Product p : order.getProductList().keySet()){
                if(product_list.keySet().contains(p))
                    product_list.put(p, product_list.get(p) + order.getProductList().get(p));
                else
                    product_list.put(p, order.getProductList().get(p));
            }
        }

        for( Product p : product_list.keySet()){
            result += String.format("%3s", product_list.get(p)) + " x " + p.toString() + "\n";
            total_orders += product_list.get(p) * p.getPrice();
        }

        result += "\n#################################\n";
        result += String.format("%-26sR$%5.2f", "Total no período", total_orders);
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

            if(product_list.get(p)*p.getPrice() > max_money*p.getPrice()){
                max_money = product_list.get(p) * p.getPrice();
                money = p;
            }
        }

        String result = "";

        result += "O relatório conclui que o produto mais rentável foi: ";
        result += money.getName();
        result += "\nRendendo um total de: " + String.format("R$ %5.2f", max_money);
        result += "\n\nE o produto mais procurado foi: ";
        result += count.getName();
        result += "\nCom " + max_count + " unidades vendidas";

        return result;
    }
}