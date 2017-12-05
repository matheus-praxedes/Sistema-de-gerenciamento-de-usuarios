// Padrão Factory Method
package business.control;

import java.util.Map;
import java.util.TreeMap;
import infra.Persistence;

public abstract class PersistenceFactory{

    static Map<String, PersistenceFactory> factoryList = null;

    public static Persistence getPersistence(String type){

        if(factoryList == null){
            factoryList = new TreeMap<>();
            factoryList.put("fileUser", FilePersistenceUserFactory.getInstance() );
            factoryList.put("fileProduct", FilePersistenceProductFactory.getInstance() );
            factoryList.put("fileOrder", FilePersistenceOrderFactory.getInstance() );
            factoryList.put("fileSale", FilePersistenceSaleFactory.getInstance() );
            factoryList.put("xmlUser", XMLPersistenceUserFactory.getInstance() );
            factoryList.put("xmlProduct", XMLPersistenceProductFactory.getInstance() );
            factoryList.put("xmlOrder", XMLPersistenceOrderFactory.getInstance() );
            factoryList.put("xmlSale", XMLPersistenceSaleFactory.getInstance() );
        }

        PersistenceFactory factory = factoryList.get(type);
        return factory.getPersistence();
    }

    public abstract Persistence getPersistence();
}