package business.model;

import business.control.OrderControl;
import util.ControlException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
        return "Relatório de Clientes mais lucarativos no Período";
    }

    @Override
    public String generateData() throws ControlException{

        user_spendings = new HashMap<>();
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
            result += String.format("%-24sR$%5.2f", u.getLogin(), user_spendings.get(u));
            bestClient = user_spendings.get(u) > bestValue ? u : bestClient;
            bestValue = user_spendings.get(bestClient);
        }

        result += "\n\n################################\n";
        result += String.format("%-24sR$%5.2f", "Total no período", total_orders);
        result += "\n";

        return result;
    }

    @Override
    public String generateEnd(){

        String result = "";

        result += "O relatório conclui que o cliente mais rentável do período foi:\n";
        
        result += String.format("%-24sR$%5.2f", bestClient.getLogin(), user_spendings.get(bestClient));

        return result;
    }
}