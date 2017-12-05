// Padrão Factory Method
package business.control;

import infra.XMLPersistence;
import infra.Persistence;

public class XMLPersistenceOrderFactory extends PersistenceFactory{

    private static final XMLPersistenceOrderFactory instance = new XMLPersistenceOrderFactory();

    private XMLPersistenceOrderFactory(){}

    public static XMLPersistenceOrderFactory getInstance(){
        return instance;
    }

    @Override
    public Persistence getPersistence(){
        return new XMLPersistence<>("order.xml");
    }
}