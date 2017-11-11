package business.control;

import business.model.User;
import infra.FilePersistence;
import infra.Persistence;

public class FilePersistenceUserFactory extends PersistenceFactory{

    private static final FilePersistenceUserFactory instance = new FilePersistenceUserFactory();

    private FilePersistenceUserFactory(){}

    public static FilePersistenceUserFactory getInstance(){
        return instance;
    }

    @Override
    public Persistence getPersistence(){
        return new FilePersistence<User>("user.bin");
    }

}