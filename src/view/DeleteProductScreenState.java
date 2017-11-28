// Implementação do padrão State
package view;

import util.ControlException;

public class DeleteProductScreenState implements ScreenState{
    
    private static DeleteProductScreenState instance = new DeleteProductScreenState();

    public static DeleteProductScreenState getInstance(){
        return instance;
    }
    
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
    }
}
