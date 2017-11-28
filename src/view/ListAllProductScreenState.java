// Implementação do padrão State
package view;

import business.model.Product;
import java.util.List;
import util.ControlException;

public class ListAllProductScreenState  implements ScreenState{
    
    private static ListAllProductScreenState instance = new ListAllProductScreenState();

    public static ListAllProductScreenState getInstance(){
        return instance;
    }
    
    public void showScreen(Screen context){
    
        System.out.println();
    
        try{
            List<Product> all_products = context.facade.listAllProducts();
            System.out.println(String.format("%-20s%-7s" , "Name", "Price" ));
            System.out.println("---------------------------");
            
            for(Product p : all_products)
                System.out.println(p);
        }
        catch(ControlException e){
            System.out.println(e.getMessage());
        }
    }
}