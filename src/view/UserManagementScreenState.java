// Implementação do padrão State
package view;

public class UserManagementScreenState implements ScreenState {
    
private static UserManagementScreenState instance = new UserManagementScreenState();

    public static UserManagementScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.println("\n\n#######################################\n");
        System.out.println("Choose one of the options below: \n" +
                          " 1 - Make order\n" +
                          " 2 - List product\n" +
                          " 3 - Show menu\n" +
                          " 4 - Search for sale\n" +
                          " ------------------------\n" +
                          " 5 - Product management\n" +
                          " 6 - Sale management\n" +
                          " ------------------------\n" +
                          " 7 - Report control\n" +
                          " ------------------------\n" +
                          " 8 - Go back\n" +
                          " 0 - Exit system");
    
        int choice = context.input.nextInt();
        context.input.nextLine();
        System.out.println();
        
        switch(choice){
            case 0:
                context.current_state = ExitScreenState.getInstance();
                break;
            
            default:         
                context.current_state = (ScreenState) this;
        }
    }
}
