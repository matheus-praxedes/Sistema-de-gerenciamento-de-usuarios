// Implementação do padrão State
package view;

import util.ControlException;
import java.io.IOException;

public class DeleteProductScreenState implements ScreenState{
    
    private static DeleteProductScreenState instance = new DeleteProductScreenState();

    public static DeleteProductScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){
        
        System.out.print("Enter product name to delete: ");
        String name = context.input.nextLine();
        System.out.println();

        try{
            context.facade.deleteProduct(name);
            System.out.print("Deleted product\n");
        }catch (ControlException ex) {
            System.out.println( ex.getMessage());
        }

        context.current_state = ProductManagementScreenState.getInstance();

        try{
            System.out.println("Press 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    }
}
