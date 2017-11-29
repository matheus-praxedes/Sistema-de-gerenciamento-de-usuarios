// Implementação do padrão State
package view;

import java.io.IOException;

public class ExitScreenState implements ScreenState{

    private static ExitScreenState instance = new ExitScreenState();
    
    public static ExitScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.println("Thank you for using the system!");
        System.out.println();
        
        try{
            System.out.println("Press 'ENTER' to continue");
            System.in.read();
        }
        catch( IOException ex ){
            ex.printStackTrace(System.out);
        }
    }
}