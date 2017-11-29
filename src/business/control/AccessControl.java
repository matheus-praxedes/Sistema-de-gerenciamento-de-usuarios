package business.control;

import business.model.User;
import util.UserException;
import util.ControlException;

public class AccessControl{

    private User loggedUser;
    private UserControl control;

    private static AccessControl instance;

    public static AccessControl getInstance() throws ControlException{

        if(instance == null)
            instance = new AccessControl();

        return instance;
    }
    
    private AccessControl() throws ControlException{
        this.control = UserControl.getInstance();
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

    public int getAccessLevel(){
        if( loggedUser == null ) return 0;
        else if( loggedUser.getLogin().equals("adm")) return 3;
        else if( loggedUser.getLogin().equals("func")) return 2;
        else return 1;
    }
}