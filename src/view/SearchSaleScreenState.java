// Padrão State
package view;

import business.control.Command;
import business.control.SearchCommand;
import business.model.memento.Sale;
import util.ControlException;
import java.io.IOException;


public class SearchSaleScreenState implements ScreenState {
    
    private static SearchSaleScreenState instance = new SearchSaleScreenState();

    public static SearchSaleScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){

        Sale sale;

        System.out.print("Enter sale name: ");
        String name = context.input.nextLine();
        System.out.println();

        try{
            Command cmd = new SearchCommand(context.facade_sales.getSaleControl(), name);
            sale = context.facade_sales.service(cmd);

            System.out.println(String.format("%-20s%-7s" , "Name", "Price" ));
            System.out.println("---------------------------");
            System.out.println(sale + "\n");
        }
        catch(ControlException e){
            System.out.println( e.getMessage());
        }

        try{
            System.out.println("\nPress 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }

        context.current_state = MainScreenState.getInstance();

    }
}

