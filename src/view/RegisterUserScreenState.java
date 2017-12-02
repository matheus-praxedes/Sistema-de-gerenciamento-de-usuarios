// Implementação do padrão State
package view;

import util.ControlException;
import util.LoginException;
import util.PasswordException;
import java.io.IOException;

public class RegisterUserScreenState implements ScreenState {
    
private static RegisterUserScreenState instance = new RegisterUserScreenState();

    public static RegisterUserScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.print("Enter your login: ");
        String login = context.input.next();

        System.out.print("Enter your password: ");
        String password = context.input.next();
        
        System.out.print("Enter your email: ");
        String email = context.input.next();
        
        System.out.print("Enter your phone number: ");
        String phone = context.input.next();

        try{
            context.facade.addUser(login, password, (email.equals("0") ? "" : email), (phone.equals("0") ? "" : phone));
            System.out.println("User " + login + " registered successfully!\n");
            context.current_state = StartScreenState.getInstance();
        }
        catch(LoginException | PasswordException e){

            System.out.println( e.getMessage());
            System.out.println("Try again");
            context.current_state = StartScreenState.getInstance();
            
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to save user data into the system. Search for an administrator for support");
            ex.printStackTrace(System.out);
            context.current_state = StartScreenState.getInstance();
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