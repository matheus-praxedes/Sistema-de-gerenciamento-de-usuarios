/****************************************************************************
 * Métodos de Projeto de Software - 2017.1
 * Alunos: Francisco Matheus Gonçalves de Souza - 11403723 
 *         Fabricio Leita Soares - 11311014
 *         Matheus Maranhão Rêgo Praxedes - 11403744
 * 
 * Data de entrega: 23 de outubro de 2017
 * Laboratório 3
 ****************************************************************************/
package business.model;

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
