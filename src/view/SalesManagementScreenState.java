// Implementação do padrão State
package view;

import business.control.Command;
import business.control.UpdateCommand;
import java.util.ArrayList;
import java.util.List;
import util.ControlException;
import java.io.IOException;

public class SalesManagementScreenState implements ScreenState {

    private static SalesManagementScreenState instance = new SalesManagementScreenState();

    public static SalesManagementScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.println("Choose one of the options below: \n" +
                        " 1 - Add sale\n" +
                        " 2 - Change sale\n" +
                        " 3 - Undo last sale action\n" +
                        " 4 - Redo last sale action\n" +
                        " ------------------------\n" +
                        " 5 - Go back\n" +
                        " 0 - Exit system");
    
        int choice = context.input.nextInt();
        context.input.nextLine();
        System.out.println();
        
        switch(choice){
            case 0:
                context.current_state = ExitScreenState.getInstance();
                break;
           case 1:
                context.current_state = RegisterSaleScreenState.getInstance();
                break;
            case 2:
                context.current_state = UpdateSaleScreenState.getInstance();
                break;
            case 3:
                undoSaleMenu(context);
                break;
            case 4:
                redoSaleMenu(context);
                break;
            case 5:
                context.current_state = MainScreenState.getInstance();
                break;
            default:         
                context.current_state = this;
                
        }
    }
   
    private void undoSaleMenu(Screen context){
        
        try{
            context.facade_sales.undo();      
            System.out.print("Last actions was undo successfully!\n");
        }catch (ControlException ex) {
            System.out.println("\nCan't retry action.");
        }

        try{
            System.out.println("Press 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    }

    private void redoSaleMenu(Screen context){
        
        try{
            context.facade_sales.redo();      
            System.out.print("Last actions was undo successfully!\n");
        }catch (ControlException ex) {
            System.out.println("\nCan't retry action.");
        }

        try{
            System.out.println("Press 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    }
}
