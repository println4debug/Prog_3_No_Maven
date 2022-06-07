/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.deletefile.implementation;

import businesslogic.FileAdministration;
import events.eventinfo.implementation.CMDEventInfo;
import events.eventinfo.implementation.CMDEventInfoHandler;
import events.eventinfo.implementation.CMDEventInfoListener;
import ui.Console;

public class CMDDeleteFileListener {

    private FileAdministration model;
    private CMDEventInfoHandler infoHandler;

    public CMDDeleteFileListener(FileAdministration model) {
        if(model != null) {
            this.model = model;
        } else {
            throw new NullPointerException("Model was null!");
        }
        this.infoHandler = new CMDEventInfoHandler();
    }

    public void onCommandEvent(CMDDeleteFileEvent event) {
        boolean success = this.model.removeUpload(event.getAddress());
        CMDEventInfo infoEvent;
        CMDEventInfoListener listener = new CMDEventInfoListener(new Console());
        infoHandler.addListener(listener);
        if(success) {
            infoEvent = new CMDEventInfo(this,"File with address " +event.getAddress() + " has been deleted successfully!  Total Files: " + model.getTotalFilesCount());
        } else {
            infoEvent = new CMDEventInfo(this,"File with address " +event.getAddress() + " has been deleted unsuccessfully! Total Files: " + model.getTotalFilesCount());
        }
        infoHandler.handle(infoEvent);
        infoHandler.removeListener(listener);
    }
}
