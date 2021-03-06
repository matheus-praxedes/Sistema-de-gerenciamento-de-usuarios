// Padrão Factory Method
package business.control;

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
        return new FilePersistence<>("sale.bin");
    }
}