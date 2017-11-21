package infra;

import java.util.Map;
import util.InfraException;

public class PrevPersistence <T> implements Persistence <T>{

    private final String fileName;
    
    public PrevPersistence(String file){
        
        fileName = file;
    }
    @Override
    public void save(Map<String,T> objects) throws InfraException{
        
       
    }
    @Override
    public Map<String,T> load() throws InfraException{
        return null;
    
    }   
}

