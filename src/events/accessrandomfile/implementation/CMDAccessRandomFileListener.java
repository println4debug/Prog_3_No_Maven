/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.accessrandomfile.implementation;

import businesslogic.FileAdministration;
import events.eventinfo.implementation.CMDEventInfo;
import events.eventinfo.implementation.CMDEventInfoHandler;
import events.eventinfo.implementation.CMDEventInfoListener;
import mediaDB.implementation.File;
import ui.Console;

public class CMDAccessRandomFileListener implements events.accessrandomfile.CMDAccessRandomFileListener {
    private FileAdministration administration;
    private CMDEventInfoHandler infoHandler;

    public CMDAccessRandomFileListener(FileAdministration administration) {
        if(administration==null) {
            throw new NullPointerException("Administration was null!");
        }
        this.administration = administration;
        this.infoHandler = new CMDEventInfoHandler();
    }
    @Override
    public void onCommandEvent(CMDAccessRandomFileEvent event) {
        File toIncrease = this.administration.getRandomFile();
        toIncrease.increaseAccess();
        CMDEventInfo infoEvent;
        CMDEventInfoListener listener = new CMDEventInfoListener(new Console());
        infoHandler.addListener(listener);
        infoEvent = new CMDEventInfo(this,toIncrease.getClass().getSimpleName() + "-File with address " + toIncrease.getAddress() + "has been accessed! Total Accesses: " + toIncrease.getAccessCount());
        infoHandler.handle(infoEvent);
        infoHandler.removeListener(listener);
    }
}
