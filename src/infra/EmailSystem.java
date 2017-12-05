// Padrão Adapter
package infra;

import util.InfraException;

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

        if(number == null || number.isEmpty()){
            throw new InfraException("Notification failed.");
        }

        this.adress = number;
    }
}

