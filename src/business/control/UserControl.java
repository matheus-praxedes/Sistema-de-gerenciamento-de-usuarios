package business.control;

import business.model.User;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import util.ControlException;
import util.InfraException;
import util.LoginException;
import util.PasswordException;
import util.UserException;
import infra.Persistence;

public class UserControl {
    
    private Map<String,User> users;
    private Persistence persistence;

    private static UserControl instance;

    public static UserControl getInstance() throws ControlException{

        if(instance == null)
            instance  = new UserControl();

        return instance;
    }
    
    private UserControl() throws ControlException{
 
        persistence = PersistenceFactory.getPersistence("xmlUser") ;
        try {
            users = persistence.load();
        } catch (InfraException ex) {
            throw new ControlException("Can not access user data");
        }
    
    }

    public void addUser(String login, String password) throws LoginException,
    PasswordException, ControlException{

        if(users.containsKey(login)){
            throw new LoginException("Login already in use");
        }
        else if(login.length() > 12){
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
        
        try {
            persistence.save(users);
        } catch (InfraException ex) {
            throw new ControlException("Can not save user data");
        }
       
    }

    public void addUser(String login, String password, String email, String phone_number) 
    throws LoginException,PasswordException,ControlException{
    
        addUser(login,password);

        if(!email.isEmpty()){

            String[] e  = email.split("@");
                    
            if(e.length != 2 || e[0].isEmpty() || e[1].isEmpty()){
                
                try {
                    delete(login);
                } catch (UserException ex) {
                    System.out.println(ex.getMessage());
                }
                throw new ControlException("Email error");
            }
            
            users.get(login).setEmail(email);

        }

        if(!phone_number.isEmpty()){
        
            String[] pn = phone_number.split("-");
                    
            if(pn.length != 2 || pn[0].length() != 5 || pn[1].length() != 4){
                
                try {
                    delete(login);
                } catch (UserException ex) {
                    System.out.println(ex.getMessage());
                }
                throw new ControlException("Ill-formed phone number. Ex: 90000-0000");
            }
            
            users.get(login).setPhoneNumber(phone_number);
        }
        
        try {
            persistence.save(users);
        } catch (InfraException ex) {
            throw new ControlException("Can not save user data");
        }
    }
    
    public List<User> listAll() throws UserException{
        
        List<User> output = new ArrayList<>();
        
        if(users.isEmpty()){
            throw new UserException("There are no registered users");
        }
        
        for(String s : users.keySet()){  
            output.add(users.get(s));
        }

        return output;
    }

    public User list(String login) throws UserException{
        
        if(users.get(login) != null){
            return users.get(login);
        }else{
            throw new UserException("User not registered");
        }
    }

    public void delete(String login) throws UserException, ControlException{
    
        if(users.get(login) != null){
            users.remove(login);
        }else{
            throw new UserException("User not registered");
        }
        try {
            persistence.save(users);
        } catch (InfraException ex) {
            throw new ControlException("Can not save user data");
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

    public int countUsers(){
  
        return users.size(); 
    }

    public void clear() throws ControlException{
    
        users.clear();
        try {
            persistence.save(users);
        } catch (InfraException ex) {
            throw new ControlException("Can not save user data");
        }
    }

    public boolean containsUser(String login){
        if(users.get(login) != null)
            return true;
        else
            return false;
    }
 }
