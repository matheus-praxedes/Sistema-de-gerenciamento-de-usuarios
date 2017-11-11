package business.control;

import business.model.Product;
import infra.FilePersistence;
import infra.Persistence;

public class FilePersistenceProductFactory extends PersistenceFactory{

    private static final FilePersistenceProductFactory instance = new FilePersistenceProductFactory();

    private FilePersistenceProductFactory(){}

    public static FilePersistenceProductFactory getInstance(){
        return instance;
    }

    @Override
    public Persistence getPersistence(){
        return new FilePersistence<Product>("product.bin");
    }
}