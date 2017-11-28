// Implementação do padrão State
package view;

import util.ControlException;

public class OrderReportScreenState implements ScreenState{
 
    private static OrderReportScreenState instance = new OrderReportScreenState();

    public static OrderReportScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
    
        try{
            System.out.println(context.facade.getOrderReport());
        }catch (ControlException ex) {
            System.out.println("\nCan't generate report.");
        }
    }
}
