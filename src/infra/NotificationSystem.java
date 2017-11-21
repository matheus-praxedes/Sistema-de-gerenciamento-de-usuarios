package infra;

import util.InfraException;

/* Esta interface implementa o padrão Adapter junto com as classes SMS e Email*/

public interface NotificationSystem {

    public void notifyUser() throws InfraException;
    public void setDestiny() throws InfraException;
}

