// Padrão State
package view;

import business.model.Product;
import java.util.List;
import util.ControlException;
import java.io.IOException;

public class ListAllProductScreenState  implements ScreenState{
    
    private static ListAllProductScreenState instance = new ListAllProductScreenState();

    public static ListAllProductScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){
    
        System.out.println();
    
        try{
            List<Product> all_products = context.facade.listAllProducts();
            System.out.println(String.format("%-20s%-7s" , "Name", "Price" ));
            System.out.println("---------------------------");
            
            for(Product p : all_products)
                System.out.println(p);
        }
        catch(ControlException e){
            System.out.println(e.getMessage());
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