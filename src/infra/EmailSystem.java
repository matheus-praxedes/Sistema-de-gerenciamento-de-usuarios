
package infra;

import util.InfraException;

/*Esta classe implementa o padrão Adapter*/

public class EmailSystem implements NotificationSystem {

    private String adress;

    @Override
    public void notifyUser(String message) throws InfraException {

        if(message.isEmpty()){
            throw new InfraException("Notification failed.");
        }

        System.out.println("Notification sented to " + adress + " by email.");
    }

    @Override
    public void setDestiny(String number) throws InfraException{

        if(number.isEmpty()){
            throw new InfraException("Notification failed.");
        }

        this.adress = number;
    }
    
}

