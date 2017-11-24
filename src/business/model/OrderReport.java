package business.model;

import business.control.OrderControl;
import util.ControlException;
import java.util.List;

public class OrderReport extends Report{

    private OrderControl ordersList;
    float totalOrders = 0.0f;

    public OrderReport(OrderControl orders){
        ordersList = orders;
    }

    @Override
    public String generateHeader(){
        return "Relatório de Vendas no Período:";
    }

    @Override
    public String generateData() throws ControlException{

        String result = "";
        float total = 0.0f;

        for( Order order : ordersList.listAll()){
            result += order.toString() + "\n";
            total += order.getValue();
        }

        result += "################################\n";
        result += String.format("%-24sR$%5.2f", "Total no período", total);
        result += "\n";

        return result;
    }

    @Override
    public String generateEnd(){

        String result = "";

        result += "O relatório conclui que ";
        if(totalOrders > 5000.0f)
            result += "houve lucro significativo no período";
        else if(totalOrders > 0.0f)
            result += "houve pouco lucro no período";
        else
            result += "não houve lucro no período";

        return result;
    }
}
