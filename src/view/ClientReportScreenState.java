// Implementação do padrão State
package view;

import util.ControlException;
import java.io.IOException;

public class ClientReportScreenState implements ScreenState{
 
    private static ClientReportScreenState instance = new ClientReportScreenState();

    public static ClientReportScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
 
        try{
            System.out.println(context.facade.getClientReport());
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