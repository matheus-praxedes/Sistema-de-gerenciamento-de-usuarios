package view;

import business.control.AccessControl;
import business.control.ProductControl;
import business.control.UserControl;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class UserForm {
    
    private UserControl     user_control;
    private ProductControl  product_control;
    private Scanner         input;
    private AccessControl   access;  
    
    public UserForm(){
        
        input = new Scanner(System.in);
        input.useLocale(Locale.US);
        
        try {
            user_control = new UserControl();
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to load user data into the system. Search for an administrator for support");
            System.exit(0);
        }
        access = new AccessControl(user_control);
    
        try {
            product_control = new ProductControl();
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to load product data into the system. Search for an administrator for support");
            System.exit(0);
        }
    }
    
    public void mainMenu(){
        
        System.out.println("Welcome!");
        System.out.println("User registration system\n");
        
        while(true){
       
            System.out.println("\nChoose one of the options below:\n" +
                          " 1 - Login\n" +
                          " 2 - Add user\n" +
                          " 3 - Delete user\n" + 
                          " 4 - List user\n" +
                          " 5 - List all users\n" +
                          " 6 - Add product\n" +
                          " 7 - Delete product\n" + 
                          " 8 - List product\n" +
                          " 9 - Show menu\n" +
                          " 0 - Exit");
       
            int choice = input.nextInt();
            input.nextLine();
            
            if(choice == 0){
                break;
            }
            
            switch(choice){
                case 1:
                    loginMenu();
                    break;
                case 2:
                    addUserMenu();
                    break;
                case 3:
                    deleteUserMenu();
                    break;
                case 4:
                    listUserMenu();
                    break;
                case 5:
                    listAllUserMenu();
                    break;
                case 6:
                    addProductMenu();
                    break;
                case 7:
                    deleteProductMenu();
                    break;
                case 8:
                    listProductMenu();
                    break;
                case 9:
                    listAllProductMenu();
                    break;

            }
        }
        System.out.println("Thank you for using the system");
    }

    private void loginMenu(){
    
        System.out.print("Enter login:");
        String login = input.next();

        System.out.print("Enter password:");
        String password = input.next();
        
        try{
            access.login(login,password);
            System.out.println("Successfully logged!");
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }

    private void addUserMenu(){
        
        while(true){
            
            System.out.print("Enter your login:");
            String login = input.next();

            System.out.print("Enter your password:");
            String password = input.next();

            try{
                user_control.addUser(login,password);
                break;
            }
            catch(LoginException | PasswordException e){

                System.out.println( e.getMessage());
                System.out.println("Try again");
                
            } catch (ControlException ex) {
                System.out.println("Internal error. Unable to save user data into the system. Search for an administrator for support");
            }
        }
    }

    private void deleteUserMenu(){
    
        System.out.print("Enter login to delete:");
        String login = input.next();

        try{
            user_control.delete(login);
            System.out.print("Deleted user\n");
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to save user data into the system. Search for an administrator for support");
        }
    }

    private void listUserMenu(){
    
        System.out.print("Enter login to display:");
        String login = input.next();

        try{
            System.out.println(user_control.list(login) + "\n");
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }

    private void listAllUserMenu(){
    
        try{
            System.out.println(user_control.listAll());
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }
    
    private void addProductMenu(){
        
        while(true){
            
            System.out.print("Enter product name:");
            String name = input.nextLine();

            System.out.print("Enter product price:");
            float price = input.nextFloat();
            input.nextLine();

            try{
                product_control.addProduct(name,price);
                break;
            }catch (ControlException ex) {
                System.out.println("Internal error. Unable to save product data into the system. Search for an administrator for support");
            }
        }
    }

    private void deleteProductMenu(){
    
        System.out.print("Enter product name to delete:");
        String name = input.nextLine();

        try{
            product_control.delete(name);
            System.out.print("Deleted product\n");
        }catch (ControlException ex) {
            System.out.println("Internal error. Unable to save product data into the system. Search for an administrator for support");
        }
    }

    private void listProductMenu(){
    
        System.out.print("Enter product name:");
        String name = input.nextLine();

        try{
            System.out.println(product_control.list(name) + "\n");
        }
        catch(ControlException e){
            System.out.println( e.getMessage());
        }
    }

    private void listAllProductMenu(){
    
        try{
            System.out.println(product_control.listAll());
        }
        catch(ControlException e){
            System.out.println(e.getMessage());
        }
    }
    
}
