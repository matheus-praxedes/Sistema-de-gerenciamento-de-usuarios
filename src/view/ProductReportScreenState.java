// Implementação do padrão State
package view;

import util.ControlException;


public class ProductReportScreenState implements ScreenState {
    
    private static ProductReportScreenState instance = new ProductReportScreenState();

    public static ProductReportScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
    
        try{
            System.out.println(context.facade.getProductReport());
        }catch (ControlException ex) {
            System.out.println("\nCan't generate report.");
        }
    }
}
