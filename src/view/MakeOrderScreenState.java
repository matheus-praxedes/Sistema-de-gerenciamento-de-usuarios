// Implementação do padrão State
package view;

import java.util.ArrayList;
import java.util.List;
import util.ControlException;

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
        
        try{
            context.facade.newOrder(orders);      
        }catch (ControlException ex) {
            System.out.println("\nInvalid order. Try again.");
        }
        
        System.out.print("Order made successfully!\n");
        
    }
}