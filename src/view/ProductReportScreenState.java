// Implementação do padrão State
package view;

import util.ControlException;
import java.io.IOException;


public class ProductReportScreenState implements ScreenState {
    
    private static ProductReportScreenState instance = new ProductReportScreenState();

    public static ProductReportScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
    
        try{
            System.out.println(context.facade.getProductReport());
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
