// Padrão State
package view;

import business.control.AddCommand;
import business.control.Command;
import java.util.ArrayList;
import java.util.List;
import util.ControlException;
import java.io.IOException;


public class RegisterSaleScreenState implements ScreenState{
 
    private static RegisterSaleScreenState instance = new RegisterSaleScreenState();

    public static RegisterSaleScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){
    
        
        List<String> sale_products = new ArrayList<>();
            
        System.out.print("Enter sale name: ");
        String name = context.input.nextLine();

        while(true){
            
            System.out.print("Enter product name or type '0' to exit: ");
            String product_name = context.input.nextLine();

            if(!product_name.equals("0")){ 
               
            }
            else if(sale_products.size() > 0){
                break;
            }
            else{
               return;
            }
            
            sale_products.add(product_name);   
        }

        System.out.print("Enter discount(%): ");
        float discount = context.input.nextFloat();
        context.input.nextLine();
        System.out.println();
        
        try{
            Command cmd = new AddCommand(context.facade_sales.getSaleControl(), name, discount/100.0f, sale_products);
            context.facade_sales.service(cmd);      
            System.out.print("Sale registered successfully!\n");
        }catch (ControlException ex) {
            System.out.println("\nInvalid sale. Try again.");
        }

        context.current_state = SalesManagementScreenState.getInstance();

        try{
            System.out.println("\nPress 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    }
}
