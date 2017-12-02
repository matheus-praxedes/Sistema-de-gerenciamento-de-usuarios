// Implementação do padrão State
package view;

import util.UserException;
import view.ExitScreenState;
import view.StartScreenState;
import java.io.IOException;

public class LoginScreenState implements ScreenState{

    private static LoginScreenState instance = new LoginScreenState();

    public static LoginScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){

        System.out.print("Enter login: ");
        String login = context.input.next();

        System.out.print("Enter password: ");
        String password = context.input.next();

        System.out.println();
        
        try{
            context.facade.loginSystem(login, password);
            System.out.println("Successfully logged as " + login + "!");
            context.current_state = MainScreenState.getInstance();
        }
        catch(UserException e){
            System.out.println( e.getMessage());
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