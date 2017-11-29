package view;

import business.control.Command;
import business.control.UpdateCommand;
import java.util.ArrayList;
import java.util.List;
import util.ControlException;
import java.io.IOException;

public class UpdateSaleScreenState implements ScreenState{
 
    private static UpdateSaleScreenState instance = new UpdateSaleScreenState();

    public static UpdateSaleScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
    
       List<String> sale_products = new ArrayList<>();
            
        System.out.print("Enter sale name: ");
        String name = context.input.nextLine();

        while(true){
            
            System.out.print("Enter new products name or type '0' to exit: ");
            String product_name = context.input.nextLine();

            if(product_name.equals("0")){ 
               break;
            }
            
            sale_products.add(product_name);   
        }

        System.out.print("Enter new discount(%): ");
        float discount = context.input.nextFloat();
        context.input.nextLine();
        System.out.println();
        
        try{
            Command cmd = new UpdateCommand(context.facade_sales.getSaleControl(), name, discount/100.0f, sale_products);
            context.facade_sales.service(cmd);      
            System.out.print("Sale modified successfully!\n");
        }catch (ControlException ex) {
            System.out.println("\nInvalid sale. Try again.");
        }

        context.current_state = MainScreenState.getInstance();

        try{
            System.out.println("Press 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    
    }
}
