// Padrão State
package view;

import util.UserException;
import util.ControlException;
import java.io.IOException;

public class DeleteUserScreenState implements ScreenState {
    
    private static DeleteUserScreenState instance = new DeleteUserScreenState();

    public static DeleteUserScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){

        System.out.print("Enter login to delete: ");
        String login = context.input.next();
        System.out.println();

        try{
            context.facade.deleteUser(login);
            System.out.print(login + " deleted successfully!\n");
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to save user data into the system. Search for an administrator for support");
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
