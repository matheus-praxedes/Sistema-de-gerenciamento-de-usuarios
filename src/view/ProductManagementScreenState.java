// Implementação do padrão State
package view;

import util.ControlException;

public class ProductManagementScreenState implements ScreenState {
    
    private static ProductManagementScreenState instance = new ProductManagementScreenState();

    public static ProductManagementScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.println("\n\n#######################################\n");
        System.out.println("Choose one of the options below: \n" +
                        " 1 - Register product\n" +
                        " 2 - Delete product\n" + 
                        " ------------------------\n" +
                        " 3 - Go back\n" +
                        " 0 - Exit system");
    
        int choice = context.input.nextInt();
        context.input.nextLine();
        System.out.println();
        
        switch(choice){
            case 0:
                context.current_state = ExitScreenState.getInstance();
                break;
            case 1:
                context.current_state = AddProductScreenState.getInstance();
                break;
            case 2:
                context.current_state = DeleteProductScreenState.getInstance();
                break;
            case 3:
                context.current_state = MainScreenState.getInstance();
                break;
            default:         
                context.current_state = this;
        }
    }
}