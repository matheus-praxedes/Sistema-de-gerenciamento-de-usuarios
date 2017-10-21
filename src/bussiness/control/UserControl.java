package bussiness.control;

import bussiness.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class UserControl {
    
    private Map<String,User> users = new HashMap<String,User>();
    
    public void addUser(String login, String password) throws LoginException,
    PasswordException{
        
        if(login.length() > 12){
            throw new LoginException("Login exceeds maximum length");
        }
        else if(login.isEmpty()){
            throw new LoginException("Login is empty");
        }
        else if(login.matches(".*\\d.*")){ 
            throw new LoginException("Login can not contain numbers");
        }
        else if(password.length() > 20){
            throw new PasswordException("Password exceds max length");
        }
        else if(password.length() < 8){
            throw new PasswordException("Password is too short");
        }
        else if(counterNumber(password) < 2){
            throw new PasswordException("Password must contain at least 2 numbers");
       }
       
        users.put(login, new User(login,password));
       
    }
    public void listAll() throws UserException{
        System.out.println("entrei222");
        for(String s: users.keySet()){
            System.out.println("entrei");
            if(users.isEmpty()){
                System.out.println("entrei2");
                throw new UserException("There are no registered users");
            }
            System.out.println(users.get(s));
        }
    }
    public void list(String login) throws UserException{
        
        if(users.get(login) != null){
            System.out.println(users.get(login));
        }else{
            throw new UserException("User not registered");
        }
    }
    public void delete(String login) throws UserException{
    
        if(users.get(login) != null){
            users.remove(login);
        }else{
            throw new UserException("User not registered");
        }
    }
    private static int counterNumber(String s){
        
        int count = 0;
        for(int i = 0, len = s.length(); i < len; i++){

            if(Character.isDigit(s.charAt(i))){
                count ++;
            }
        }
        return count;
    }

    public void addUser(Scanner login, Scanner password) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
