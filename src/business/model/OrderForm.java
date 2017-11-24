package business.model;

import java.util.List;

public class OrderForm extends Form{

    private List<Order> ordersList;
    float totalOrders = 0.0f;

    public OrderForm(List<Order> orders){
        ordersList = orders;

        for( Order order : ordersList)
            totalOrders += order.getValue();
    }

    @Override
    public String generateHeader(){
        return "Relatório de Vendas no Período:";
    }

    @Override
    public String generateData(){

        String result = "";

        for( Order order : ordersList)
            result += order.getDate() + " ______ " + order.getValue();

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

    @Override
    public void action(String content){
        System.out.println(content);
    }
}
