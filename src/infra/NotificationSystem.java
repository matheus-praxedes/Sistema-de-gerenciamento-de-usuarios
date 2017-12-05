// Padrão Adapter
package infra;

import util.InfraException;

public interface NotificationSystem {

    public void notifyUser(String message) throws InfraException;
    public void setDestiny(String number)  throws InfraException;
}

