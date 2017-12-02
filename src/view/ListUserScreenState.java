// Implementação do padrão State
package view;

import util.ControlException;
import util.UserException;
import java.io.IOException;

public class ListUserScreenState implements ScreenState {
    
private static ListUserScreenState instance = new ListUserScreenState();

    public static ListUserScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.print("Enter login to display: ");
        String login = context.input.next();
        System.out.println();

        try{
            context.facade.listUser(login);
            System.out.println(String.format("%-20s%-20s%-30s%-13s" , "Login", "Password", "Email", "Phone" ));
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(context.facade.listUser(login) + "\n");
        }
        catch(UserException | ControlException e){
            System.out.println( e.getMessage());
        }

        context.current_state = UserManagementScreenState.getInstance();

        try{
            System.out.println("\nPress 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    }
}
