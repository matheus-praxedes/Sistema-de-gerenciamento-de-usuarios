// Padrão Adapter
package infra;

import util.InfraException;

public class SmsSystem implements NotificationSystem {

    private String phone_number = "";

    @Override
    public void notifyUser(String message) throws InfraException {

        if(message.isEmpty()){
            throw new InfraException("Notification failed.");
        }

        System.out.println("Notification sented to " + phone_number + " by sms.");
    }

    @Override
    public void setDestiny(String number) throws InfraException{

        if(number == null || number.isEmpty()){
            throw new InfraException("Notification failed.");
        }

        this.phone_number = number;
    }
}