package business.control;

import java.util.Map;
import java.util.HashMap;
import infra.Persistence;

public abstract class PersistenceFactory{

    static Map<String, PersistenceFactory> factoryList = null;

    public static Persistence getPersistence(String type){

        if(factoryList == null){
            factoryList = new HashMap<String, PersistenceFactory>();
            factoryList.put("fileUser", FilePersistenceUserFactory.getInstance() );
            factoryList.put("fileProduct", FilePersistenceProductFactory.getInstance() );
            factoryList.put("fileOrder", FilePersistenceOrderFactory.getInstance() );
        }

        PersistenceFactory factory = factoryList.get(type);
        return factory.getPersistence();
    }

    public abstract Persistence getPersistence();

}