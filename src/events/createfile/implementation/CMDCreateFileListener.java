/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.createfile.implementation;

import businesslogic.FileAdministration;
import events.eventinfo.implementation.CMDEventInfo;
import events.eventinfo.implementation.CMDEventInfoHandler;
import events.eventinfo.implementation.CMDEventInfoListener;
import mediaDB.Tag;
import ui.Console;

import javax.xml.datatype.Duration;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class CMDCreateFileListener implements events.createfile.CMDCreateFileListener {

    private FileAdministration model;
    private CMDEventInfoHandler infoHandler;

    public CMDCreateFileListener(FileAdministration model) {
        if(model != null) {
            this.model = model;
        } else {
            throw new NullPointerException("Model was null!");
        }
        this.infoHandler = new CMDEventInfoHandler();
    }

    public void onCommandEvent(CMDCreateFileEvent event) {
        boolean success = this.model.addUpload(event.getType(),event.getUploader());
        CMDEventInfo infoEvent;
        CMDEventInfoListener listener = new CMDEventInfoListener(new Console());
        infoHandler.addListener(listener);
        if(success) {
            infoEvent = new CMDEventInfo(this,event.getType() + "-File has been uploaded by " + event.getUploader() + " successfully! Total Files: " + model.getTotalFilesCount());
        } else {
            infoEvent = new CMDEventInfo(this, event.getType() + "-File has been uploaded by " + event.getUploader() + " unsuccessfully! Total Files: " + model.getTotalFilesCount());
        }
        infoHandler.handle(infoEvent);
        infoHandler.removeListener(listener);
    }
}