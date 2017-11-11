package view;

import business.control.UserControl;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;
import java.awt.*; 

public class UserFormAWT {
    
    private UserControl control;
    private Scanner     input;
 
    public UserFormAWT(){
    
        Frame f = new Frame();  

        Label l1 = new Label("Login:");
        l1.setBounds(80, 150, 50, 20);  
        f.add(l1);
        TextField t1 = new TextField();
        t1.setBounds(135, 150, 150, 20);  
        f.add(t1);

        Label l2 = new Label("Senha:");
        l2.setBounds(80, 180, 50, 20);  
        f.add(l2);
        TextField t2 = new TextField();
        t2.setBounds(135, 180, 150, 20);  
        f.add(t2);

        Button b = new Button("Log in");  
        b.setBounds(205, 210, 80, 30);  
        f.add(b);  
        f.setSize(360, 640);  
        f.setLayout(null);  
        f.setVisible(true);

        try {
            control = new UserControl();
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to load user data into the system. Search for an administrator for support");
            System.exit(0);
        }
    }
    public void mainMenu(){
        
       //f.show();
        

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
