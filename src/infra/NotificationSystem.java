package infra;

import util.InfraException;

/* Esta interface implementa o padrão Adapter junto com as classes SMS e Email*/

public interface NotificationSystem {

    public void notifyUser(String message) throws InfraException;
    public void setDestiny(String number)  throws InfraException;
}

