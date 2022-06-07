/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.createuploader.implementation;

import businesslogic.FileAdministration;
import events.createuploader.CMDCreateUploadertListener;
import events.eventinfo.implementation.CMDEventInfo;
import events.eventinfo.implementation.CMDEventInfoHandler;
import events.eventinfo.implementation.CMDEventInfoListener;
import ui.Console;

public class CMDCreateUploaderListener implements CMDCreateUploadertListener {

    private FileAdministration model;
    private CMDEventInfoHandler infoHandler;

    public CMDCreateUploaderListener(FileAdministration model) {
        if(model != null) {
            this.model = model;
        } else {
            throw new NullPointerException("Model was null!");
        }
        this.infoHandler = new CMDEventInfoHandler();
    }

    public void onCommandEvent(CMDCreateUploaderEvent event) {
        boolean success = this.model.createUploader(event.getName());
        CMDEventInfo infoEvent;
        CMDEventInfoListener listener = new CMDEventInfoListener(new Console());
        infoHandler.addListener(listener);
        if(success) {
            infoEvent = new CMDEventInfo(this,"User " + event.getName() + " has been created successfully!");
        } else {
            infoEvent = new CMDEventInfo(this, "User " + event.getName() + " has been created unsuccessfully!");
        }
        infoHandler.handle(infoEvent);
        infoHandler.removeListener(listener);
    }
}
