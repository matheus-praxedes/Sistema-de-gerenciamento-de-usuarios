package infra;

import business.model.User;
import java.util.Map;
import util.InfraException;


public interface Persistence {
    
    public void saveUsers(Map<String,User> users) throws InfraException;
    public Map<String,User> loadUsers() throws InfraException;    
}
