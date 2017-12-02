// Implementação do padrão State
package view;

public class MainScreenState implements  ScreenState{

    private static MainScreenState instance = new MainScreenState();

    public static MainScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){

        System.out.println("Choose one of the options below: \n" +
                          " 1 - Make order\n" +
                          " 2 - List product\n" +
                          " 3 - Show menu\n" +
                          " 4 - Search for sale\n" +
                          " ------------------------\n" +
                          " 5 - User management\n" +
                          " 6 - Product management\n" +
                          " 7 - Sale management\n" +
                          " ------------------------\n" +
                          " 8 - Report control\n" +
                          " ------------------------\n" +
                          " 9 - Logout\n" +
                          " 0 - Exit system");
    
        int choice = context.input.nextInt();
        context.input.nextLine();
        System.out.println();
        
        switch(choice){
            case 0:
                context.current_state = ExitScreenState.getInstance();
                break;
            case 1:
                context.current_state = MakeOrderScreenState.getInstance();
                break;
            case 2:
                context.current_state = ListProductScreenState.getInstance();
                break;
            case 3:
                context.current_state = ListAllProductScreenState.getInstance();
                break;
            case 4:
                context.current_state = SearchSaleScreenState.getInstance();
                break;
            case 5:
                context.current_state = UserManagementScreenState.getInstance();
                break;
            case 6:
                context.current_state = ProductManagementScreenState.getInstance();
                break;
            case 7:
                context.current_state = SalesManagementScreenState.getInstance();
                break;
            case 8:
                context.current_state = ReportControlScreenState.getInstance();
                break;
            case 9:
                context.facade.logoutSystem();
                context.current_state = StartScreenState.getInstance();
                break;
            default:         
                context.current_state = this;
        }

    }
}
