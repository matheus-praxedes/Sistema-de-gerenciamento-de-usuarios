// Implementação do padrão State
package view;

import util.ControlException;
import java.io.IOException;

public class OrderReportScreenState implements ScreenState{
 
    private static OrderReportScreenState instance = new OrderReportScreenState();

    public static OrderReportScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
    
        try{
            System.out.println(context.facade.getOrderReport());
        }catch (ControlException ex) {
            System.out.println( ex.getMessage() );
        }

        context.current_state = ReportControlScreenState.getInstance();

        try{
            System.out.println("\nPress 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    }
}
