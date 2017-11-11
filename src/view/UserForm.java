package view;

import business.control.UserControl;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class UserForm {
    
    private UserControl control;
    private Scanner     input;
 
    public UserForm(){
    
        input = new Scanner(System.in);
        try {
            control = new UserControl();
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to load user data into the system. Search for an administrator for support");
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
                          " 3 - Remove user\n" + 
                          " 4 - List user\n" +
                          " 5 - List all users\n" + 
                          " 0 - Exit");
       
            int choice = input.nextInt();
            
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

            }
        }
        System.out.println("Thank you for using the system");
    }

    private void loginMenu(){
    
        System.out.print("Enter login:");
        String login = input.next();

        try{
            control.list(login);
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }

        System.out.print("Enter password:");
        String password = input.next();

        try{
            if(control.verifyPassword(login, password)){
                System.out.println("Successfully logged!");
            }
            else{
                System.out.println("Incorrect login or password. Try again.");
            }
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
                control.addUser(login,password);
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
            control.delete(login);
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
            System.out.println(control.list(login));
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }

    private void listAllUserMenu(){
    
        try{
            System.out.println(control.listAll());
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }
    
}
