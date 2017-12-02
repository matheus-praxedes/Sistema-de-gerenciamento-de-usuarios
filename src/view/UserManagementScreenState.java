// Implementação do padrão State
package view;

import java.io.IOException;

public class UserManagementScreenState implements ScreenState {
    
private static UserManagementScreenState instance = new UserManagementScreenState();

    public static UserManagementScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.println("Choose one of the options below: \n" +
                          " 1 - Delete user\n" +  
                          " 2 - List user\n" +
                          " 3 - List all users\n" +
                          " ------------------------\n" +
                          " 4 - Go back\n" +
                          " 0 - Exit system");
    
        int choice = context.input.nextInt();
        context.input.nextLine();
        System.out.println();
        
        switch(choice){
            case 0:
                context.current_state = ExitScreenState.getInstance();
                break;
            case 1:
                context.current_state = DeleteUserScreenState.getInstance();
                break;
            case 2:
                context.current_state = ListUserScreenState.getInstance();
                break;
            case 3:
                context.current_state = ListAllUserScreenState.getInstance();
                break;
            case 4:
                context.current_state = MainScreenState.getInstance();
                break;

            default:         
                context.current_state = this;
        }
    }
}
