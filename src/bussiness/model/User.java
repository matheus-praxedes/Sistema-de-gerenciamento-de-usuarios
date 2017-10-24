package bussiness.model;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class User implements Serializable{
    
    private String login;
    private String password;

    public User(){
        this.login = "";
        this.password = "";
    }
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        
        return login + "\t" + password;
    }
}
