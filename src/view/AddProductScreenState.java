// Implementação do padrão State
package view;

import util.ControlException;

public class AddProductScreenState implements ScreenState{
 
    private static AddProductScreenState instance = new AddProductScreenState();

    public static AddProductScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
        
         while(true){
            
            System.out.print("Enter product name: ");
            String name = context.input.nextLine();

            System.out.print("Enter product price: R$ ");
            float price = context.input.nextFloat();
            context.input.nextLine();

            try{
                context.facade.addProduct(name, price);
                break;
            }catch (ControlException ex) {
                System.out.println("Internal error. Unable to save product data into the system. Search for an administrator for support");
            }
        } 
    }
}
