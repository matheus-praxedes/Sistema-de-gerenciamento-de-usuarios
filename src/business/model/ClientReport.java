package business.model;

import business.control.OrderControl;
import util.ControlException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ClientReport extends Report{

    private OrderControl ordersList;
    Map<User, Float> user_spendings;
    float total_orders = 0.0f;
    User bestClient;

    public ClientReport(OrderControl orders){
        ordersList = orders;
    }

    @Override
    public String generateHeader(){
        return "Most Lucrative Customer Report for the Period";
    }

    @Override
    public String generateData() throws ControlException{

        user_spendings = new TreeMap<>();
        bestClient = new User();
        float bestValue = 0.0f;

        String result = "";
        float total_orders = 0.0f;

        for( Order order : ordersList.listAll()){
            user_spendings.put( order.getUser(), 0.0f);
            total_orders += order.getValue();
        }

        for( Order order : ordersList.listAll()){
            user_spendings.put( order.getUser(), user_spendings.get( order.getUser() ) + order.getValue());
        }

        for( User u : user_spendings.keySet()){
            result += String.format("%-24sR$ %.2f", u.getLogin(), user_spendings.get(u)) + "\n";
            bestClient = user_spendings.get(u) > bestValue ? u : bestClient;
            bestValue = user_spendings.get(bestClient);
        }

        result += "\n\n################################\n";
        result += String.format("%-24sR$ %.2f", "Total in the period", total_orders);
        result += "\n";

        return result;
    }

    @Override
    public String generateEnd(){

        String result = "";

        result += "The report concludes that the most profitable \ncustomer of the period was:\n";   
        result += String.format("%-24sR$ %.2f", bestClient.getLogin(), user_spendings.get(bestClient));

        return result;
    }
}