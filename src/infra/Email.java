
package infra;

import util.InfraException;

/*Esta classe implementa o padrão Adapter*/

public class Email implements NotificationSystem {

    private String adress;

    @Override
    public void notifyUser(String message) throws InfraException {

        if(message.empty()){
            throw new InfraException("Notification failed.");
        }

        System.out.println("Notification sented to " + adress);
    }

    @Override
    public void setDestiny(String adress) throws InfraException{

        if(adress.empty()){
            throw new InfraException("Notification failed.");
        }

        this.adress = adress;
    }
    
}

