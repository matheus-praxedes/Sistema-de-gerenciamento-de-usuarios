package infra;

import util.InfraException;

/*Esta classe implementa o padrão Adapter*/

public class SMS implements NotificationSystem {

    private String phone_number;

    @Override
    public void notifyUser(String message) throws InfraException {

        if(message.empty()){
            throw new InfraException("Notification failed.");
        }

        System.out.println("Notification sented to " + adress);
    }

    @Override
    public void setDestiny(String number) throws InfraException{

        if(number.empty()){
            throw new InfraException("Notification failed.");
        }

        this.phone_number = number;
    }
    
}