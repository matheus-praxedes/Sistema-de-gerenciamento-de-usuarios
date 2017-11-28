// Implementação do padrão State
package view;

public class RegisterUserScreenState implements ScreenState {
    
private static RegisterUserScreenState instance = new RegisterUserScreenState();

    public static RegisterUserScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.println("\n\n#######################################\n");
        System.out.println("Choose one of the options below: \n" +
                        " 1 - Register user\n" +
                        " ------------------------\n" +
                        " 2 - Go back\nn" +
                        " 0 - Exit system");
    
        int choice = context.input.nextInt();
        context.input.nextLine();
        System.out.println();
        
        switch(choice){
            case 0:
                context.current_state = ExitScreenState.getInstance();
                break;
           
            default:         
                context.current_state = this;
        }

    }
}