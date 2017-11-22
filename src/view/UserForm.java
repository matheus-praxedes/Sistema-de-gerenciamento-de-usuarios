package view;

import business.control.AccessControl;
import business.control.Facade;
import business.control.ProductControl;
import business.control.UserControl;
import business.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class UserForm {
    
    private Facade  facade;
    private Scanner input;
    
    
    public UserForm(){
        
        input = new Scanner(System.in);
        input.useLocale(Locale.US);
        
        try {
            facade = new Facade();
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to load data into the system. Search for an administrator for support");
            System.exit(0);
        }    
    }
    
    public void mainMenu(){
        
        System.out.println("Welcome!");
        System.out.println("User registration system\n");
        
        while(true){
       
            System.out.println("\nChoose one of the options below:\n" +
                          " 1  - Login\n" +
                          " 2  - Add user\n" +
                          " 3  - Delete user\n" + 
                          " 4  - List user\n" +
                          " 5  - List all users\n" +
                          " 6  - Add product\n" +
                          " 7  - Delete product\n" + 
                          " 8  - List product\n" +
                          " 9  - Show menu\n" +
                          " 10 - Make order\n" +
                          " 11 - Logout\n" +
                          " 0  - Exit system");
       
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
                case 10:
                    makeOrderMenu();
                    break;
                case 11:
                    logoutMenu();
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
            facade.loginSystem(login, password);
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
            
            System.out.print("Enter your email:");
            String email = input.next();
            
            System.out.print("Enter your phone number:");
            String phone = input.next();

            try{
                facade.addUser(login, password, email, phone);
                break;
            }
            catch(LoginException | PasswordException e){

                System.out.println( e.getMessage());
                System.out.println("Try again");
                
            } catch (ControlException ex) {
                System.out.println("Internal error. Unable to save user data into the system. Search for an administrator for support");
                ex.printStackTrace(System.out);
            }
        }
    }

    private void deleteUserMenu(){
    
        System.out.print("Enter login to delete:");
        String login = input.next();

        try{
            facade.deleteUser(login);
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
            System.out.println(facade.listUser(login) + "\n");
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }

    private void listAllUserMenu(){
    
        try{
            System.out.println(facade.listAllUsers());
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
                facade.addProduct(name, price);
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
            facade.deleteProduct(name);
            System.out.print("Deleted product\n");
        }catch (ControlException ex) {
            System.out.println("Internal error. Unable to save product data into the system. Search for an administrator for support");
        }
    }

    private void listProductMenu(){
    
        System.out.print("Enter product name:");
        String name = input.nextLine();

        try{
            System.out.println(facade.listProduct(name) + "\n");
        }
        catch(ControlException e){
            System.out.println( e.getMessage());
        }
    }

    private void listAllProductMenu(){
    
        try{
            System.out.println(facade.listAllProducts());
        }
        catch(ControlException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void makeOrderMenu(){
        
        List<String> orders = new ArrayList<>();
            
        while(true){
            
            System.out.print("Enter product name or type '0' to exit:");
            String name = input.nextLine();


            if(!name.equals("0")){ 
               
            }
            else if(orders.size() > 0){
                break;
            }
            else{
               return;
            }
            
            orders.add(name);
            
        }
        
        try{
            facade.newOrder(orders);      
        }catch (ControlException ex) {
            System.out.println("\nInvalid order. Try again.");
        }
        
        System.out.print("Order made successfully");
        
    }
    
    private void logoutMenu(){
        
        facade.logoutSystem();
    }
 }
