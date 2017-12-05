// Padrão State
package view;

import business.control.FacadeInterface;
import business.control.FacadeAccessProxy;
import business.control.FacadeSales;
import java.util.Locale;
import java.util.Scanner;
import util.ControlException;

public class Screen {
    
    FacadeInterface  facade;
    FacadeSales  facade_sales;
    Scanner input;
    ScreenState current_state;
    ScreenState final_state;
    
    public Screen(){
        
        input = new Scanner(System.in);
        input.useLocale(Locale.US);
        
        try {
            facade = new FacadeAccessProxy();
            facade_sales = new FacadeSales();
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to load data into the system. Search for an administrator for support");
            System.exit(0);
        }    
        
        current_state = StartScreenState.getInstance();
        final_state = ExitScreenState.getInstance();
    }

    public void showSystem(){
        
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Welcome!");
        System.out.println("User registration system");
        
        while( current_state != final_state ){
            
            System.out.println("=========================================");
            System.out.println("|       User registration system        |");
            System.out.println("=========================================\n");
            System.out.println(facade.getUserLogged() == null ? "Not logged" : "Logged as " + facade.getUserLogged().getLogin());
            System.out.println();
            showScreen();
            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }

        showScreen();
    }

    public void showScreen(){
        current_state.showScreen(this);
    }
}
