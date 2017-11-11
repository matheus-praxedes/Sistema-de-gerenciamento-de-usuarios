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
    public void printHeader(){
        System.out.println("Relatório de Vendas no Período:");
    }

    @Override
    public void printData(){

        for( Order order : ordersList)
            System.out.println(order.getDate() + " ______ " + order.getValue());

    }

    @Override
    public void printEnd(){
        System.out.print("O relatório conclui que ");
        if(totalOrders > 5000.0f)
            System.out.println("houve lucro significativo no período");
        else if(totalOrders > 0.0f)
            System.out.println("houve pouco lucro no período");
        else
            System.out.println("não houve lucro no período");
    }
}
