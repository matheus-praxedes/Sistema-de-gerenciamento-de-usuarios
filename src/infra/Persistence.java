package infra;

import business.model.User;
import java.util.Map;
import util.InfraException;


public interface Persistence <T>{
    
    public void save(Map<String,T> objects) throws InfraException;
    public Map<String,T> load() throws InfraException;    
}
