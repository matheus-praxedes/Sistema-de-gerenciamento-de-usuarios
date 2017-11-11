package business.control;

import infra.FilePersistence;
import infra.Persistence;

public class FilePersistenceFactory extends PersistenceFactory{

    private static FilePersistenceFactory instance = new FilePersistenceFactory();

    private FilePersistenceFactory(){}

    public static FilePersistenceFactory getInstance(){
        return instance;
    }

    public Persistence getPersistence(){
        return new FilePersistence();
    }

}