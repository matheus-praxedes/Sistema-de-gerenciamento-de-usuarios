// Padrão Factory Method
package business.control;

import infra.XMLPersistence;
import infra.Persistence;

public class XMLPersistenceUserFactory extends PersistenceFactory{

    private static final XMLPersistenceUserFactory instance = new XMLPersistenceUserFactory();

    private XMLPersistenceUserFactory(){}

    public static XMLPersistenceUserFactory getInstance(){
        return instance;
    }

    @Override
    public Persistence getPersistence(){
        return new XMLPersistence<>("user.xml");
    }
}