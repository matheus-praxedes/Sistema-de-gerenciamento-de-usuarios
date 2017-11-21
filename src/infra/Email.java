
package infra;

import util.InfraException;

/*Esta classe implementa o padrão Adapter*/

public class Email implements NotificationSystem {

    @Override
    public void notifyUser() throws InfraException {
        throw new InfraException("Notification failed."); 
    }
    
}

