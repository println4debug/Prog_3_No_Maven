/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.deleterandomfile.implementation;

import businesslogic.FileAdministration;
import events.eventinfo.implementation.CMDEventInfo;
import events.eventinfo.implementation.CMDEventInfoHandler;
import events.eventinfo.implementation.CMDEventInfoListener;
import ui.Console;

public class CMDDeleteRandomFileListener implements events.deleterandomfile.CMDDeleteRandomFileListener {

    private FileAdministration model;
    private CMDEventInfoHandler infoHandler;

    public CMDDeleteRandomFileListener(FileAdministration model) {
        if(model != null) {
            this.model = model;
        } else {
            throw new NullPointerException("Model was null!");
        }
        this.infoHandler = new CMDEventInfoHandler();
    }

    public void onCommandEvent(CMDDeleteRandomFileEvent event) {
        CMDEventInfo infoEvent;
        CMDEventInfoListener listener = new CMDEventInfoListener(new Console());
        infoHandler.addListener(listener);
        if(model.getTotalFilesCount() > 0) {
            boolean success = this.model.removeRandomUpload();
            if (success) {
                infoEvent = new CMDEventInfo(this, "Random File has been deleted successfully!");
            } else {
                infoEvent = new CMDEventInfo(this, "Random File has been deleted unsuccessfully!");
            }
        } else {
            infoEvent = new CMDEventInfo(this, "*** No random File deleted because there were none to delete! ***");
        }
        infoHandler.handle(infoEvent);
        infoHandler.removeListener(listener);
    }
}
