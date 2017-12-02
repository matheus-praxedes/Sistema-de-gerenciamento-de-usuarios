// Implementação do padrão State
package view;

import java.util.ArrayList;
import java.util.List;
import util.ControlException;
import java.io.IOException;


public class ListProductScreenState implements ScreenState {
    
    private static ListProductScreenState instance = new ListProductScreenState();

    public static ListProductScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){

        System.out.print("Enter product name: ");
        String name = context.input.nextLine();
        System.out.println();

        try{
            context.facade.listProduct(name);
            System.out.println(String.format("%-20s%-7s" , "Name", "Price" ));
            System.out.println("---------------------------");
            System.out.println(context.facade.listProduct(name) + "\n");
        }
        catch(ControlException e){
            System.out.println( e.getMessage());
        }

        context.current_state = MainScreenState.getInstance();

        try{
            System.out.println("\nPress 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    }
}
