package business.control;

import business.model.User;
import infra.FilePersistence;
import java.util.Map;
import util.ControlException;
import util.InfraException;
import util.LoginException;
import util.PasswordException;
import util.UserException;
import infra.Persistence;

public class UserControl {
    
    private Map<String,User> users;
    private Persistence persistence;
    
    public UserControl() throws ControlException{
 
        persistence = PersistenceFactory.getPersistence("file") ;
        try {
            users = persistence.loadUsers();
        } catch (InfraException ex) {
            throw new ControlException("Can not access user data");
        }
    
    }

    public void addUser(String login, String password) throws LoginException,
    PasswordException,ControlException{
        
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
        
        try {
            persistence.saveUsers(users);
        } catch (InfraException ex) {
            throw new ControlException("Can not save user data");
        }
       
    }

    public String listAll() throws UserException{
        
        String output = "";
        
        if(users.isEmpty()){
            
            throw new UserException("There are no registered users");
        }
        for(String s: users.keySet()){
          
            output = output + users.get(s) + "\n";
        }
        return output;
    }

    public String list(String login) throws UserException{
        
        if(users.get(login) != null){
            return users.get(login) + "\n";
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
            persistence.saveUsers(users);
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
            persistence.saveUsers(users);
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

    public boolean verifyPassword(String login, String password) throws UserException{
        if(users.get(login) != null){
            if(users.get(login).getPassword().equals(password))
                return true;
            else
                return false;
        }else{
            throw new UserException("User not registered");
        }
    }
}
