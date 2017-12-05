package business.control;

import infra.XMLPersistence;
import infra.Persistence;

public class XMLPersistenceSaleFactory extends PersistenceFactory{

    private static final XMLPersistenceSaleFactory instance = new XMLPersistenceSaleFactory();

    private XMLPersistenceSaleFactory(){}

    public static XMLPersistenceSaleFactory getInstance(){
        return instance;
    }

    @Override
    public Persistence getPersistence(){
        return new XMLPersistence<>("sale.xml");
    }
}