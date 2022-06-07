/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.accessfile.implementation;

import businesslogic.FileAdministration;
import events.eventinfo.implementation.CMDEventInfo;
import events.eventinfo.implementation.CMDEventInfoHandler;
import events.eventinfo.implementation.CMDEventInfoListener;
import mediaDB.implementation.File;
import ui.Console;

public class CMDAccessFileListener implements events.accessfile.CMDAccessFileListener {
    private FileAdministration administration;
    private CMDEventInfoHandler infoHandler;

    public CMDAccessFileListener(FileAdministration administration) {
        if(administration==null) {
            throw new NullPointerException("Administration was null!");
        }
        this.administration = administration;
        this.infoHandler = new CMDEventInfoHandler();
    }
    @Override
    public void onCommandEvent(CMDAccessFileEvent event) {
        File toIncrease = this.administration.getFile(event.getAddress());
        toIncrease.increaseAccess();
        CMDEventInfo infoEvent;
        CMDEventInfoListener listener = new CMDEventInfoListener(new Console());
        infoHandler.addListener(listener);
        infoEvent = new CMDEventInfo(this,toIncrease.getClass().getSimpleName() + "-File with address " + toIncrease.getAddress() + " has been accessed! Total Accesses: " + toIncrease.getAccessCount());
        infoHandler.handle(infoEvent);
        infoHandler.removeListener(listener);
    }
}
