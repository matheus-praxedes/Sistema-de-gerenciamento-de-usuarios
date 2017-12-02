// Implementação do padrão State
package view;

import util.ControlException;
import view.ExitScreenState;
import view.MainScreenState;
import java.io.IOException;

public class StartScreenState implements ScreenState{

    private static StartScreenState instance = new StartScreenState();

    public static StartScreenState getInstance(){
        return instance;
    }
    
    @Override
    public void showScreen(Screen context){

        System.out.println("Choose one of the options below: \n" +
                        " 1 - Login\n" +
                        " 2 - Register user\n" +
                        " ------------------------\n" +
                        " 0 - Exit system");
    
        int choice = context.input.nextInt();
        context.input.nextLine();
        System.out.println();
        
        switch(choice){
            case 0:
                context.current_state = ExitScreenState.getInstance();
                break;
            case 1:
                context.current_state = LoginScreenState.getInstance();
                break;
            case 2:
                context.current_state = RegisterUserScreenState.getInstance();
                break;
            default:         
                context.current_state = this;
        }
    }
}