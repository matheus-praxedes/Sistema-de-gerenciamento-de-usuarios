// Implementação do padrão State
package view;

import java.io.IOException;

public class ReportControlScreenState implements ScreenState {

    private static ReportControlScreenState instance = new ReportControlScreenState();

    public static ReportControlScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.println("Choose one of the options below: \n" +
                        " 1 - Show orders report\n" +
                        " 2 - Show products report\n" +
                        " 3 - Show clients report\n" +
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
                context.current_state = OrderReportScreenState.getInstance();
                break;
            case 2:
                context.current_state = ProductReportScreenState.getInstance();
                break;
            case 3:
                context.current_state = ClientReportScreenState.getInstance();
                break;
            case 4:
                context.current_state = MainScreenState.getInstance();
                break;
            default:         
                context.current_state = this;
        }
    }
}