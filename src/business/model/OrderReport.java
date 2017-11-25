package business.model;

import business.control.OrderControl;
import util.ControlException;
import java.util.List;

public class OrderReport extends Report{

    private OrderControl ordersList;
    float total_orders = 0.0f;

    public OrderReport(OrderControl orders){
        ordersList = orders;
    }

    @Override
    public String generateHeader(){
        return "Sales Report for the Period";
    }

    @Override
    public String generateData() throws ControlException{

        String result = "";

        for( Order order : ordersList.listAll()){
            result += order.toString() + "\n";
            total_orders += order.getValue();
        }

        result += "################################\n";
        result += String.format("%-24sR$%5.2f", "Total in the period", total_orders);
        result += "\n";

        return result;
    }

    @Override
    public String generateEnd(){

        String result = "";

        result += "The report concludes that there was a profit of " + String.format("R$ %.2f", total_orders);

        return result;
    }
}
