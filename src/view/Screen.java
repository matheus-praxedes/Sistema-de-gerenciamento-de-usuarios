// Implementação do padrão State
package view;

import business.control.Facade;
import business.control.FacadeSales;
import java.util.Locale;
import java.util.Scanner;
import view.ScreenState;
import util.ControlException;

public class Screen {
    
    Facade  facade;
    FacadeSales  facade_sales;
    Scanner input;
    ScreenState current_state;
    ScreenState final_state;
    
    public Screen(){
        
        input = new Scanner(System.in);
        input.useLocale(Locale.US);
        
        try {
            facade = new Facade();
            facade_sales = new FacadeSales();
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to load data into the system. Search for an administrator for support");
            System.exit(0);
        }    
        
        current_state = StartScreenState.getInstance();
        final_state = ExitScreenState.getInstance();
    }

    public void showSystem(){
        
        System.out.println("Welcome!");
        System.out.println("User registration system");
        
        while( current_state != final_state ){
            showScreen();
        }

        showScreen();
    }

    public void showScreen(){
        current_state.showScreen(this);
    }
}
