package business.control;

import business.model.Sale;
import infra.FilePersistence;
import infra.Persistence;

public class FilePersistenceSaleFactory extends PersistenceFactory{

    private static final FilePersistenceSaleFactory instance = new FilePersistenceSaleFactory();

    private FilePersistenceSaleFactory(){}

    public static FilePersistenceSaleFactory getInstance(){
        return instance;
    }

    @Override
    public Persistence getPersistence(){
        return new FilePersistence<Sale>("sale.bin");
    }
}