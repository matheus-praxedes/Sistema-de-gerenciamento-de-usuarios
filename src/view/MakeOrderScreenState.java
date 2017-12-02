// Implementação do padrão State
package view;

import java.util.ArrayList;
import java.util.List;
import util.ControlException;
import java.io.IOException;

public class MakeOrderScreenState implements ScreenState{
    
    private static MakeOrderScreenState instance = new MakeOrderScreenState();

    public static MakeOrderScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        List<String> orders = new ArrayList<>();
            
        while(true){
            
            System.out.print("Enter product name or type '0' to exit: ");
            String name = context.input.nextLine();


            if(!name.equals("0")){ 
               
            }
            else if(orders.size() > 0){
                break;
            }
            else{
               return;
            }
            
            orders.add(name);      
        }

        System.out.println();
        
        try{
            context.facade.newOrder(orders);      
            System.out.println("Order made successfully!\n");
        }catch (ControlException ex) {
            System.out.println("\nInvalid order. Try again.");
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