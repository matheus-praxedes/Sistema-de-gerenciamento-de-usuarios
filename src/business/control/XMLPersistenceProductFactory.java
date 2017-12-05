package business.control;

import infra.XMLPersistence;
import infra.Persistence;

public class XMLPersistenceProductFactory extends PersistenceFactory{

    private static final XMLPersistenceProductFactory instance = new XMLPersistenceProductFactory();

    private XMLPersistenceProductFactory(){}

    public static XMLPersistenceProductFactory getInstance(){
        return instance;
    }

    @Override
    public Persistence getPersistence(){
        return new XMLPersistence<>("product.xml");
    }
}