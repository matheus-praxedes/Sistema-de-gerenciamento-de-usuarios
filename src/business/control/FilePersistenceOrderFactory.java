package business.control;

import infra.FilePersistence;
import infra.Persistence;


public class FilePersistenceOrderFactory extends PersistenceFactory{

    private static final FilePersistenceOrderFactory instance = new FilePersistenceOrderFactory();

    private FilePersistenceOrderFactory(){}

    public static FilePersistenceOrderFactory getInstance(){
        return instance;
    }

    @Override
    public Persistence getPersistence(){
        return new FilePersistence<>("order.bin");
    }
}