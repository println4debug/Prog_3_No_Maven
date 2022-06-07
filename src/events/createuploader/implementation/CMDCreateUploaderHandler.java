/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.createuploader.implementation;

import events.createuploader.CMDCreateUploadertListener;

import java.util.LinkedList;
import java.util.List;

public class CMDCreateUploaderHandler {

    private List<CMDCreateUploadertListener> listenerList;

    public CMDCreateUploaderHandler() {
        this.listenerList = new LinkedList<>();
    }

    public void addListener(CMDCreateUploadertListener listener) {
        try {
            this.listenerList.add(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void removeListener(CMDCreateUploadertListener listener) {
        try {
            this.listenerList.remove(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void handle(CMDCreateUploaderEvent event) { // Datatype?
        for(CMDCreateUploadertListener listener : this.listenerList) {
            listener.onCommandEvent(event);
        }
    }

}
