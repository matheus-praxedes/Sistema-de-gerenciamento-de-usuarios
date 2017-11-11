package business.control;

import business.model.User;
import util.UserException;

public class AccessControl{

    private User loggedUser;
    private UserControl control;
    
    public AccessControl(UserControl control){
    
        this.control = control;
    }
    
    public void login(String login, String password) throws UserException{
        
        User usertmp;
        usertmp = control.list(login);
        
        if(usertmp.getPassword().equals(password)){
            
            loggedUser = usertmp;
        
        }else{
            throw new UserException("Incorrect password.");
        }
    }
    
    public void logout(){
        
        loggedUser = null;
    }
    
    public User getUser(){
        return loggedUser;
    }
 }