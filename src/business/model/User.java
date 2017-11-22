package business.model;

import java.io.Serializable;

public class User implements Serializable{
    
    private String login;
    private String password;
    private String email;
    private String phone_number;

    public User(){
        this.login = "";
        this.password = "";
        this.phone_number = "";
        this.email = "";
    }
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public User(String login, String password, String email, String phone_number) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }
    
    @Override
    public String toString() {
        
        return login + "\t" + password + "\t" + email + "\t" + phone_number;
    }
}
