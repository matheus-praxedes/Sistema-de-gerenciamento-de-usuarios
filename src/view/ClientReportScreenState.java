// Implementação do padrão State
package view;

import util.ControlException;

public class ClientReportScreenState implements ScreenState{
 
    private static ClientReportScreenState instance = new ClientReportScreenState();

    public static ClientReportScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
        
        try{
            System.out.println(context.facade.getClientReport());
        }catch (ControlException ex) {
            System.out.println("\nCan't generate report.");
        }
    }
}