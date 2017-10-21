package view;

import bussiness.control.UserControl;
import java.util.Scanner;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class UserForm {
    
    private UserControl control;
    private Scanner     input;
 
    public UserForm(){
    
        input = new Scanner(System.in);
        control = new UserControl();
    }
    public void mainMenu(){
        
        System.out.println("Welcome!");
        System.out.println("User registration system\n");
        
        while(true){
       
            System.out.println("\nChoose one of the options below:\n" +
                          " 1 - Add user\n" +
                          " 2 - Remove user\n" + 
                          " 3 - List user\n" +
                          " 4 - List all users\n" + 
                          " 0 - Exit");
       
            int choice = input.nextInt();
            
            if(choice == 0){
                break;
            }
            
            switch(choice){
                case 1:
                    addUserMenu();
                    break;
                case 2:
                    deleteUserMenu();
                    break;
                case 3:
                    listUserMenu();
                    break;
                case 4:
                    listAllUserMenu();
                    break;
            }
        }
        System.out.println("Thank you for using the system");
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
        }
    }
    private void listUserMenu(){
    
        System.out.print("Enter login to display:");
        String login = input.next();

        try{
            control.list(login);
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }
    private void listAllUserMenu(){
    
        try{
            control.listAll();
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }
}
