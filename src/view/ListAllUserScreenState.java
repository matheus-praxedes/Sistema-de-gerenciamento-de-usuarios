// Implementação do padrão State
package view;

import business.model.User;

import java.io.IOException;
import java.util.List;
import util.UserException;
import util.ControlException;

public class ListAllUserScreenState implements ScreenState {
    
private static ListAllUserScreenState instance = new ListAllUserScreenState();

    public static ListAllUserScreenState getInstance(){
        return instance;
    }
    
@Override
    public void showScreen(Screen context){

        try{
            List<User> all_users = context.facade.listAllUsers();
            System.out.println(String.format("%-20s%-20s%-30s%-13s" , "Login", "Password", "Email", "Phone" ));
            System.out.println("-----------------------------------------------------------------------------------");
            
            for(User u : all_users)
                System.out.println(u);
        }
        catch(UserException | ControlException e ){
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
