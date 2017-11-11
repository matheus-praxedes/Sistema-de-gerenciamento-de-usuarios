/****************************************************************************
 * Métodos de Projeto de Software - 2017.1
 * Alunos: Francisco Matheus Gonçalves de Souza - 11403723 
 *         Fabricio Leita Soares - 11311014
 *         Matheus Maranhão Rêgo Praxedes - 11403744
 * 
 * Data de entrega: ?? de novembro de 2017
 * Laboratório 3
 ****************************************************************************/
package infra;

import business.model.User;
import java.util.Map;
import util.InfraException;


public interface Persistence {
    
    public void saveUsers(Map<String,User> users) throws InfraException;
    public Map<String,User> loadUsers() throws InfraException;    
}
