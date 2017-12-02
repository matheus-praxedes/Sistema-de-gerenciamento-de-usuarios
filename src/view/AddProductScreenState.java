// Implementação do padrão State
package view;

import util.ControlException;
import java.io.IOException;

public class AddProductScreenState implements ScreenState{
 
    private static AddProductScreenState instance = new AddProductScreenState();

    public static AddProductScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){
            
        System.out.print("Enter product name: ");
        String name = context.input.nextLine();

        System.out.print("Enter product price: R$ ");
        float price = context.input.nextFloat();
        context.input.nextLine();

        try{
            context.facade.addProduct(name, price);
            System.out.println("Product successfully registered!");
            context.current_state = ProductManagementScreenState.getInstance();
        }catch (ControlException ex) {
            System.out.println("Internal error. Unable to save product data into the system. Search for an administrator for support");
            context.current_state = this;
        }

        try{
            System.out.println("\nPress 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }

    }
}
